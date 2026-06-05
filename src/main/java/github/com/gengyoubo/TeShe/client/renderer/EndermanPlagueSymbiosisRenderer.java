package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueEndermanEntity;
import github.com.gengyoubo.TeShe.model.EndermanPlagueSymbiosis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EndermanPlagueSymbiosisRenderer extends MobRenderer<PlagueEndermanEntity, EndermanPlagueSymbiosis<PlagueEndermanEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(TE.MODID, "textures/entity/enderman.png");

    public EndermanPlagueSymbiosisRenderer(EntityRendererProvider.Context context)
    {
        super(context, new EndermanPlagueSymbiosis<>(context.bakeLayer(EndermanPlagueSymbiosis.LAYER_LOCATION)), 0.5F);
        addLayer(new EndermanPlagueSymbiosisEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(PlagueEndermanEntity entity)
    {
        return TEXTURE;
    }
}

