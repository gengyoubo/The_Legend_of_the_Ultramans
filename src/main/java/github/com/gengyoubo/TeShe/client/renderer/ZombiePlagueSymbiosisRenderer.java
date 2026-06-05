package github.com.gengyoubo.TeShe.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.model.ZombiePlagueSymbiosis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;

public class ZombiePlagueSymbiosisRenderer<T extends Zombie> extends MobRenderer<T, ZombiePlagueSymbiosis<T>>
{
    private final ResourceLocation texture;

    public ZombiePlagueSymbiosisRenderer(EntityRendererProvider.Context context, String textureName, boolean hasDrownedOuterLayer)
    {
        super(context, new ZombiePlagueSymbiosis<>(context.bakeLayer(ZombiePlagueSymbiosis.LAYER_LOCATION)), 0.5F);
        this.texture = new ResourceLocation(TE.MODID, "textures/entity/" + textureName + ".png");
        addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        if (hasDrownedOuterLayer) {
            addLayer(new ZombiePlagueSymbiosisOuterLayer<>(this, context));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T entity)
    {
        return texture;
    }

    @Override
    protected boolean isShaking(T entity)
    {
        return super.isShaking(entity) || entity.isUnderWaterConverting();
    }

    @Override
    protected void setupRotations(T entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks)
    {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
        if (entity instanceof Drowned drowned) {
            float swimAmount = drowned.getSwimAmount(partialTicks);
            if (swimAmount > 0.0F) {
                float targetRot = -10.0F - drowned.getXRot();
                float rot = Mth.lerp(swimAmount, 0.0F, targetRot);
                poseStack.rotateAround(Axis.XP.rotationDegrees(rot), 0.0F, drowned.getBbHeight() / 2.0F, 0.0F);
            }
        }
    }
}

