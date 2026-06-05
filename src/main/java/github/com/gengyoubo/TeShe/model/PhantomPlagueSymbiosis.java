package github.com.gengyoubo.TeShe.model;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlaguePhantomEntity;
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

public class PhantomPlagueSymbiosis extends HierarchicalModel<PlaguePhantomEntity>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "phantom_plague_symbiosis"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public PhantomPlagueSymbiosis(ModelPart root)
    {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.leftWing = this.body.getChild("wing1");
        this.rightWing = this.body.getChild("wing0");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition body_0 = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.F, -2.F, -8.F, 5.F, 3.F, 9.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 0.F, 0.F, 0.F, 0.F, 0.F));
        PartDefinition wing0_1 = body_0.addOrReplaceChild("wing0", CubeListBuilder.create().texOffs(23, 12).addBox(0.F, 0.F, 0.F, 6.F, 2.F, 9.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(2.F, -2.F, -8.F, 0.F, 0.F, 0.08727F));
        PartDefinition wingtip0_2 = wing0_1.addOrReplaceChild("wingtip0", CubeListBuilder.create().texOffs(16, 24).addBox(0.F, 0.F, 0.F, 13.F, 1.F, 9.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(6.F, 0.F, 0.F, 0.F, 0.F, 0.17453F));
        PartDefinition wing1_3 = body_0.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(23, 12).addBox(-6.F, 0.F, 0.F, 6.F, 2.F, 9.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-3.F, -2.F, -8.F, 0.F, 0.F, -0.08727F));
        PartDefinition wingtip1_4 = wing1_3.addOrReplaceChild("wingtip1", CubeListBuilder.create().texOffs(16, 24).addBox(-13.F, 0.F, 0.F, 13.F, 1.F, 9.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(-6.F, 0.F, 0.F, 0.F, 0.F, -0.17453F));
        PartDefinition head_5 = body_0.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.F, -2.F, -5.F, 7.F, 3.F, 5.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 1.F, -7.F, 0.F, 0.F, 0.F));
        PartDefinition tail_6 = body_0.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(3, 20).addBox(-2.F, 0.F, 0.F, 3.F, 2.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, -2.F, 1.F, -0.08727F, 0.F, 0.F));
        PartDefinition tailtip_7 = tail_6.addOrReplaceChild("tailtip", CubeListBuilder.create().texOffs(4, 29).addBox(-1.F, 0.F, 0.F, 1.F, 1.F, 6.F, new CubeDeformation(0.F)), PartPose.offsetAndRotation(0.F, 0.5F, 6.F, -0.08727F, 0.F, 0.F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root()
    {
        return root;
    }

    @Override
    public void setupAnim(PlaguePhantomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        float flap = Mth.sin(ageInTicks * 0.45F) * 0.35F;
        leftWing.zRot = flap;
        rightWing.zRot = -flap;
    }
}
