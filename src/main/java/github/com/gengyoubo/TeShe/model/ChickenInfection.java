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
import net.minecraft.world.entity.animal.Chicken;

public class ChickenInfection<T extends Chicken> extends HierarchicalModel<T>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "chicken_infection"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart wing0;
    private final ModelPart wing1;

    public ChickenInfection(ModelPart root)
    {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.wing0 = root.getChild("wing0");
        this.wing1 = root.getChild("wing1");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 9)
                .addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, Mth.HALF_PI, 0.0F, 0.0F));

        PartDefinition group = body.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.ZERO);
        group.addOrReplaceChild("cube_r1", CubeListBuilder.create()
                .texOffs(0, 23)
                .addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 4.0F, -0.2967F, 0.544F, -0.7209F));
        group.addOrReplaceChild("cube_r2", CubeListBuilder.create()
                .texOffs(0, 23)
                .addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 4.0F, -0.829F, 0.0F, 0.0F));
        group.addOrReplaceChild("cube_r3", CubeListBuilder.create()
                .texOffs(18, 8)
                .addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, 5.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));
        head.addOrReplaceChild("comb", CubeListBuilder.create()
                .texOffs(14, 4)
                .addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
        head.addOrReplaceChild("beak", CubeListBuilder.create()
                .texOffs(14, 0)
                .addBox(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

        partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create()
                .texOffs(26, 0)
                .addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 19.0F, 1.0F));
        partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create()
                .texOffs(26, 0)
                .addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 19.0F, 1.0F));
        partdefinition.addOrReplaceChild("wing0", CubeListBuilder.create()
                .texOffs(24, 13)
                .addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 13.0F, 0.0F));
        partdefinition.addOrReplaceChild("wing1", CubeListBuilder.create()
                .texOffs(24, 13)
                .addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 13.0F, 0.0F));

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
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        leg0.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leg1.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount;
        wing0.zRot = ageInTicks;
        wing1.zRot = -ageInTicks;
    }
}
