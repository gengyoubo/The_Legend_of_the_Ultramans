package github.com.gengyoubo.TeShe.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class BlockbenchGeoModelTranslator
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final double EPSILON = 0.000001D;

    private BlockbenchGeoModelTranslator()
    {
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length > 0 && "--batch".equals(args[0])) {
            runBatch(args);
            return;
        }

        if (args.length < 3) {
            System.out.println("Usage: java BlockbenchGeoModelTranslator <input.bbmodel> <output.geo.json> <geometry_identifier>");
            System.out.println("Batch: java BlockbenchGeoModelTranslator --batch <map.json> <bbmodel-dir> <geo-dir> <item-model-dir> <animation-dir> <texture-dir> <namespace>");
            System.out.println("Example geometry_identifier: geometry.teshe.smdrtk_multi_function_pistol");
            return;
        }

        Path input = Path.of(args[0]);
        Path output = Path.of(args[1]);
        String identifier = sanitizeIdentifier(args[2]);

        JsonObject source = GSON.fromJson(Files.readString(input, StandardCharsets.UTF_8), JsonObject.class);
        JsonObject translated = translate(source, identifier);

        Files.createDirectories(output.getParent());
        Files.writeString(output, GSON.toJson(translated) + System.lineSeparator(), StandardCharsets.UTF_8);
        System.out.printf(Locale.ROOT, "Translated Gecko model %s into %s%n", identifier, output);
    }

    private static void runBatch(String[] args) throws IOException
    {
        if (args.length < 8) {
            System.out.println("Batch: java BlockbenchGeoModelTranslator --batch <map.json> <bbmodel-dir> <geo-dir> <item-model-dir> <animation-dir> <texture-dir> <namespace>");
            return;
        }

        Path mapFile = Path.of(args[1]);
        Path bbmodelDir = Path.of(args[2]);
        Path geoDir = Path.of(args[3]);
        Path itemModelDir = Path.of(args[4]);
        Path animationDir = Path.of(args[5]);
        Path textureDir = Path.of(args[6]);
        String namespace = sanitizeResourcePath(args[7]);

        Map<String, String> modelMap = readModelMap(mapFile);
        Files.createDirectories(geoDir);
        Files.createDirectories(itemModelDir);
        Files.createDirectories(animationDir);
        Files.createDirectories(textureDir);

        int converted = 0;
        for (Map.Entry<String, String> entry : modelMap.entrySet()) {
            String fileName = entry.getKey().endsWith(".bbmodel") ? entry.getKey() : entry.getKey() + ".bbmodel";
            String itemName = sanitizeResourcePath(entry.getValue());
            Path input = bbmodelDir.resolve(fileName);
            if (!Files.isRegularFile(input)) {
                System.out.printf(Locale.ROOT, "Skipped missing bbmodel: %s%n", input);
                continue;
            }

            JsonObject source = GSON.fromJson(Files.readString(input, StandardCharsets.UTF_8), JsonObject.class);
            JsonObject translated = translate(source, "geometry." + namespace + "." + itemName);
            Files.writeString(geoDir.resolve(itemName + ".geo.json"), GSON.toJson(translated) + System.lineSeparator(), StandardCharsets.UTF_8);
            Files.writeString(itemModelDir.resolve(itemName + ".json"), GSON.toJson(defaultGeckoItemModel(source)) + System.lineSeparator(), StandardCharsets.UTF_8);
            extractTextures(source, textureDir, itemName);
            converted++;
        }

        Files.writeString(animationDir.resolve("empty.animation.json"), GSON.toJson(emptyAnimation()) + System.lineSeparator(), StandardCharsets.UTF_8);
        System.out.printf(Locale.ROOT, "Batch translated %d Gecko item models from %s%n", converted, bbmodelDir);
    }

    private static Map<String, String> readModelMap(Path mapFile) throws IOException
    {
        JsonObject json = GSON.fromJson(Files.readString(mapFile, StandardCharsets.UTF_8), JsonObject.class);
        Map<String, String> map = new LinkedHashMap<>();
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getAsString());
        }
        return map;
    }

    private static void extractTextures(JsonObject source, Path textureDir, String itemName) throws IOException
    {
        if (!source.has("textures") || !source.get("textures").isJsonArray()) {
            return;
        }

        int index = 0;
        for (JsonElement element : source.getAsJsonArray("textures")) {
            JsonObject texture = element.getAsJsonObject();
            if (!texture.has("source")) {
                index++;
                continue;
            }

            byte[] image = decodeDataImage(texture.get("source").getAsString());
            if (image.length > 0) {
                String suffix = index == 0 ? "" : "_" + index;
                Files.write(textureDir.resolve(itemName + suffix + ".png"), image);
            }
            index++;
        }
    }

    private static byte[] decodeDataImage(String source)
    {
        int comma = source.indexOf(',');
        if (!source.startsWith("data:image/") || comma < 0 || !source.substring(0, comma).contains(";base64")) {
            return new byte[0];
        }
        return Base64.getDecoder().decode(source.substring(comma + 1));
    }

    private static JsonObject defaultGeckoItemModel(JsonObject source)
    {
        JsonObject model = new JsonObject();
        model.addProperty("parent", "builtin/entity");
        if (source.has("display") && source.get("display").isJsonObject()) {
            model.add("display", source.getAsJsonObject("display").deepCopy());
            return model;
        }

        JsonObject display = new JsonObject();
        display.add("thirdperson_righthand", transform(array(0.0D, 90.0D, 0.0D), array(0.0D, 1.5D, 0.0D), array(0.55D, 0.55D, 0.55D)));
        display.add("thirdperson_lefthand", transform(array(0.0D, 90.0D, 0.0D), array(0.0D, 1.5D, 0.0D), array(0.55D, 0.55D, 0.55D)));
        display.add("firstperson_righthand", transform(array(0.0D, 90.0D, 0.0D), array(1.13D, 3.2D, 1.13D), array(0.68D, 0.68D, 0.68D)));
        display.add("firstperson_lefthand", transform(array(0.0D, 90.0D, 0.0D), array(1.13D, 3.2D, 1.13D), array(0.68D, 0.68D, 0.68D)));
        display.add("ground", transform(null, array(0.0D, 2.0D, 0.0D), array(0.35D, 0.35D, 0.35D)));
        display.add("gui", transform(array(30.0D, 225.0D, 0.0D), array(0.0D, 0.0D, 0.0D), array(0.75D, 0.75D, 0.75D)));
        display.add("fixed", transform(array(0.0D, 180.0D, 0.0D), array(0.0D, 0.0D, 0.0D), array(0.65D, 0.65D, 0.65D)));
        model.add("display", display);
        return model;
    }

    private static JsonObject emptyAnimation()
    {
        JsonObject animation = new JsonObject();
        animation.addProperty("format_version", "1.8.0");
        animation.add("animations", new JsonObject());
        return animation;
    }

    public static JsonObject translate(JsonObject source, String identifier)
    {
        double textureWidth = getTextureSize(source, "width");
        double textureHeight = getTextureSize(source, "height");
        Map<String, JsonObject> cubesByUuid = mapElements(source);
        Map<String, JsonObject> groupsByUuid = mapGroups(source);
        Set<String> emittedCubes = new HashSet<>();

        JsonArray bones = new JsonArray();
        if (source.has("outliner")) {
            for (JsonElement child : source.getAsJsonArray("outliner")) {
                emitOutlinerNode(child, null, groupsByUuid, cubesByUuid, emittedCubes, bones, textureWidth, textureHeight);
            }
        }

        JsonArray looseCubes = new JsonArray();
        for (Map.Entry<String, JsonObject> entry : cubesByUuid.entrySet()) {
            if (!emittedCubes.contains(entry.getKey())) {
                looseCubes.add(convertCube(entry.getValue(), textureWidth, textureHeight));
            }
        }
        if (!looseCubes.isEmpty()) {
            JsonObject looseBone = new JsonObject();
            looseBone.addProperty("name", "root");
            looseBone.add("pivot", array(0.0D, 0.0D, 0.0D));
            looseBone.add("cubes", looseCubes);
            bones.add(looseBone);
        }

        JsonObject geometry = new JsonObject();
        JsonObject description = new JsonObject();
        description.addProperty("identifier", sanitizeIdentifier(identifier));
        description.addProperty("texture_width", textureWidth);
        description.addProperty("texture_height", textureHeight);
        addVisibleBounds(source, description);
        geometry.add("description", description);
        geometry.add("bones", bones);

        JsonObject root = new JsonObject();
        root.addProperty("format_version", "1.12.0");
        JsonArray geometryList = new JsonArray();
        geometryList.add(geometry);
        root.add("minecraft:geometry", geometryList);
        return root;
    }

    private static void emitOutlinerNode(
            JsonElement node,
            String parent,
            Map<String, JsonObject> groupsByUuid,
            Map<String, JsonObject> cubesByUuid,
            Set<String> emittedCubes,
            JsonArray bones,
            double textureWidth,
            double textureHeight
    )
    {
        if (node.isJsonPrimitive()) {
            return;
        }

        JsonObject outlinerNode = node.getAsJsonObject();
        String uuid = outlinerNode.get("uuid").getAsString();
        JsonObject group = groupsByUuid.get(uuid);
        if (group == null) {
            return;
        }

        String boneName = uniqueBoneName(sanitizeName(group.has("name") ? group.get("name").getAsString() : uuid), bones);
        JsonObject bone = new JsonObject();
        bone.addProperty("name", boneName);
        if (parent != null) {
            bone.addProperty("parent", parent);
        }
        if (group.has("origin")) {
            bone.add("pivot", copyArray(group.getAsJsonArray("origin")));
        }
        if (group.has("rotation")) {
            addNonZeroArray(bone, "rotation", group.getAsJsonArray("rotation"));
        }

        JsonArray boneCubes = new JsonArray();
        for (JsonElement child : outlinerNode.getAsJsonArray("children")) {
            if (child.isJsonPrimitive()) {
                String cubeUuid = child.getAsString();
                JsonObject cube = cubesByUuid.get(cubeUuid);
                if (cube != null) {
                    boneCubes.add(convertCube(cube, textureWidth, textureHeight));
                    emittedCubes.add(cubeUuid);
                }
            }
        }
        if (!boneCubes.isEmpty()) {
            bone.add("cubes", boneCubes);
        }
        bones.add(bone);

        for (JsonElement child : outlinerNode.getAsJsonArray("children")) {
            if (!child.isJsonPrimitive()) {
                emitOutlinerNode(child, boneName, groupsByUuid, cubesByUuid, emittedCubes, bones, textureWidth, textureHeight);
            }
        }
    }

    private static JsonObject convertCube(JsonObject source, double textureWidth, double textureHeight)
    {
        JsonArray from = source.getAsJsonArray("from");
        JsonArray to = source.getAsJsonArray("to");

        JsonObject cube = new JsonObject();
        copyString(source, cube, "name");
        cube.add("origin", copyArray(from));
        cube.add("size", array(
                value(to, 0) - value(from, 0),
                value(to, 1) - value(from, 1),
                value(to, 2) - value(from, 2)
        ));
        if (source.has("origin")) {
            cube.add("pivot", copyArray(source.getAsJsonArray("origin")));
        }
        if (source.has("rotation")) {
            addNonZeroArray(cube, "rotation", source.getAsJsonArray("rotation"));
        }
        if (source.has("inflate") && Math.abs(source.get("inflate").getAsDouble()) > EPSILON) {
            cube.addProperty("inflate", round(source.get("inflate").getAsDouble()).getAsDouble());
        }
        if (source.has("faces")) {
            cube.add("uv", convertFaces(source.getAsJsonObject("faces"), textureWidth, textureHeight));
        }
        return cube;
    }

    private static JsonObject convertFaces(JsonObject faces, double textureWidth, double textureHeight)
    {
        JsonObject uv = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : faces.entrySet()) {
            JsonObject sourceFace = entry.getValue().getAsJsonObject();
            if (!sourceFace.has("uv")) {
                continue;
            }

            JsonArray sourceUv = sourceFace.getAsJsonArray("uv");
            double u1 = value(sourceUv, 0);
            double v1 = value(sourceUv, 1);
            double u2 = value(sourceUv, 2);
            double v2 = value(sourceUv, 3);
            double width = u2 - u1;
            double height = v2 - v1;
            if (Math.abs(width) < EPSILON) {
                width = textureWidth >= 1.0D ? Math.copySign(1.0D, width == 0.0D ? 1.0D : width) : 1.0D;
            }
            if (Math.abs(height) < EPSILON) {
                height = textureHeight >= 1.0D ? Math.copySign(1.0D, height == 0.0D ? 1.0D : height) : 1.0D;
            }

            JsonObject face = new JsonObject();
            face.add("uv", array(u1, v1));
            face.add("uv_size", array(width, height));
            uv.add(entry.getKey(), face);
        }
        return uv;
    }

    private static void addVisibleBounds(JsonObject source, JsonObject description)
    {
        if (!source.has("visible_box")) {
            return;
        }

        JsonArray visibleBox = source.getAsJsonArray("visible_box");
        if (visibleBox.size() >= 1) {
            description.addProperty("visible_bounds_width", value(visibleBox, 0));
        }
        if (visibleBox.size() >= 2) {
            description.addProperty("visible_bounds_height", value(visibleBox, 1));
        }
        if (visibleBox.size() >= 3) {
            description.add("visible_bounds_offset", array(0.0D, value(visibleBox, 1) / 2.0D, value(visibleBox, 2)));
        }
    }

    private static Map<String, JsonObject> mapElements(JsonObject source)
    {
        Map<String, JsonObject> elements = new HashMap<>();
        if (!source.has("elements")) {
            return elements;
        }
        for (JsonElement element : source.getAsJsonArray("elements")) {
            JsonObject cube = element.getAsJsonObject();
            if (cube.has("uuid") && (!cube.has("type") || "cube".equals(cube.get("type").getAsString()))) {
                elements.put(cube.get("uuid").getAsString(), cube);
            }
        }
        return elements;
    }

    private static Map<String, JsonObject> mapGroups(JsonObject source)
    {
        Map<String, JsonObject> groups = new HashMap<>();
        if (!source.has("groups")) {
            return groups;
        }
        for (JsonElement element : source.getAsJsonArray("groups")) {
            JsonObject group = element.getAsJsonObject();
            if (group.has("uuid")) {
                groups.put(group.get("uuid").getAsString(), group);
            }
        }
        return groups;
    }

    private static String uniqueBoneName(String baseName, JsonArray bones)
    {
        Set<String> usedNames = new HashSet<>();
        for (JsonElement element : bones) {
            JsonObject bone = element.getAsJsonObject();
            if (bone.has("name")) {
                usedNames.add(bone.get("name").getAsString());
            }
        }

        String name = baseName.isBlank() ? "bone" : baseName;
        int suffix = 2;
        while (usedNames.contains(name)) {
            name = baseName + "_" + suffix++;
        }
        return name;
    }

    private static void addNonZeroArray(JsonObject target, String key, JsonArray source)
    {
        boolean nonZero = false;
        for (JsonElement value : source) {
            nonZero |= Math.abs(value.getAsDouble()) > EPSILON;
        }
        if (nonZero) {
            target.add(key, copyArray(source));
        }
    }

    private static double getTextureSize(JsonObject source, String key)
    {
        if (source.has("resolution")) {
            JsonObject resolution = source.getAsJsonObject("resolution");
            if (resolution.has(key)) {
                return resolution.get(key).getAsDouble();
            }
        }
        return 256.0D;
    }

    private static String sanitizeIdentifier(String identifier)
    {
        return sanitizeResourcePath(identifier.toLowerCase(Locale.ROOT).replace(':', '.'));
    }

    private static String sanitizeName(String name)
    {
        return sanitizeResourcePath(name.toLowerCase(Locale.ROOT)).replace('/', '_').replace('.', '_');
    }

    private static String sanitizeResourcePath(String path)
    {
        String lowered = path.toLowerCase(Locale.ROOT).replace('\\', '/');
        StringBuilder sanitized = new StringBuilder();
        for (int i = 0; i < lowered.length(); i++) {
            char c = lowered.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.' || c == '_' || c == '-' || c == '/') {
                sanitized.append(c);
            } else {
                sanitized.append('_');
            }
        }
        return sanitized.toString();
    }

    private static void copyString(JsonObject source, JsonObject target, String property)
    {
        if (source.has(property)) {
            target.addProperty(property, source.get(property).getAsString());
        }
    }

    private static JsonArray copyArray(JsonArray source)
    {
        JsonArray copy = new JsonArray();
        for (JsonElement value : source) {
            copy.add(round(value.getAsDouble()));
        }
        return copy;
    }

    private static JsonArray array(double... values)
    {
        JsonArray array = new JsonArray();
        for (double value : values) {
            array.add(round(value));
        }
        return array;
    }

    private static double value(JsonArray array, int index)
    {
        return array.get(index).getAsDouble();
    }

    private static JsonPrimitive round(double value)
    {
        double rounded = Math.round(value * 100000.0D) / 100000.0D;
        return new JsonPrimitive(Math.abs(rounded) < EPSILON ? 0.0D : rounded);
    }

    private static JsonObject transform(JsonArray rotation, JsonArray translation, JsonArray scale)
    {
        JsonObject transform = new JsonObject();
        if (rotation != null) {
            transform.add("rotation", rotation);
        }
        if (translation != null) {
            transform.add("translation", translation);
        }
        if (scale != null) {
            transform.add("scale", scale);
        }
        return transform;
    }
}
