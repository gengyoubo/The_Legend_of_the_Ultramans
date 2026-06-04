package github.com.gengyoubo.TeShe.client.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.item.GeckoArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GenericGeoArmorItemModel extends GeoModel<GeckoArmorItem>
{
    @Override
    public ResourceLocation getModelResource(GeckoArmorItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(TE.MODID, "geo/item/" + animatable.getGeckoItemModelName() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeckoArmorItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(TE.MODID, "textures/armor/" + animatable.getGeckoModelName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeckoArmorItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(TE.MODID, "animations/armor/empty.animation.json");
    }
}
