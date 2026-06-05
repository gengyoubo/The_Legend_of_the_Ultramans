package github.com.gengyoubo.TeShe.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.model.ZombiePlagueSymbiosis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class ZombiePlagueSymbiosisOuterLayer<T extends Zombie> extends RenderLayer<T, ZombiePlagueSymbiosis<T>>
{
    private static final ResourceLocation DROWNED_OUTER_LAYER = new ResourceLocation(TE.MODID, "textures/entity/drowned_outer_layer.png");
    private final ZombiePlagueSymbiosis<T> model;

    public ZombiePlagueSymbiosisOuterLayer(RenderLayerParent<T, ZombiePlagueSymbiosis<T>> parent, EntityRendererProvider.Context context)
    {
        super(parent);
        this.model = new ZombiePlagueSymbiosis<>(context.bakeLayer(ZombiePlagueSymbiosis.OUTER_LAYER_LOCATION));
    }

    @Override
    public void render(
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            T entity,
            float limbSwing,
            float limbSwingAmount,
            float partialTick,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    )
    {
        coloredCutoutModelCopyLayerRender(getParentModel(), model, DROWNED_OUTER_LAYER, poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTick, 1.0F, 1.0F, 1.0F);
    }
}

