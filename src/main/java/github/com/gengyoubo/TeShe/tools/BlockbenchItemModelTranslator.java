package github.com.gengyoubo.TeShe.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;

public final class BlockbenchItemModelTranslator
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final double[] JAVA_ROTATION_ANGLES = {-45.0D, -22.5D, 0.0D, 22.5D, 45.0D};
    private static final double EPSILON = 0.000001D;

    private BlockbenchItemModelTranslator()
    {
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length < 3) {
            System.out.println("Usage: java BlockbenchItemModelTranslator <input.bbmodel|input.json> <output.json> <texture_ref>");
            System.out.println("Example texture_ref: teshe:item/smdrtk_multi_function_pistol");
            return;
        }

        Path input = Path.of(args[0]);
        Path output = Path.of(args[1]);
        String textureRef = args[2];

        JsonObject source = GSON.fromJson(Files.readString(input, StandardCharsets.UTF_8), JsonObject.class);
        JsonObject translated = translate(source, textureRef);

        Files.createDirectories(output.getParent());
        Files.writeString(output, GSON.toJson(translated) + System.lineSeparator(), StandardCharsets.UTF_8);
        System.out.printf(Locale.ROOT, "Translated %d elements into %s%n", translated.getAsJsonArray("elements").size(), output);
    }

    public static JsonObject translate(JsonObject source, String textureRef)
    {
        boolean blockbenchProject = source.has("meta") && source.has("resolution");
        double textureWidth = getTextureWidth(source, blockbenchProject);
        double textureHeight = getTextureHeight(source, blockbenchProject);
        double uvScaleX = textureWidth / 16.0D;
        double uvScaleY = textureHeight / 16.0D;
        PositionTransform positionTransform = createPositionTransform(source, blockbenchProject);

        JsonObject target = new JsonObject();
        target.addProperty("credit", "Translated from Blockbench by BlockbenchItemModelTranslator");
        target.add("texture_size", array(textureWidth, textureHeight));

        JsonObject textures = convertTextures(source, textureRef);
        textures.addProperty("particle", textureRef);
        target.add("textures", textures);

        JsonArray elements = new JsonArray();
        for (JsonElement element : source.getAsJsonArray("elements")) {
            JsonObject cube = element.getAsJsonObject();
            if (cube.has("type") && !"cube".equals(cube.get("type").getAsString())) {
                continue;
            }

            JsonObject converted = new JsonObject();
            copyString(cube, converted, "name");
            converted.add("from", convertPosition(cube.getAsJsonArray("from"), blockbenchProject, positionTransform));
            converted.add("to", convertPosition(cube.getAsJsonArray("to"), blockbenchProject, positionTransform));

            JsonObject rotation = convertRotation(cube, blockbenchProject, positionTransform);
            if (rotation != null) {
                converted.add("rotation", rotation);
            }

            converted.add("faces", convertFaces(cube.getAsJsonObject("faces"), blockbenchProject, uvScaleX, uvScaleY));
            elements.add(converted);
        }
        target.add("elements", elements);
        target.add("display", defaultDisplay());
        return target;
    }

    private static JsonObject convertFaces(JsonObject sourceFaces, boolean blockbenchProject, double uvScaleX, double uvScaleY)
    {
        JsonObject faces = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : sourceFaces.entrySet()) {
            JsonObject sourceFace = entry.getValue().getAsJsonObject();
            JsonObject face = new JsonObject();
            if (sourceFace.has("uv")) {
                face.add("uv", convertUv(sourceFace.getAsJsonArray("uv"), blockbenchProject, uvScaleX, uvScaleY));
            }
            if (sourceFace.has("rotation")) {
                face.add("rotation", sourceFace.get("rotation"));
            }
            if (sourceFace.has("tintindex")) {
                face.add("tintindex", sourceFace.get("tintindex"));
            }
            face.addProperty("texture", convertTextureReference(sourceFace));
            faces.add(entry.getKey(), face);
        }
        return faces;
    }

    private static JsonObject convertTextures(JsonObject source, String textureRef)
    {
        JsonObject textures = new JsonObject();
        int textureCount = source.has("textures") && source.get("textures").isJsonArray()
                ? source.getAsJsonArray("textures").size()
                : 1;
        for (int i = 0; i < textureCount; i++) {
            textures.addProperty(String.valueOf(i), i == 0 ? textureRef : textureRef + "_" + i);
        }
        return textures;
    }

    private static String convertTextureReference(JsonObject sourceFace)
    {
        if (!sourceFace.has("texture")) {
            return "#0";
        }

        JsonElement texture = sourceFace.get("texture");
        if (texture.isJsonPrimitive()) {
            JsonPrimitive primitive = texture.getAsJsonPrimitive();
            if (primitive.isNumber()) {
                return "#" + primitive.getAsInt();
            }

            String value = primitive.getAsString();
            if (value.startsWith("#")) {
                return value;
            }
            return "#" + value;
        }

        return "#0";
    }

    private static JsonObject convertRotation(JsonObject cube, boolean blockbenchProject, PositionTransform positionTransform)
    {
        if (blockbenchProject) {
            if (!cube.has("rotation")) {
                return null;
            }
            JsonArray rotation = cube.getAsJsonArray("rotation");
            return singleAxisRotation(
                    value(rotation, 0),
                    value(rotation, 1),
                    value(rotation, 2),
                    cube.has("origin") ? convertPosition(cube.getAsJsonArray("origin"), true, positionTransform) : array(8.0D, 8.0D, 8.0D)
            );
        }

        if (!cube.has("rotation")) {
            return null;
        }

        JsonObject rotation = cube.getAsJsonObject("rotation");
        JsonArray origin = rotation.has("origin") ? copyArray(rotation.getAsJsonArray("origin")) : array(8.0D, 8.0D, 8.0D);
        if (rotation.has("axis") && rotation.has("angle")) {
            double snapped = snapAngle(rotation.get("angle").getAsDouble());
            if (Math.abs(snapped) < EPSILON) {
                return null;
            }
            JsonObject converted = new JsonObject();
            converted.add("origin", origin);
            converted.addProperty("axis", rotation.get("axis").getAsString());
            converted.addProperty("angle", snapped);
            return converted;
        }

        return singleAxisRotation(
                rotation.has("x") ? rotation.get("x").getAsDouble() : 0.0D,
                rotation.has("y") ? rotation.get("y").getAsDouble() : 0.0D,
                rotation.has("z") ? rotation.get("z").getAsDouble() : 0.0D,
                origin
        );
    }

    private static JsonObject singleAxisRotation(double x, double y, double z, JsonArray origin)
    {
        String axis = "x";
        double angle = x;
        if (Math.abs(y) > Math.abs(angle)) {
            axis = "y";
            angle = y;
        }
        if (Math.abs(z) > Math.abs(angle)) {
            axis = "z";
            angle = z;
        }

        double snapped = snapAngle(angle);
        if (Math.abs(snapped) < EPSILON) {
            return null;
        }

        JsonObject rotation = new JsonObject();
        rotation.add("origin", origin);
        rotation.addProperty("axis", axis);
        rotation.addProperty("angle", snapped);
        return rotation;
    }

    private static JsonArray convertPosition(JsonArray source, boolean blockbenchProject, PositionTransform positionTransform)
    {
        double x = blockbenchProject ? value(source, 0) + 8.0D : value(source, 0);
        double y = blockbenchProject ? value(source, 1) : value(source, 1);
        double z = blockbenchProject ? value(source, 2) + 8.0D : value(source, 2);

        return array(
                positionTransform.apply(x),
                positionTransform.apply(y),
                positionTransform.apply(z)
        );
    }

    private static PositionTransform createPositionTransform(JsonObject source, boolean blockbenchProject)
    {
        if (!source.has("elements")) {
            return PositionTransform.IDENTITY;
        }

        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (JsonElement element : source.getAsJsonArray("elements")) {
            JsonObject cube = element.getAsJsonObject();
            if (cube.has("type") && !"cube".equals(cube.get("type").getAsString())) {
                continue;
            }
            if (!cube.has("from") || !cube.has("to")) {
                continue;
            }

            JsonArray from = cube.getAsJsonArray("from");
            JsonArray to = cube.getAsJsonArray("to");
            for (int i = 0; i < 3; i++) {
                double convertedFrom = basePositionValue(from, i, blockbenchProject);
                double convertedTo = basePositionValue(to, i, blockbenchProject);
                min = Math.min(min, Math.min(convertedFrom, convertedTo));
                max = Math.max(max, Math.max(convertedFrom, convertedTo));
            }
        }

        if (!Double.isFinite(min) || !Double.isFinite(max) || (min >= -16.0D && max <= 32.0D)) {
            return PositionTransform.IDENTITY;
        }

        double center = (min + max) / 2.0D;
        double scale = Math.min(1.0D, 47.0D / (max - min));
        return new PositionTransform(center, scale);
    }

    private static double basePositionValue(JsonArray position, int index, boolean blockbenchProject)
    {
        double value = value(position, index);
        if (blockbenchProject && (index == 0 || index == 2)) {
            return value + 8.0D;
        }
        return value;
    }

    private static JsonArray convertUv(JsonArray source, boolean blockbenchProject, double uvScaleX, double uvScaleY)
    {
        if (!blockbenchProject) {
            return fixZeroSizeUv(copyArray(source), 1.0D / 16.0D, 1.0D / 16.0D);
        }

        JsonArray uv = array(
                value(source, 0) / uvScaleX,
                value(source, 1) / uvScaleY,
                value(source, 2) / uvScaleX,
                value(source, 3) / uvScaleY
        );
        return fixZeroSizeUv(uv, 1.0D / uvScaleX, 1.0D / uvScaleY);
    }

    private static JsonArray fixZeroSizeUv(JsonArray uv, double minWidth, double minHeight)
    {
        double u1 = value(uv, 0);
        double v1 = value(uv, 1);
        double u2 = value(uv, 2);
        double v2 = value(uv, 3);

        if (Math.abs(u2 - u1) < EPSILON) {
            u2 = expandUvEnd(u1, minWidth);
        }
        if (Math.abs(v2 - v1) < EPSILON) {
            v2 = expandUvEnd(v1, minHeight);
        }

        return array(u1, v1, u2, v2);
    }

    private static double expandUvEnd(double start, double amount)
    {
        if (start + amount <= 16.0D) {
            return start + amount;
        }
        return start - amount;
    }

    private static double getTextureWidth(JsonObject source, boolean blockbenchProject)
    {
        if (blockbenchProject && source.has("resolution")) {
            JsonObject resolution = source.getAsJsonObject("resolution");
            if (resolution.has("width")) {
                return resolution.get("width").getAsDouble();
            }
        }
        if (source.has("texture_size")) {
            return value(source.getAsJsonArray("texture_size"), 0);
        }
        return 256.0D;
    }

    private static double getTextureHeight(JsonObject source, boolean blockbenchProject)
    {
        if (blockbenchProject && source.has("resolution")) {
            JsonObject resolution = source.getAsJsonObject("resolution");
            if (resolution.has("height")) {
                return resolution.get("height").getAsDouble();
            }
        }
        if (source.has("texture_size")) {
            return value(source.getAsJsonArray("texture_size"), 1);
        }
        return 256.0D;
    }

    private static double snapAngle(double angle)
    {
        double best = JAVA_ROTATION_ANGLES[0];
        double bestDistance = Math.abs(angle - best);
        for (double candidate : JAVA_ROTATION_ANGLES) {
            double distance = Math.abs(angle - candidate);
            if (distance < bestDistance) {
                best = candidate;
                bestDistance = distance;
            }
        }
        return best;
    }

    private static JsonObject defaultDisplay()
    {
        JsonObject display = new JsonObject();
        display.add("thirdperson_righthand", transform(array(72.0D, 90.0D, 180.0D), array(0.0D, 2.25D, 1.5D), array(0.55D, 0.55D, 0.55D)));
        display.add("thirdperson_lefthand", transform(array(72.0D, 90.0D, 180.0D), array(0.0D, 2.25D, 1.5D), array(0.55D, 0.55D, 0.55D)));
        display.add("firstperson_righthand", transform(array(0.0D, -2.0D, 0.0D), array(1.2D, 3.25D, 1.5D), array(0.68D, 0.68D, 0.68D)));
        display.add("firstperson_lefthand", transform(array(0.0D, 182.0D, 0.0D), array(1.2D, 3.25D, 1.5D), array(0.68D, 0.68D, 0.68D)));
        display.add("gui", transform(array(30.0D, 225.0D, 0.0D), array(0.0D, 0.0D, 0.0D), array(0.72D, 0.72D, 0.72D)));
        display.add("ground", transform(null, array(0.0D, 3.0D, 0.0D), array(0.45D, 0.45D, 0.45D)));
        display.add("fixed", transform(array(0.0D, 180.0D, 0.0D), null, array(0.65D, 0.65D, 0.65D)));
        return display;
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
            copy.add(value);
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

    private record PositionTransform(double center, double scale)
    {
        private static final PositionTransform IDENTITY = new PositionTransform(8.0D, 1.0D);

        private double apply(double value)
        {
            return 8.0D + ((value - center) * scale);
        }
    }
}
