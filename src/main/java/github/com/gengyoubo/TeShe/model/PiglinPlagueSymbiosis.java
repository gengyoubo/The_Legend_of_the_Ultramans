package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlaguePiglinEntity;
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

public class PiglinPlagueSymbiosis extends HierarchicalModel<PlaguePiglinEntity>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "piglin_plague_symbiosis"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public PiglinPlagueSymbiosis(ModelPart root)
    {
        this.root = root.getChild("root");
        this.head = findBone(this.root, "head");
        this.rightArm = findBone(this.root, "RightArm");
        this.leftArm = findBone(this.root, "LeftArm");
        this.rightLeg = findBone(this.root, "RightLeg");
        this.leftLeg = findBone(this.root, "LeftLeg");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition Body_0 = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.F, 0.F, -2.F, 8.F, 12.F, 4.F, new CubeDeformation(0.F)).texOffs(24, 16).addBox(-4.F, 0.F, -2.F, 8.F, 12.F, 4.F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition head_1 = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.F, -8.F, -4.F, 10.F, 8.F, 8.F, new CubeDeformation(-0.02F)).texOffs(52, 9).addBox(-2.F, -4.F, -5.F, 4.F, 4.F, 1.F, new CubeDeformation(0.F)).texOffs(56, 32).addBox(2.F, -2.F, -5.F, 1.F, 2.F, 1.F, new CubeDeformation(0.F)).texOffs(56, 35).addBox(-3.F, -2.F, -5.F, 1.F, 2.F, 1.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition leftear_2 = head_1.addOrReplaceChild("leftear", CubeListBuilder.create().texOffs(48, 44).addBox(-1.F, 0.F, -2.F, 1.F, 5.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(5.F, -6.F, 0.F, 0.F, 0.F, -0.5236F));
        PartDefinition rightear_3 = head_1.addOrReplaceChild("rightear", CubeListBuilder.create().texOffs(52, 0).addBox(0.F, 0.F, -2.F, 1.F, 5.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-5.F, -6.F, 0.F, 0.F, 0.F, 0.5236F));
        PartDefinition group5_4 = head_1.addOrReplaceChild("group5", CubeListBuilder.create(), PartPose.offsetAndRotation(3.65F, -9.25F, -1.8F, 0.F, 1.5708F, 0.F));
        group5_4.addOrReplaceChild("cube_0", CubeListBuilder.create().texOffs(48, 32).addBox(-1.F, -4.5F, -1.F, 2.F, 10.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-2.F, 0.75F, 1.4F, -0.96711F, -0.32864F, 0.34824F));
        group5_4.addOrReplaceChild("cube_1", CubeListBuilder.create().texOffs(48, 32).addBox(-1.F, -4.5F, -1.F, 2.F, 10.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(1.5F, 1.85F, -0.1F, -0.55575F, -0.04779F, 0.81585F));
        PartDefinition group6_5 = head_1.addOrReplaceChild("group6", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.25F, -10.4F, 0.85F, 0.F, 0.F, 0.F));
        group6_5.addOrReplaceChild("cube_2", CubeListBuilder.create().texOffs(48, 53).addBox(-1.F, -4.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 3.4F, 3.25F, -1.00924F, -0.14559F, -0.27938F));
        group6_5.addOrReplaceChild("cube_3", CubeListBuilder.create().texOffs(48, 53).addBox(-1.F, -4.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 2.4F, -0.65F, 0.11989F, -0.19966F, -0.94404F));
        PartDefinition RightArm_6 = root.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 32).addBox(-3.F, -2.F, -2.F, 4.F, 12.F, 4.F, new CubeDeformation(0.F)).texOffs(16, 32).addBox(-3.F, -2.F, -2.F, 4.F, 12.F, 4.F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.F, 2.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition group2_7 = RightArm_6.addOrReplaceChild("group2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.85F, 0.2F, 0.85F, 0.F, 0.F, 0.F));
        group2_7.addOrReplaceChild("cube_4", CubeListBuilder.create().texOffs(48, 53).addBox(-1.F, -4.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -2.4F, 0.65F, -0.46819F, -0.14559F, -0.27938F));
        group2_7.addOrReplaceChild("cube_5", CubeListBuilder.create().texOffs(48, 53).addBox(-1.F, -4.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 2.4F, -0.65F, -0.23579F, -0.22846F, -0.58794F));
        PartDefinition group3_8 = RightArm_6.addOrReplaceChild("group3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.05F, -2.4F, -2.55F, 0.F, -1.5708F, 0.F));
        group3_8.addOrReplaceChild("cube_6", CubeListBuilder.create().texOffs(48, 53).addBox(-1.F, -4.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -2.4F, 0.65F, -0.46819F, -0.14559F, -0.27938F));
        group3_8.addOrReplaceChild("cube_7", CubeListBuilder.create().texOffs(48, 53).addBox(-1.F, -4.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 2.4F, -0.65F, -0.23579F, -0.22846F, -0.58794F));
        PartDefinition LeftArm_9 = root.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 32).addBox(-1.F, -2.F, -2.F, 4.F, 12.F, 4.F, new CubeDeformation(0.F)).texOffs(36, 0).addBox(-1.F, -2.F, -2.F, 4.F, 12.F, 4.F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.F, 2.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition group_10 = LeftArm_9.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offsetAndRotation(2.45F, 2.95F, 0.1F, 0.F, 0.F, 0.F));
        group_10.addOrReplaceChild("cube_8", CubeListBuilder.create().texOffs(48, 32).addBox(-1.F, -4.5F, -1.F, 2.F, 10.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -1.85F, 1.4F, -0.5236F, 0.F, 0.56723F));
        group_10.addOrReplaceChild("cube_9", CubeListBuilder.create().texOffs(48, 32).addBox(-1.F, -4.5F, -1.F, 2.F, 10.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 1.85F, -0.9F, 0.18718F, 0.04294F, 0.34437F));
        PartDefinition group4_11 = LeftArm_9.addOrReplaceChild("group4", CubeListBuilder.create(), PartPose.offsetAndRotation(1.65F, 0.75F, -1.8F, 0.F, 1.5708F, 0.F));
        group4_11.addOrReplaceChild("cube_10", CubeListBuilder.create().texOffs(48, 32).addBox(-1.F, -4.5F, -1.F, 2.F, 10.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -1.85F, 1.4F, -0.5236F, 0.F, 0.56723F));
        group4_11.addOrReplaceChild("cube_11", CubeListBuilder.create().texOffs(48, 32).addBox(-1.F, -4.5F, -1.F, 2.F, 10.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 1.85F, -0.9F, 0.18718F, 0.04294F, 0.34437F));
        PartDefinition RightLeg_12 = root.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.1F, 0.F, -2.F, 4.F, 12.F, 4.F, new CubeDeformation(0.F)).texOffs(16, 48).addBox(-2.1F, 0.F, -2.F, 4.F, 12.F, 4.F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-1.9F, 12.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition LeftLeg_13 = root.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(48, 16).addBox(-1.9F, 0.F, -2.F, 4.F, 12.F, 4.F, new CubeDeformation(0.F)).texOffs(32, 48).addBox(-1.9F, 0.F, -2.F, 4.F, 12.F, 4.F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.9F, 12.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition leftItem_14 = root.addOrReplaceChild("leftItem", CubeListBuilder.create(), PartPose.offsetAndRotation(6.F, 9.F, 1.F, 0.F, 0.F, 0.F));
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
    public void setupAnim(PlaguePiglinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        rightArm.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.2F * limbSwingAmount;
        leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.2F * limbSwingAmount;
    }
}
