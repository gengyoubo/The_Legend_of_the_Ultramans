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
import net.minecraft.world.entity.monster.ElderGuardian;

public class Guardian_elderInfection<T extends ElderGuardian> extends HierarchicalModel<T>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "guardian_elder_infection"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart tailpart0;
    private final ModelPart tailpart1;
    private final ModelPart tailpart2;

    public Guardian_elderInfection(ModelPart root)
    {
        this.root = root;
        this.head = root.getChild("head");
        this.tailpart0 = head.getChild("tailpart0");
        this.tailpart1 = tailpart0.getChild("tailpart1");
        this.tailpart2 = tailpart1.getChild("tailpart2");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-6.0F, -14.0F, -8.0F, 12.0F, 12.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28)
                .addBox(-8.0F, -14.0F, -6.0F, 2.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28)
                .mirror()
                .addBox(6.0F, -14.0F, -6.0F, 2.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
                .mirror(false)
                .texOffs(16, 40)
                .mirror()
                .addBox(-6.0F, -16.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .mirror(false)
                .texOffs(16, 40)
                .mirror()
                .addBox(-6.0F, -2.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

        head.addOrReplaceChild("eye", CubeListBuilder.create()
                .texOffs(8, 0)
                .addBox(-1.0F, 15.0F, -8.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition tailpart0 = head.addOrReplaceChild("tailpart0", CubeListBuilder.create()
                .texOffs(40, 0)
                .addBox(-2.0F, 14.0F, 8.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));
        PartDefinition tailpart1 = tailpart0.addOrReplaceChild("tailpart1", CubeListBuilder.create()
                .texOffs(0, 54)
                .addBox(-1.5F, 14.0F, 16.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
        tailpart1.addOrReplaceChild("tailpart2", CubeListBuilder.create()
                .texOffs(41, 32)
                .addBox(-1.0F, 14.0F, 23.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(25, 19)
                .addBox(0.0F, 10.5F, 26.0F, 1.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

        addSpike(head, "spikepart0", 10.25F, -4.5F, -1.0F, 0.0F, 0.0F, 0.7854F);
        addSpike(head, "spikepart1", -12.25F, -4.5F, -1.0F, 0.0F, 0.0F, -0.7854F);
        addSpike(head, "spikepart2", -1.0F, -4.5F, -12.25F, 0.7854F, 0.0F, 0.0F);
        addSpike(head, "spikepart3", -1.0F, -4.5F, 10.5F, -0.7854F, 0.0F, 0.0F);
        addSpike(head, "spikepart4", 10.25F, -27.5F, -1.0F, 0.0F, 0.0F, 2.3562F);
        addSpike(head, "spikepart5", -12.25F, -27.5F, -1.0F, 0.0F, 0.0F, -2.3562F);
        addSpike(head, "spikepart6", -1.0F, -28.5F, -12.25F, 2.3562F, 0.0F, 0.0F);
        addSpike(head, "spikepart7", -1.0F, -27.5F, 10.25F, -2.3562F, 0.0F, 0.0F);
        addSpike(head, "spikepart8", -1.0F, -17.5F, -17.0F, Mth.HALF_PI, -0.7854F, 0.0F);
        addSpike(head, "spikepart9", -1.0F, -17.5F, -17.0F, Mth.HALF_PI, 0.7854F, 0.0F);
        addSpike(head, "spikepart10", -1.0F, -17.5F, -17.0F, Mth.HALF_PI, -2.3562F, 0.0F);
        addSpike(head, "spikepart11", -1.0F, -17.5F, -17.0F, Mth.HALF_PI, 2.3562F, 0.0F);

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    private static void addSpike(PartDefinition head, String name, float x, float y, float z, float xRot, float yRot, float zRot)
    {
        head.addOrReplaceChild(name, CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(x, y, z, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, xRot, yRot, zRot));
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

        float tail = Mth.sin(ageInTicks * 0.15F);
        tailpart0.yRot = tail * 0.15F;
        tailpart1.yRot = tail * 0.25F;
        tailpart2.yRot = tail * 0.35F;
    }
}
