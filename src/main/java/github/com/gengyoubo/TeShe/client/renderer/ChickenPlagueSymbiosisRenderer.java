package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.TesheChickenEntity;
import github.com.gengyoubo.TeShe.model.ChickenPlagueSymbiosis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChickenPlagueSymbiosisRenderer extends MobRenderer<TesheChickenEntity, ChickenPlagueSymbiosis>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(TE.MODID, "textures/entity/chicken.png");

    public ChickenPlagueSymbiosisRenderer(EntityRendererProvider.Context context)
    {
        super(context, new ChickenPlagueSymbiosis(context.bakeLayer(ChickenPlagueSymbiosis.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(TesheChickenEntity entity)
    {
        return TEXTURE;
    }
}

