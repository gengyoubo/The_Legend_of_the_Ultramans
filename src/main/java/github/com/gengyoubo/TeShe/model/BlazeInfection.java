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
import net.minecraft.world.entity.monster.Blaze;

public class BlazeInfection<T extends Blaze> extends HierarchicalModel<T>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TE.MODID, "blaze_infection"), "main");

    private final ModelPart root;
    private final ModelPart group;
    private final ModelPart[] upperBodyParts = new ModelPart[12];
    private final ModelPart head;

    public BlazeInfection(ModelPart root)
    {
        this.root = root;
        this.group = root.getChild("group");
        for (int i = 0; i < upperBodyParts.length; i++) {
            upperBodyParts[i] = group.getChild("upperBodyParts" + i);
        }
        this.head = group.getChild("Head");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition group = partdefinition.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        for (int i = 0; i < 12; i++) {
            group.addOrReplaceChild("upperBodyParts" + i, CubeListBuilder.create()
                    .texOffs(0, 16)
                    .addBox(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
        }
        group.addOrReplaceChild("Head", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public ModelPart root()
    {
        return root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        float angle = ageInTicks * Mth.PI * -0.1F;
        for (int i = 0; i < 4; i++) {
            upperBodyParts[i].y = 2.0F + Mth.cos((i * 2.0F + ageInTicks) * 0.25F);
            upperBodyParts[i].x = Mth.cos(angle) * 9.0F;
            upperBodyParts[i].z = Mth.sin(angle) * 9.0F;
            angle += Mth.HALF_PI;
        }

        angle = Mth.PI / 4.0F + ageInTicks * Mth.PI * 0.03F;
        for (int i = 4; i < 8; i++) {
            upperBodyParts[i].y = 8.0F + Mth.cos((i * 2.0F + ageInTicks) * 0.25F);
            upperBodyParts[i].x = Mth.cos(angle) * 7.0F;
            upperBodyParts[i].z = Mth.sin(angle) * 7.0F;
            angle += Mth.HALF_PI;
        }

        angle = 0.47123894F + ageInTicks * Mth.PI * -0.05F;
        for (int i = 8; i < 12; i++) {
            upperBodyParts[i].y = 18.0F + Mth.cos((i * 1.5F + ageInTicks) * 0.5F);
            upperBodyParts[i].x = Mth.cos(angle) * 5.0F;
            upperBodyParts[i].z = Mth.sin(angle) * 5.0F;
            angle += Mth.HALF_PI;
        }

        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        head.xRot = headPitch * Mth.DEG_TO_RAD;
    }
}
