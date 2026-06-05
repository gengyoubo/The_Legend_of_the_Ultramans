package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.TesheChickenEntity;
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

public class ChickenPlagueSymbiosis extends HierarchicalModel<TesheChickenEntity>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "chicken_plague_symbiosis"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart wing0;
    private final ModelPart wing1;

    public ChickenPlagueSymbiosis(ModelPart root)
    {
        this.root = root.getChild("root");
        this.head = findBone(this.root, "head");
        this.leg0 = findBone(this.root, "leg0");
        this.leg1 = findBone(this.root, "leg1");
        this.wing0 = findBone(this.root, "wing0");
        this.wing1 = findBone(this.root, "wing1");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition body_0 = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-3.F, -4.F, -3.F, 6.F, 8.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 16.F, 0.F, 1.5708F, 0.F, 0.F));
        PartDefinition group_1 = body_0.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        group_1.addOrReplaceChild("cube_0", CubeListBuilder.create().texOffs(18, 8).addBox(-1.F, -1.F, -3.F, 2.F, 2.F, 4.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-3.F, -1.F, 5.F, 0.F, -0.5236F, 0.F));
        group_1.addOrReplaceChild("cube_1", CubeListBuilder.create().texOffs(0, 23).addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 2.F, 4.F, -0.82903F, 0.F, 0.F));
        group_1.addOrReplaceChild("cube_2", CubeListBuilder.create().texOffs(0, 23).addBox(-1.F, -1.F, -2.F, 2.F, 2.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -1.F, 4.F, -0.29673F, 0.54396F, -0.7209F));
        PartDefinition head_2 = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.F, -6.F, -2.F, 4.F, 6.F, 3.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 15.F, -4.F, 0.F, 0.F, 0.F));
        PartDefinition comb_3 = head_2.addOrReplaceChild("comb", CubeListBuilder.create().texOffs(14, 4).addBox(-1.F, -2.F, -3.F, 2.F, 2.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition beak_4 = head_2.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 0).addBox(-2.F, -4.F, -4.F, 4.F, 2.F, 2.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition leg0_5 = root.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(26, 0).addBox(-1.F, 0.F, -3.F, 3.F, 5.F, 3.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-2.F, 19.F, 1.F, 0.F, 0.F, 0.F));
        PartDefinition leg1_6 = root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(26, 0).addBox(-1.F, 0.F, -3.F, 3.F, 5.F, 3.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(1.F, 19.F, 1.F, 0.F, 0.F, 0.F));
        PartDefinition wing0_7 = root.addOrReplaceChild("wing0", CubeListBuilder.create().texOffs(24, 13).addBox(-1.F, 0.F, -3.F, 1.F, 4.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-3.F, 13.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition wing1_8 = root.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(24, 13).addBox(0.F, 0.F, -3.F, 1.F, 4.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(3.F, 13.F, 0.F, 0.F, 0.F, 0.F));
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
    public void setupAnim(TesheChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        leg0.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leg1.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount;
        float wingFlap = Mth.sin(ageInTicks * 0.35F) * 0.35F;
        wing0.zRot = wingFlap;
        wing1.zRot = -wingFlap;
    }
}
