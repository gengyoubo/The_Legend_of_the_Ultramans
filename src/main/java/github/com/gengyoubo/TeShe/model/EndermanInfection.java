package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
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
import net.minecraft.world.entity.monster.EnderMan;

public class EndermanInfection<T extends EnderMan> extends HierarchicalModel<T>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "enderman_infection"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public EndermanInfection(ModelPart root)
    {
        this.root = root;
        this.head = root.getChild("Head");
        this.body = root.getChild("Body");
        this.rightArm = root.getChild("RightArm");
        this.leftArm = root.getChild("LeftArm");
        this.rightLeg = root.getChild("RightLeg");
        this.leftLeg = root.getChild("LeftLeg");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -24.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 16)
                .addBox(-4.0F, -22.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.ZERO);

        PartDefinition body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create()
                .texOffs(32, 16)
                .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.0F));
        PartDefinition group = body.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.ZERO);
        group.addOrReplaceChild("cube_r1", CubeListBuilder.create()
                .texOffs(38, 32)
                .addBox(-1.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1727F, 7.0019F, 2.4947F, -1.6643F, 0.1122F, -0.6713F));
        group.addOrReplaceChild("cube_r2", CubeListBuilder.create()
                .texOffs(30, 32)
                .addBox(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 5.0F, 2.0F, -1.3305F, 0.9903F, -0.496F));
        group.addOrReplaceChild("cube_r3", CubeListBuilder.create()
                .texOffs(30, 32)
                .addBox(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 2.0F, -0.9163F, 0.0F, -0.6981F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create()
                .texOffs(56, 0)
                .addBox(-3.0F, -2.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -12.0F, 0.0F));
        rightArm.addOrReplaceChild("group2", CubeListBuilder.create()
                .texOffs(32, 0)
                .addBox(-1.5F, -3.0F, -5.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32)
                .addBox(-1.5F, 0.0F, -5.0F, 3.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.2F, 25.6F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create()
                .texOffs(56, 0)
                .mirror()
                .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F))
                .mirror(false), PartPose.offset(5.0F, -12.0F, 0.0F));
        leftArm.addOrReplaceChild("group3", CubeListBuilder.create()
                .texOffs(32, 0)
                .mirror()
                .addBox(-1.5F, -3.0F, -5.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .mirror(false)
                .texOffs(0, 32)
                .mirror()
                .addBox(-1.5F, 0.0F, -5.0F, 3.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .mirror(false), PartPose.offset(0.2F, 25.6F, 0.0F));

        partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create()
                .texOffs(56, 0)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -2.0F, 0.0F));
        partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create()
                .texOffs(56, 0)
                .mirror()
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F))
                .mirror(false), PartPose.offset(2.0F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root()
    {
        return root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        head.xRot = headPitch * Mth.DEG_TO_RAD;

        float walk = Mth.clamp(limbSwingAmount, 0.0F, 0.5F);
        rightArm.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * walk;
        leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * walk;
        rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * walk;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * walk;

        rightArm.zRot = 0.0F;
        leftArm.zRot = 0.0F;
        rightLeg.zRot = 0.0F;
        leftLeg.zRot = 0.0F;

        if (entity.isCreepy()) {
            rightArm.xRot = -1.5F;
            leftArm.xRot = -1.5F;
        }
    }
}
