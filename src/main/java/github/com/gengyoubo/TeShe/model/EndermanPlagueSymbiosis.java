package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueEndermanEntity;
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

public class EndermanPlagueSymbiosis extends HierarchicalModel<PlagueEndermanEntity>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "enderman_plague_symbiosis"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public EndermanPlagueSymbiosis(ModelPart root)
    {
        this.root = root;
        this.head = findBone(this.root, "Head");
        this.rightArm = findBone(this.root, "RightArm");
        this.leftArm = findBone(this.root, "LeftArm");
        this.rightLeg = findBone(this.root, "RightLeg");
        this.leftLeg = findBone(this.root, "LeftLeg");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Head_0 = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.F, -24.F, -4.F, 8.F, 8.F, 8.F, new CubeDeformation(-0.5F)).texOffs(0, 16).addBox(-4.F, -22.F, -4.F, 8.F, 8.F, 8.F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition Body_1 = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 16).addBox(-4.F, 0.F, -2.F, 8.F, 12.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -14.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition group_2 = Body_1.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        group_2.addOrReplaceChild("cube_0", CubeListBuilder.create().texOffs(30, 32).addBox(-1.F, -4.F, -1.F, 2.F, 8.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-1.F, 5.F, 2.F, -0.9163F, 0.F, -0.69813F));
        group_2.addOrReplaceChild("cube_1", CubeListBuilder.create().texOffs(30, 32).addBox(-1.F, -4.F, -1.F, 2.F, 8.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.1F, 5.F, 2.F, -1.3305F, 0.99027F, -0.49604F));
        group_2.addOrReplaceChild("cube_2", CubeListBuilder.create().texOffs(38, 32).addBox(-1.F, -3.5F, -1.F, 2.F, 7.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.1727F, 7.00187F, 2.49468F, -1.66431F, 0.11225F, -0.67134F));
        PartDefinition RightArm_3 = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(56, 0).addBox(-3.F, -2.F, -1.F, 2.F, 30.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-3.F, -12.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition group2_4 = RightArm_3.addOrReplaceChild("group2", CubeListBuilder.create().texOffs(32, 0).addBox(-1.5F, -3.F, -5.F, 3.F, 3.F, 8.F, new CubeDeformation(0.F)).texOffs(0, 32).addBox(-1.5F, 0.F, -5.F, 3.F, 3.F, 12.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-2.2F, 25.6F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition LeftArm_5 = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(56, 0).addBox(-1.F, -2.F, -1.F, 2.F, 30.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(5.F, -12.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition group3_6 = LeftArm_5.addOrReplaceChild("group3", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1.5F, -3.F, -5.F, 3.F, 3.F, 8.F, new CubeDeformation(0.F)).mirror(false).texOffs(0, 32).mirror().addBox(-1.5F, 0.F, -5.F, 3.F, 3.F, 12.F, new CubeDeformation(0.F)).mirror(false), PartPose.offsetAndRotation(0.2F, 25.6F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition RightLeg_7 = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(56, 0).addBox(-1.F, 0.F, -1.F, 2.F, 30.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-2.F, -2.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition LeftLeg_8 = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 0).addBox(-1.F, 0.F, -1.F, 2.F, 30.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(2.F, -2.F, 0.F, 0.F, 0.F, 0.F));
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
    public void setupAnim(PlagueEndermanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        root.getAllParts().forEach(ModelPart::resetPose);
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        float walk = Math.min(limbSwingAmount, 1.0F);
        rightArm.xRot = Mth.clamp(Mth.cos(limbSwing * 0.6662F + Mth.PI) * walk, -0.4F, 0.4F);
        leftArm.xRot = Mth.clamp(Mth.cos(limbSwing * 0.6662F) * walk, -0.4F, 0.4F);
        rightLeg.xRot = Mth.clamp(Mth.cos(limbSwing * 0.6662F) * walk, -0.4F, 0.4F);
        leftLeg.xRot = Mth.clamp(Mth.cos(limbSwing * 0.6662F + Mth.PI) * walk, -0.4F, 0.4F);

        if (entity.getCarriedBlock() != null) {
            rightArm.xRot = -0.5F;
            leftArm.xRot = -0.5F;
            rightArm.zRot = 0.05F;
            leftArm.zRot = -0.05F;
        }

        if (entity.isCreepy()) {
            head.y -= 5.0F;
        }
    }
}
