package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueEndermanEntity;
import github.com.gengyoubo.TeShe.model.EndermanInfection;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EndermanInfectionRenderer extends MobRenderer<PlagueEndermanEntity, EndermanInfection<PlagueEndermanEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(TE.MODID, "textures/entity/enderman.png");

    public EndermanInfectionRenderer(EntityRendererProvider.Context context)
    {
        super(context, new EndermanInfection<>(context.bakeLayer(EndermanInfection.LAYER_LOCATION)), 0.5F);
        addLayer(new EndermanInfectionEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(PlagueEndermanEntity entity)
    {
        return TEXTURE;
    }
}
