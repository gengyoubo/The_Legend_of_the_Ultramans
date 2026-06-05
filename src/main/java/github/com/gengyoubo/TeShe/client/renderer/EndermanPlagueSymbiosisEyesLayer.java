package github.com.gengyoubo.TeShe.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueEndermanEntity;
import github.com.gengyoubo.TeShe.model.EndermanPlagueSymbiosis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class EndermanPlagueSymbiosisEyesLayer extends RenderLayer<PlagueEndermanEntity, EndermanPlagueSymbiosis<PlagueEndermanEntity>>
{
    private static final ResourceLocation EYES = new ResourceLocation(TE.MODID, "textures/entity/enderman_eyes.png");
    private static final RenderType EYES_RENDER_TYPE = RenderType.eyes(EYES);

    public EndermanPlagueSymbiosisEyesLayer(RenderLayerParent<PlagueEndermanEntity, EndermanPlagueSymbiosis<PlagueEndermanEntity>> parent)
    {
        super(parent);
    }

    @Override
    public void render(
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            PlagueEndermanEntity entity,
            float limbSwing,
            float limbSwingAmount,
            float partialTick,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    )
    {
        getParentModel().renderToBuffer(poseStack, bufferSource.getBuffer(EYES_RENDER_TYPE), 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}

