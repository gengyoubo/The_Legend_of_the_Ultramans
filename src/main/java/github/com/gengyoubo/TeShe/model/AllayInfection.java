package github.com.gengyoubo.TeShe.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.animal.allay.Allay;

public class AllayInfection<T extends Allay> extends HierarchicalModel<T> implements ArmedModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("teshe", "allay_infection"), "main");
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart left_wing;
	private final ModelPart right_wing;

	public AllayInfection(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.body = this.root.getChild("body");
		this.right_arm = this.body.getChild("right_arm");
		this.left_arm = this.body.getChild("left_arm");
		this.left_wing = this.body.getChild("left_wing");
		this.right_wing = this.body.getChild("right_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.01F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(23, 0).addBox(-0.75F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 0.5F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(23, 6).addBox(-0.25F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 0.5F, 0.0F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(16, 14).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.5F, 1.0F, 1.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setupAnim(T allay, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		float wingPhase = ageInTicks * 20.0F * ((float)Math.PI / 180F) + limbSwing;
		float wingFlap = Mth.cos(wingPhase) * (float)Math.PI * 0.15F + limbSwingAmount;
		float partialTick = ageInTicks - allay.tickCount;
		float bobPhase = ageInTicks * 9.0F * ((float)Math.PI / 180F);
		float flyingAmount = Math.min(limbSwingAmount / 0.3F, 1.0F);
		float standingAmount = 1.0F - flyingAmount;
		float holdingItem = allay.getHoldingItemAnimationProgress(partialTick);

		if (allay.isDancing()) {
			float dancePhase = ageInTicks * 8.0F * ((float)Math.PI / 180F) + limbSwingAmount;
			float rootRoll = Mth.cos(dancePhase) * 16.0F * ((float)Math.PI / 180F);
			float spinning = allay.getSpinningProgress(partialTick);
			float headRoll = Mth.cos(dancePhase) * 14.0F * ((float)Math.PI / 180F);
			float headYaw = Mth.cos(dancePhase) * 30.0F * ((float)Math.PI / 180F);
			root.yRot = allay.isSpinning() ? 12.566371F * spinning : root.yRot;
			root.zRot = rootRoll * (1.0F - spinning);
			head.yRot = headYaw * (1.0F - spinning);
			head.zRot = headRoll * (1.0F - spinning);
		} else {
			head.xRot = headPitch * ((float)Math.PI / 180F);
			head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		}

		right_wing.xRot = 0.43633232F * (1.0F - flyingAmount);
		right_wing.yRot = (-(float)Math.PI / 4F) + wingFlap;
		left_wing.xRot = 0.43633232F * (1.0F - flyingAmount);
		left_wing.yRot = ((float)Math.PI / 4F) - wingFlap;
		body.xRot = flyingAmount * ((float)Math.PI / 4F);
		float armItemXRot = holdingItem * Mth.lerp(flyingAmount, (-(float)Math.PI / 3F), -1.134464F);
		root.y += (float)Math.cos(bobPhase) * 0.25F * standingAmount;
		right_arm.xRot = armItemXRot;
		left_arm.xRot = armItemXRot;
		float freeArmAmount = standingAmount * (1.0F - holdingItem);
		float armRoll = 0.43633232F - Mth.cos(bobPhase + ((float)Math.PI * 1.5F)) * (float)Math.PI * 0.075F * freeArmAmount;
		left_arm.zRot = -armRoll;
		right_arm.zRot = armRoll;
		right_arm.yRot = 0.27925268F * holdingItem;
		left_arm.yRot = -0.27925268F * holdingItem;
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
		root.translateAndRotate(poseStack);
		body.translateAndRotate(poseStack);
		poseStack.translate(0.0F, 0.0625F, 0.1875F);
		poseStack.mulPose(Axis.XP.rotation(right_arm.xRot));
		poseStack.scale(0.7F, 0.7F, 0.7F);
		poseStack.translate(0.0625F, 0.0F, 0.0F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
