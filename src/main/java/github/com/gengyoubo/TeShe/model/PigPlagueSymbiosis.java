package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlaguePigEntity;
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

public class PigPlagueSymbiosis extends HierarchicalModel<PlaguePigEntity>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "pig_plague_symbiosis"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;

    public PigPlagueSymbiosis(ModelPart root)
    {
        this.root = root.getChild("root");
        this.head = findBone(this.root, "head");
        this.leg0 = findBone(this.root, "leg0");
        this.leg1 = findBone(this.root, "leg1");
        this.leg2 = findBone(this.root, "leg2");
        this.leg3 = findBone(this.root, "leg3");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition body_0 = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-5.F, -10.F, -7.F, 10.F, 16.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 11.F, 2.F, 1.5708F, 0.F, 0.F));
        PartDefinition group6_1 = body_0.addOrReplaceChild("group6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, -2.F, -2.2F, 0.F, 0.F, 0.F));
        PartDefinition group_2 = group6_1.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        group_2.addOrReplaceChild("cube_0", CubeListBuilder.create().texOffs(16, 20).addBox(-1.F, -1.F, -3.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.F, -5.F, 4.F, 0.43633F, 0.6545F, 0.F));
        group_2.addOrReplaceChild("cube_1", CubeListBuilder.create().texOffs(16, 20).addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.44827F, -2.57738F, 2.28098F, -1.23015F, 1.20711F, -0.93169F));
        PartDefinition group2_3 = group6_1.addOrReplaceChild("group2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.4F, 0.F, -1.1F, 0.F, 0.F, 0.F));
        group2_3.addOrReplaceChild("cube_2", CubeListBuilder.create().texOffs(16, 20).mirror().addBox(-1.F, -1.F, -1.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-6.F, -5.F, -4.F, -0.43633F, 0.6545F, 0.F));
        group2_3.addOrReplaceChild("cube_3", CubeListBuilder.create().texOffs(16, 20).mirror().addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-6.44827F, -2.57738F, -2.28098F, 1.23015F, 1.20711F, 0.93169F));
        PartDefinition group3_4 = group6_1.addOrReplaceChild("group3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, 9.1F, 0.F, 0.F, 0.F, 0.F));
        group3_4.addOrReplaceChild("cube_4", CubeListBuilder.create().texOffs(16, 20).mirror().addBox(-1.F, -1.F, -3.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-6.F, -5.F, 4.F, 0.43633F, -0.6545F, 0.F));
        group3_4.addOrReplaceChild("cube_5", CubeListBuilder.create().texOffs(16, 20).mirror().addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-6.44827F, -2.57738F, 2.28098F, -1.23015F, -1.20711F, 0.93169F));
        PartDefinition group4_5 = group6_1.addOrReplaceChild("group4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, 0.F, 0.4F, 0.F, 0.F, 0.F));
        group4_5.addOrReplaceChild("cube_6", CubeListBuilder.create().texOffs(0, 26).addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, -4.F, 4.F, 0.45476F, -0.27602F, -0.13247F));
        group4_5.addOrReplaceChild("cube_7", CubeListBuilder.create().texOffs(0, 26).addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, -4.F, 1.F, 0.91546F, -0.80239F, -0.50343F));
        PartDefinition group5_6 = group6_1.addOrReplaceChild("group5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, -2.F, -1.8F, 0.F, 0.F, 0.F));
        group5_6.addOrReplaceChild("cube_8", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(6.F, -4.F, -4.F, -0.45476F, -0.27602F, 0.13247F));
        group5_6.addOrReplaceChild("cube_9", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-1.F, -2.F, -4.F, 2.F, 2.F, 8.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(6.F, -4.F, -1.F, -0.91546F, -0.80239F, 0.50343F));
        PartDefinition head_7 = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.F, -4.F, -8.F, 8.F, 8.F, 8.F, new CubeDeformation(0.F)).texOffs(16, 16).addBox(-2.F, 0.F, -9.F, 4.F, 3.F, 1.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 12.F, -6.F, 0.F, 0.F, 0.F));
        PartDefinition leg0_8 = root.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(0, 16).addBox(-2.F, 0.F, -2.F, 4.F, 6.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-3.F, 18.F, 7.F, 0.F, 0.F, 0.F));
        PartDefinition leg1_9 = root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.F, 0.F, -2.F, 4.F, 6.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(3.F, 18.F, 7.F, 0.F, 0.F, 0.F));
        PartDefinition leg2_10 = root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.F, 0.F, -2.F, 4.F, 6.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-3.F, 18.F, -5.F, 0.F, 0.F, 0.F));
        PartDefinition leg3_11 = root.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 16).addBox(-2.F, 0.F, -2.F, 4.F, 6.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(3.F, 18.F, -5.F, 0.F, 0.F, 0.F));
        return LayerDefinition.create(meshdefinition, 64, 64);
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
    public void setupAnim(PlaguePigEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        leg0.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leg1.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount;
        leg2.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount;
        leg3.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
