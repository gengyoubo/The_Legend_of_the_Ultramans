package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GenericGeoEntityRenderer<T extends Entity & GeoAnimatable> extends GeoEntityRenderer<T>
{
    public GenericGeoEntityRenderer(EntityRendererProvider.Context context, String modelName, float shadowRadius)
    {
        super(context, new DefaultedEntityGeoModel<T>(new ResourceLocation(TE.MODID, modelName))
                .withAltAnimations(new ResourceLocation(TE.MODID, "empty")));
        this.shadowRadius = shadowRadius;
    }
}
