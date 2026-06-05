package github.com.gengyoubo.TeShe.client.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueEnderDragonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PlagueEnderDragonGeoModel extends GeoModel<PlagueEnderDragonEntity>
{
    private static final ResourceLocation MODEL = new ResourceLocation(TE.MODID, "geo/entity/moyinglong.geo.json");
    private static final ResourceLocation TEXTURE = new ResourceLocation(TE.MODID, "textures/entity/moyinglong.png");
    private static final ResourceLocation ANIMATION = new ResourceLocation(TE.MODID, "animations/entity/moyinglong.animation.json");

    @Override
    public ResourceLocation getModelResource(PlagueEnderDragonEntity animatable)
    {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(PlagueEnderDragonEntity animatable)
    {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(PlagueEnderDragonEntity animatable)
    {
        return ANIMATION;
    }
}
