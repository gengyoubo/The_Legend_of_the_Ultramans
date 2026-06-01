package github.com.gengyoubo.TeShe.client.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.item.GeckoModelProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.GeoModel;

public class GenericGeoArmorModel<T extends GeoItem & GeckoModelProvider> extends GeoModel<T>
{
    @Override
    public ResourceLocation getModelResource(T animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(TE.MODID, "geo/armor/" + animatable.getGeckoModelName() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(TE.MODID, "textures/armor/" + animatable.getGeckoModelName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(TE.MODID, "animations/armor/empty.animation.json");
    }
}
