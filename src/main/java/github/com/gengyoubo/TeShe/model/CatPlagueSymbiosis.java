package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueCatEntity;
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

public class CatPlagueSymbiosis extends HierarchicalModel<PlagueCatEntity>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "cat_plague_symbiosis"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart backLegL;
    private final ModelPart backLegR;
    private final ModelPart frontLegL;
    private final ModelPart frontLegR;
    private final ModelPart tail1;
    private final ModelPart tail2;

    public CatPlagueSymbiosis(ModelPart root)
    {
        this.root = root.getChild("root");
        this.head = findBone(this.root, "body", "head");
        this.backLegL = findBone(this.root, "body", "backLegL");
        this.backLegR = findBone(this.root, "body", "backLegR");
        this.frontLegL = findBone(this.root, "body", "frontLegL");
        this.frontLegR = findBone(this.root, "body", "frontLegR");
        this.tail1 = findBone(this.root, "body", "tail1");
        this.tail2 = findBone(this.root, "body", "tail1", "tail2");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition body_0 = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, 17.F, 1.F, 0.F, 0.F, 0.F));
        PartDefinition belly_1 = body_0.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(20, 0).addBox(-2.F, -8.F, -3.F, 4.F, 16.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 1.5708F, 0.F, 0.F));
        PartDefinition group_2 = belly_1.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        group_2.addOrReplaceChild("cube_0", CubeListBuilder.create().texOffs(10, 22).addBox(-1.F, -1.F, -2.6F, 2.F, 2.F, 5.1F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -5.84805F, 4.52992F, -0.55851F, 0.F, 0.F));
        group_2.addOrReplaceChild("cube_1", CubeListBuilder.create().texOffs(10, 22).addBox(-1.F, -1.F, -2.6F, 2.F, 2.F, 5.1F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -1.34805F, 4.52992F, -0.55851F, 0.F, 0.F));
        group_2.addOrReplaceChild("cube_2", CubeListBuilder.create().texOffs(10, 22).addBox(-1.F, -1.F, -2.6F, 2.F, 2.F, 5.1F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 4.45195F, 4.52992F, -0.55851F, 0.F, 0.F));
        group_2.addOrReplaceChild("cube_3", CubeListBuilder.create().texOffs(24, 22).addBox(-2.2F, -2.F, -1.F, 4.7F, 2.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(2.4F, 0.F, 0.F, -0.09665F, -0.42617F, 0.23037F));
        group_2.addOrReplaceChild("cube_4", CubeListBuilder.create().texOffs(24, 22).addBox(-2.2F, -2.F, -1.F, 4.7F, 2.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(2.4F, 4.3F, 1.1F, -0.09665F, -0.42617F, 0.23037F));
        group_2.addOrReplaceChild("cube_5", CubeListBuilder.create().texOffs(24, 22).addBox(-2.2F, -2.F, -1.F, 4.7F, 2.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(2.4F, -4.3F, 1.1F, -0.09665F, -0.42617F, 0.23037F));
        group_2.addOrReplaceChild("cube_6", CubeListBuilder.create().texOffs(24, 22).mirror().addBox(-2.5F, -2.F, -1.F, 4.7F, 2.F, 2.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-2.4F, 4.3F, 1.1F, -0.09665F, 0.42617F, -0.23037F));
        group_2.addOrReplaceChild("cube_7", CubeListBuilder.create().texOffs(24, 22).mirror().addBox(-2.5F, -2.F, -1.F, 4.7F, 2.F, 2.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-2.4F, 0.F, 0.F, -0.09665F, 0.42617F, -0.23037F));
        group_2.addOrReplaceChild("cube_8", CubeListBuilder.create().texOffs(24, 22).mirror().addBox(-2.5F, -2.F, -1.F, 4.7F, 2.F, 2.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(-2.4F, -4.3F, 1.1F, -0.09665F, 0.42617F, -0.23037F));
        PartDefinition head_3 = body_0.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.F, -3.F, 5.F, 4.F, 5.F, new CubeDeformation(0.F)).texOffs(0, 24).addBox(-1.5F, -0.01562F, -4.F, 3.F, 2.F, 2.F, new CubeDeformation(0.F)).texOffs(0, 10).addBox(-2.F, -3.F, 0.F, 1.F, 1.F, 2.F, new CubeDeformation(0.F)).texOffs(6, 10).addBox(1.F, -3.F, 0.F, 1.F, 1.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -2.F, -10.F, 0.F, 0.F, 0.F));
        PartDefinition group2_4 = head_3.addOrReplaceChild("group2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        group2_4.addOrReplaceChild("cube_9", CubeListBuilder.create().texOffs(15, 0).addBox(-1.F, -3.1F, -1.F, 2.F, 3.9F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -1.7F, 0.F, -0.5236F, 0.F, 0.F));
        PartDefinition tail1_5 = body_0.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.F, 0.F, 1.F, 8.F, 1.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -2.F, 7.F, 0.7854F, 0.F, 0.F));
        PartDefinition tail2_6 = tail1_5.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(4, 15).addBox(-0.5F, 0.F, 0.F, 1.F, 8.F, 1.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 8.F, 0.F, 0.7854F, 0.F, 0.F));
        PartDefinition backLegL_7 = body_0.addOrReplaceChild("backLegL", CubeListBuilder.create().texOffs(8, 13).addBox(-1.F, 0.F, -1.F, 2.F, 6.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(1.1F, 1.F, 6.F, 0.F, 0.F, 0.F));
        PartDefinition backLegR_8 = body_0.addOrReplaceChild("backLegR", CubeListBuilder.create().texOffs(8, 13).addBox(-1.F, 0.F, -1.F, 2.F, 6.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-1.1F, 1.F, 6.F, 0.F, 0.F, 0.F));
        PartDefinition frontLegL_9 = body_0.addOrReplaceChild("frontLegL", CubeListBuilder.create().texOffs(40, 0).addBox(-1.F, -0.2F, -1.F, 2.F, 10.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(1.2F, -3.F, -5.F, 0.F, 0.F, 0.F));
        PartDefinition frontLegR_10 = body_0.addOrReplaceChild("frontLegR", CubeListBuilder.create().texOffs(40, 0).addBox(-1.F, -0.2F, -1.F, 2.F, 10.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-1.2F, -3.F, -5.F, 0.F, 0.F, 0.F));
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
    public void setupAnim(PlagueCatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        backLegL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        frontLegR.xRot = backLegL.xRot;
        backLegR.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.2F * limbSwingAmount;
        frontLegL.xRot = backLegR.xRot;
        tail1.yRot = Mth.sin(ageInTicks * 0.08F) * 0.12F;
        tail2.yRot = Mth.sin(ageInTicks * 0.08F + 0.6F) * 0.16F;
    }
}
