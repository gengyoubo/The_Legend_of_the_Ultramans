package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueGuardianEntity;
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

public class GuardianPlagueSymbiosis extends HierarchicalModel<PlagueGuardianEntity>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "guardian_plague_symbiosis"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart tail0;
    private final ModelPart tail1;
    private final ModelPart tail2;

    public GuardianPlagueSymbiosis(ModelPart root)
    {
        this.root = root.getChild("root");
        this.head = findBone(this.root, "head");
        this.tail0 = findBone(this.root, "head", "tailpart0");
        this.tail1 = findBone(this.root, "head", "tailpart0", "tailpart1");
        this.tail2 = findBone(this.root, "head", "tailpart0", "tailpart1", "tailpart2");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition head_0 = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.F, -14.F, -8.F, 12.F, 12.F, 16.F, new CubeDeformation(0.F)).texOffs(0, 28).addBox(-8.F, -14.F, -6.F, 2.F, 12.F, 12.F, new CubeDeformation(0.F)).texOffs(0, 28).addBox(6.F, -14.F, -6.F, 2.F, 12.F, 12.F, new CubeDeformation(0.F)).texOffs(16, 40).addBox(-6.F, -16.F, -6.F, 12.F, 2.F, 12.F, new CubeDeformation(0.F)).texOffs(16, 40).addBox(-6.F, -2.F, -6.F, 12.F, 2.F, 12.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 24.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition eye_1 = head_0.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(8, 0).addBox(-1.F, 15.F, -8.25F, 2.F, 2.F, 1.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition tailpart0_2 = head_0.addOrReplaceChild("tailpart0", CubeListBuilder.create().texOffs(40, 0).addBox(-2.F, 14.F, 8.F, 4.F, 4.F, 8.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition tailpart1_3 = tailpart0_2.addOrReplaceChild("tailpart1", CubeListBuilder.create().texOffs(0, 54).addBox(-1.5F, 14.F, 16.F, 3.F, 3.F, 7.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition tailpart2_4 = tailpart1_3.addOrReplaceChild("tailpart2", CubeListBuilder.create().texOffs(41, 32).addBox(-1.F, 14.F, 23.F, 2.F, 2.F, 6.F, new CubeDeformation(0.F)).texOffs(25, 19).addBox(0.F, 10.5F, 26.F, 1.F, 9.F, 9.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition spikepart0_5 = head_0.addOrReplaceChild("spikepart0", CubeListBuilder.create().texOffs(0, 0).addBox(10.25F, -4.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 0.F, 0.F, 0.7854F));
        PartDefinition spikepart1_6 = head_0.addOrReplaceChild("spikepart1", CubeListBuilder.create().texOffs(0, 0).addBox(-12.25F, -4.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 0.F, 0.F, -0.7854F));
        PartDefinition spikepart2_7 = head_0.addOrReplaceChild("spikepart2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.F, -4.5F, -12.25F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 0.7854F, 0.F, 0.F));
        PartDefinition spikepart3_8 = head_0.addOrReplaceChild("spikepart3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.F, -4.5F, 10.5F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, -0.7854F, 0.F, 0.F));
        PartDefinition spikepart4_9 = head_0.addOrReplaceChild("spikepart4", CubeListBuilder.create().texOffs(0, 0).addBox(10.25F, -27.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 0.F, 0.F, 2.35619F));
        PartDefinition spikepart5_10 = head_0.addOrReplaceChild("spikepart5", CubeListBuilder.create().texOffs(0, 0).addBox(-12.25F, -27.5F, -1.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 0.F, 0.F, -2.35619F));
        PartDefinition spikepart6_11 = head_0.addOrReplaceChild("spikepart6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.F, -28.5F, -12.25F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 2.35619F, 0.F, 0.F));
        PartDefinition spikepart7_12 = head_0.addOrReplaceChild("spikepart7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.F, -27.5F, 10.25F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, -2.35619F, 0.F, 0.F));
        PartDefinition spikepart8_13 = head_0.addOrReplaceChild("spikepart8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.F, -17.5F, -17.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 1.5708F, -0.7854F, 0.F));
        PartDefinition spikepart9_14 = head_0.addOrReplaceChild("spikepart9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.F, -17.5F, -17.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 1.5708F, 0.7854F, 0.F));
        PartDefinition spikepart10_15 = head_0.addOrReplaceChild("spikepart10", CubeListBuilder.create().texOffs(0, 0).addBox(-1.F, -17.5F, -17.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 1.5708F, -2.35619F, 0.F));
        PartDefinition spikepart11_16 = head_0.addOrReplaceChild("spikepart11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.F, -17.5F, -17.F, 2.F, 9.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -24.F, 0.F, 1.5708F, 2.35619F, 0.F));
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
    public void setupAnim(PlagueGuardianEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        float tail = Mth.sin(ageInTicks * 0.15F);
        tail0.yRot = tail * 0.15F;
        tail1.yRot = tail * 0.25F;
        tail2.yRot = tail * 0.35F;
    }
}
