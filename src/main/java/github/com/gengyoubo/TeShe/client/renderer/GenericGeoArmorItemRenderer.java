package github.com.gengyoubo.TeShe.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.com.gengyoubo.TeShe.client.model.GenericGeoArmorModel;
import github.com.gengyoubo.TeShe.item.GeckoArmorItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ArmorItem;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GenericGeoArmorItemRenderer extends GeoItemRenderer<GeckoArmorItem>
{
    public GenericGeoArmorItemRenderer()
    {
        super(new GenericGeoArmorModel<>());
    }

    @Override
    public void preRender(
            PoseStack poseStack,
            GeckoArmorItem animatable,
            BakedGeoModel model,
            MultiBufferSource bufferSource,
            VertexConsumer buffer,
            boolean isReRender,
            float partialTick,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha
    )
    {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.translate(0.0D, getItemYOffset(animatable.getType()), 0.0D);
        applyItemVisibility(model, animatable.getType());
    }

    private static double getItemYOffset(ArmorItem.Type type)
    {
        return switch (type) {
            case HELMET -> -1.75D;
            case CHESTPLATE -> -1.15D;
            case LEGGINGS -> -0.5D;
            case BOOTS -> -0.25D;
        };
    }

    private static void applyItemVisibility(BakedGeoModel model, ArmorItem.Type type)
    {
        setVisible(model, "armorHead", false);
        setVisible(model, "armorBody", false);
        setVisible(model, "armorRightArm", false);
        setVisible(model, "armorLeftArm", false);
        setVisible(model, "armorRightLeg", false);
        setVisible(model, "armorLeftLeg", false);
        setVisible(model, "armorRightBoot", false);
        setVisible(model, "armorLeftBoot", false);

        switch (type) {
            case HELMET -> setVisible(model, "armorHead", true);
            case CHESTPLATE -> {
                setVisible(model, "armorBody", true);
                setVisible(model, "armorRightArm", true);
                setVisible(model, "armorLeftArm", true);
            }
            case LEGGINGS -> {
                setVisible(model, "armorRightLeg", true);
                setVisible(model, "armorLeftLeg", true);
            }
            case BOOTS -> {
                setVisible(model, "armorRightBoot", true);
                setVisible(model, "armorLeftBoot", true);
            }
        }
    }

    private static void setVisible(BakedGeoModel model, String boneName, boolean visible)
    {
        model.getBone(boneName).ifPresent(bone -> setVisible(bone, visible));
    }

    private static void setVisible(GeoBone bone, boolean visible)
    {
        bone.setHidden(!visible);
        bone.setChildrenHidden(!visible);
    }
}
