package github.com.gengyoubo.TeShe.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.GuardianElderEntity;
import github.com.gengyoubo.TeShe.model.Guardian_elderInfection;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GuardianElderInfectionRenderer extends MobRenderer<GuardianElderEntity, Guardian_elderInfection<GuardianElderEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(TE.MODID, "textures/entity/guardian_elder.png");

    public GuardianElderInfectionRenderer(EntityRendererProvider.Context context)
    {
        super(context, new Guardian_elderInfection<>(context.bakeLayer(Guardian_elderInfection.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(GuardianElderEntity entity)
    {
        return TEXTURE;
    }

    @Override
    protected void scale(GuardianElderEntity entity, PoseStack poseStack, float partialTickTime)
    {
        poseStack.scale(2.35F, 2.35F, 2.35F);
    }
}
