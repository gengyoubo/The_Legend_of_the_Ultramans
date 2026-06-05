package github.com.gengyoubo.TeShe.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.Zombie;

public class ZombiePlagueSymbiosis<T extends Zombie> extends HierarchicalModel<T> implements ArmedModel
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("teshe", "zombie_plague_symbiosis"), "main");
    public static final ModelLayerLocation OUTER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("teshe", "zombie_plague_symbiosis_outer"), "main");
    private final ModelPart group4;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public ZombiePlagueSymbiosis(ModelPart root)
    {
        this.group4 = root.getChild("group4");
        this.body = group4.getChild("Body");
        this.head = group4.getChild("Head");
        this.rightArm = group4.getChild("RightArm");
        this.leftArm = group4.getChild("LeftArm");
        this.rightLeg = group4.getChild("RightLeg");
        this.leftLeg = group4.getChild("LeftLeg");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition group4 = partdefinition.addOrReplaceChild("group4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition body = group4.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition group3 = body.addOrReplaceChild("group3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        group3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 2.0F, 6.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7F, 3.6F, 4.6F, 0.599F, 0.5218F, -0.3675F));
        group3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(24, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, 6.8F, -3.0F, 0.6577F, 0.5218F, 0.3675F));

        PartDefinition head = group4.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition group2 = head.addOrReplaceChild("group2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        group2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 32).addBox(-1.0F, -1.9F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.1F, 3.0F, -2.1267F, 1.2681F, -1.6902F));
        group2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 32).addBox(-1.0F, -1.9F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.1F, 3.0F, -1.7322F, -0.2043F, -1.1219F));
        group2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 32).addBox(-1.0F, -1.9F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.1F, 3.0F, 0.241F, 0.1748F, -1.1831F));
        group2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.0F, 3.0F, -0.6981F, 0.0F, -0.5672F));

        group4.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        PartDefinition leftArm = group4.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
        PartDefinition group = leftArm.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offset(2.233F, -1.1093F, -2.2897F));
        group.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(36, 32).addBox(-1.7F, -5.0F, -1.7F, 2.7F, 5.1F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.133F, 1.1093F, 2.6898F, 0.4561F, 0.1296F, 0.6558F));
        group.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -5.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.133F, 1.1093F, 0.5897F, 1.1408F, 0.3189F, 0.2122F));
        group.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.767F, 1.1093F, 0.5897F, 0.3927F, 0.0F, 1.1345F));

        group4.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        group4.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root()
    {
        return group4;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        root().getAllParts().forEach(ModelPart::resetPose);
        head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        head.xRot = headPitch * ((float)Math.PI / 180F);
        rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        rightArm.zRot = 0.0F;
        leftArm.zRot = 0.0F;
        rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        rightLeg.yRot = 0.0F;
        leftLeg.yRot = 0.0F;
        AnimationUtils.animateZombieArms(leftArm, rightArm, entity.isAggressive(), attackTime, ageInTicks);
    }

    @Override
    public void translateToHand(HumanoidArm arm, PoseStack poseStack)
    {
        root().translateAndRotate(poseStack);
        ModelPart modelpart = arm == HumanoidArm.LEFT ? leftArm : rightArm;
        modelpart.translateAndRotate(poseStack);
        poseStack.translate((arm == HumanoidArm.LEFT ? 1.0F : -1.0F) / 16.0F, 0.125F, 0.0F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        group4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

