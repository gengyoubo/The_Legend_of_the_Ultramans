package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueHoglinEntity;
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

public class HoglinPlagueSymbiosis extends HierarchicalModel<PlagueHoglinEntity>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "youzhushou_plague_symbiosis"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart backRight;
    private final ModelPart backLeft;
    private final ModelPart frontRight;
    private final ModelPart frontLeft;

    public HoglinPlagueSymbiosis(ModelPart root)
    {
        this.root = root.getChild("root");
        this.head = findBone(this.root, "body", "head");
        this.backRight = findBone(this.root, "leg_back_right");
        this.backLeft = findBone(this.root, "leg_back_left");
        this.frontRight = findBone(this.root, "leg_front_right");
        this.frontLeft = findBone(this.root, "leg_front_left");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition body_0 = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.F, -6.F, -4.F, 16.F, 14.F, 26.F, new CubeDeformation(0.02F)).texOffs(0, 65).addBox(0.F, -13.F, -7.F, 0.F, 10.F, 19.F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(0.F, 5.F, -3.F, 0.F, 0.F, 0.F));
        PartDefinition group_1 = body_0.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.F, -2.F, 3.4125F, 0.F, 0.F, 0.F));
        PartDefinition group7_2 = group_1.addOrReplaceChild("group7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, 0.F, 0.F, -1.67697F, 1.0234F, -1.90443F));
        group7_2.addOrReplaceChild("cube_0", CubeListBuilder.create().texOffs(78, 80).addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, -4.F, -4.F, -0.45476F, 0.27602F, -0.13247F));
        group7_2.addOrReplaceChild("cube_1", CubeListBuilder.create().texOffs(78, 80).addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, -4.F, -1.F, -0.91546F, 0.80239F, -0.50343F));
        PartDefinition group11_3 = group_1.addOrReplaceChild("group11", CubeListBuilder.create(), PartPose.offsetAndRotation(14.F, 3.F, 9.175F, 1.67697F, 1.0234F, 1.90443F));
        group11_3.addOrReplaceChild("cube_2", CubeListBuilder.create().texOffs(78, 80).mirror().addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(6.F, -4.F, 4.F, 0.45476F, 0.27602F, 0.13247F));
        group11_3.addOrReplaceChild("cube_3", CubeListBuilder.create().texOffs(78, 80).mirror().addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(6.F, -4.F, 1.F, 0.91546F, 0.80239F, 0.50343F));
        PartDefinition group3_4 = group_1.addOrReplaceChild("group3", CubeListBuilder.create(), PartPose.offsetAndRotation(4.7625F, 1.0625F, 14.375F, 0.F, 0.F, 0.F));
        group3_4.addOrReplaceChild("cube_4", CubeListBuilder.create().texOffs(84, 20).mirror().addBox(-1.F, -1.F, -3.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-6.F, -5.F, 4.F, 0.43633F, -0.6545F, 0.F));
        group3_4.addOrReplaceChild("cube_5", CubeListBuilder.create().texOffs(84, 20).mirror().addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-6.44827F, -2.57738F, 2.28098F, -1.23015F, -1.20711F, 0.93169F));
        PartDefinition group4_5 = group_1.addOrReplaceChild("group4", CubeListBuilder.create(), PartPose.offsetAndRotation(9.2375F, 1.0625F, 14.375F, 1.1781F, 0.F, 0.F));
        group4_5.addOrReplaceChild("cube_6", CubeListBuilder.create().texOffs(84, 20).addBox(-1.F, -1.F, -3.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.F, -5.F, 4.F, 0.43633F, 0.6545F, 0.F));
        group4_5.addOrReplaceChild("cube_7", CubeListBuilder.create().texOffs(84, 20).addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.44827F, -2.57738F, 2.28098F, -1.23015F, 1.20711F, -0.93169F));
        PartDefinition group8_6 = group_1.addOrReplaceChild("group8", CubeListBuilder.create(), PartPose.offsetAndRotation(9.2375F, 7.0625F, 1.375F, 1.29794F, 0.28603F, 0.7895F));
        group8_6.addOrReplaceChild("cube_8", CubeListBuilder.create().texOffs(84, 20).addBox(-1.F, -1.F, -3.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.F, -5.F, 4.F, 0.43633F, 0.6545F, 0.F));
        group8_6.addOrReplaceChild("cube_9", CubeListBuilder.create().texOffs(84, 20).addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.44827F, -2.57738F, 2.28098F, -1.23015F, 1.20711F, -0.93169F));
        PartDefinition group9_7 = group_1.addOrReplaceChild("group9", CubeListBuilder.create(), PartPose.offsetAndRotation(4.7625F, 7.0625F, 1.375F, 1.29794F, -0.28603F, -0.7895F));
        group9_7.addOrReplaceChild("cube_10", CubeListBuilder.create().texOffs(84, 20).mirror().addBox(-1.F, -1.F, -3.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-6.F, -5.F, 4.F, 0.43633F, -0.6545F, 0.F));
        group9_7.addOrReplaceChild("cube_11", CubeListBuilder.create().texOffs(84, 20).mirror().addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-6.44827F, -2.57738F, 2.28098F, -1.23015F, -1.20711F, 0.93169F));
        PartDefinition group10_8 = group_1.addOrReplaceChild("group10", CubeListBuilder.create(), PartPose.offsetAndRotation(9.2375F, 7.0625F, 11.8F, -1.29794F, -0.28603F, 0.7895F));
        group10_8.addOrReplaceChild("cube_12", CubeListBuilder.create().texOffs(84, 20).addBox(-1.F, -1.F, -1.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.F, -5.F, -4.F, -0.43633F, -0.6545F, 0.F));
        group10_8.addOrReplaceChild("cube_13", CubeListBuilder.create().texOffs(84, 20).addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.44827F, -2.57738F, -2.28098F, 1.23015F, -1.20711F, -0.93169F));
        PartDefinition head_9 = body_0.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 40).addBox(-7.F, -5.F, -19.F, 14.F, 6.F, 19.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -3.F, -2.F, 0.87266F, 0.F, 0.F));
        head_9.addOrReplaceChild("cube_14", CubeListBuilder.create().texOffs(38, 65).addBox(-1.F, -2.F, -9.F, 2.F, 2.F, 13.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-7.F, -3.9F, -13.9875F, -1.5708F, 0.F, 0.F));
        head_9.addOrReplaceChild("cube_15", CubeListBuilder.create().texOffs(38, 65).mirror().addBox(-1.F, -2.F, -9.F, 2.F, 2.F, 13.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(7.F, -3.9F, -13.9875F, -1.5708F, 0.F, 0.F));
        head_9.addOrReplaceChild("cube_16", CubeListBuilder.create().texOffs(84, 0).mirror().addBox(-1.F, -2.F, -9.F, 2.F, 2.F, 8.8F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(7.F, 0.F, -12.0875F, -1.5708F, 0.F, 0.F));
        head_9.addOrReplaceChild("cube_17", CubeListBuilder.create().texOffs(84, 0).addBox(-1.F, -2.F, -9.F, 2.F, 2.F, 8.8F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-7.F, 0.F, -12.0875F, -1.5708F, 0.F, 0.F));
        PartDefinition right_ear_10 = head_9.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(84, 10).addBox(-6.F, 0.F, -3.F, 6.F, 1.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-7.F, -5.F, -2.F, 0.F, 0.F, -0.87266F));
        PartDefinition left_ear_11 = head_9.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(84, 15).addBox(0.F, 0.F, -3.F, 6.F, 1.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(7.F, -5.F, -2.F, 0.F, 0.F, 0.87266F));
        PartDefinition leg_back_right_12 = root.addOrReplaceChild("leg_back_right", CubeListBuilder.create().texOffs(38, 80).addBox(-14.F, -3.F, -4.F, 5.F, 11.F, 5.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.F, 16.F, 17.F, 0.F, 0.F, 0.F));
        PartDefinition leg_back_left_13 = root.addOrReplaceChild("leg_back_left", CubeListBuilder.create().texOffs(58, 80).addBox(9.F, -3.F, -4.F, 5.F, 11.F, 5.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, 16.F, 17.F, 0.F, 0.F, 0.F));
        PartDefinition leg_front_right_14 = root.addOrReplaceChild("leg_front_right", CubeListBuilder.create().texOffs(66, 40).addBox(-2.F, -2.F, -3.F, 6.F, 14.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, 12.F, -3.F, 0.F, 0.F, 0.F));
        PartDefinition leg_front_left_15 = root.addOrReplaceChild("leg_front_left", CubeListBuilder.create().texOffs(68, 60).addBox(-4.F, -2.F, -3.F, 6.F, 14.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.F, 12.F, -3.F, 0.F, 0.F, 0.F));
        PartDefinition group5_16 = root.addOrReplaceChild("group5", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.F, 3.F, 0.4125F, 0.F, 0.F, 0.F));
        group5_16.addOrReplaceChild("cube_18", CubeListBuilder.create().texOffs(78, 80).mirror().addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(6.F, -4.F, -4.F, -0.45476F, -0.27602F, 0.13247F));
        group5_16.addOrReplaceChild("cube_19", CubeListBuilder.create().texOffs(78, 80).mirror().addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(6.F, -4.F, -1.F, -0.91546F, -0.80239F, 0.50343F));
        PartDefinition group2_17 = root.addOrReplaceChild("group2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.F, 3.F, 0.4125F, 0.F, 0.F, 0.F));
        group2_17.addOrReplaceChild("cube_20", CubeListBuilder.create().texOffs(78, 80).addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, -4.F, -4.F, -0.45476F, 0.27602F, -0.13247F));
        group2_17.addOrReplaceChild("cube_21", CubeListBuilder.create().texOffs(78, 80).addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, -4.F, -1.F, -0.91546F, 0.80239F, -0.50343F));
        PartDefinition group6_18 = root.addOrReplaceChild("group6", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.F, 3.F, 0.4125F, 0.F, 0.F, -0.74176F));
        group6_18.addOrReplaceChild("cube_22", CubeListBuilder.create().texOffs(78, 80).addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, -4.F, -4.F, -0.45476F, 0.27602F, -0.13247F));
        group6_18.addOrReplaceChild("cube_23", CubeListBuilder.create().texOffs(78, 80).addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, -4.F, -1.F, -0.91546F, 0.80239F, -0.50343F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    private static ModelPart findBone(ModelPart part, String... path)
    {
        ModelPart current = part;
        for (String name : path) {
            current = current.getChild(name);
        }
        return current;
    }

    @Override
    public ModelPart root()
    {
        return root;
    }

    @Override
    public void setupAnim(PlagueHoglinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        backRight.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        frontLeft.xRot = backRight.xRot;
        backLeft.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.2F * limbSwingAmount;
        frontRight.xRot = backLeft.xRot;
    }
}
