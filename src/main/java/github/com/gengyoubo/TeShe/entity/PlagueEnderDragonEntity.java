package github.com.gengyoubo.TeShe.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PlagueEnderDragonEntity extends Monster implements GeoEntity
{
    public static final double MAX_HEALTH = 200.0D;
    public static final double ARMOR = 10.0D;
    public static final double ATTACK_DAMAGE = 15.0D;
    public static final double MOVEMENT_SPEED = 0.35D;

    private static final double ORBIT_RADIUS = 16.0D;
    private static final double ORBIT_HEIGHT = 8.0D;
    private static final double ORBIT_SPEED = 0.48D;
    private static final double CHARGE_SPEED = 1.25D;
    private static final int CHARGE_TIME = 70;
    private static final int CHARGE_COOLDOWN = 120;
    private static final int BITE_COOLDOWN = 25;
    private static final RawAnimation FLY = RawAnimation.begin().thenLoop("fly");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private double homeX;
    private double homeY;
    private double homeZ;
    private int chargeTicks;
    private int chargeCooldown;
    private int biteCooldown;
    private int orbitTicks;
    private boolean homeSet;

    public PlagueEnderDragonEntity(EntityType<? extends PlagueEnderDragonEntity> entityType, Level level)
    {
        super(entityType, level);
        this.xpReward = 120;
        setNoGravity(true);
    }

    @Override
    protected void registerGoals()
    {
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected Component getTypeName()
    {
        return Component.translatable("entity.teshe.moyinglong");
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        setNoGravity(true);

        if (level().isClientSide()) {
            return;
        }

        if (!homeSet) {
            setHome(position());
        }

        if (chargeCooldown > 0) {
            chargeCooldown--;
        }
        if (biteCooldown > 0) {
            biteCooldown--;
        }

        LivingEntity target = getTarget();
        if (target != null && (!target.isAlive() || target.isSpectator())) {
            setTarget(null);
            target = null;
        }

        if (target == null) {
            orbitHome();
            return;
        }

        lookAt(target, 30.0F, 30.0F);
        if (chargeTicks > 0) {
            chargeAt(target);
            return;
        }

        if (chargeCooldown <= 0 && distanceToSqr(target) > 64.0D && hasLineOfSight(target)) {
            chargeTicks = CHARGE_TIME;
            chargeCooldown = CHARGE_COOLDOWN;
            chargeAt(target);
            return;
        }

        orbitTarget(target);
        biteIfClose(target);
    }

    @Override
    public boolean doHurtTarget(Entity entity)
    {
        boolean hurt = super.doHurtTarget(entity);
        if (hurt && entity instanceof LivingEntity livingEntity) {
            livingEntity.knockback(1.5D, getX() - livingEntity.getX(), getZ() - livingEntity.getZ());
        }
        return hurt;
    }

    @Override
    public boolean causeFallDamage(float distance, float multiplier, DamageSource source)
    {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, net.minecraft.core.BlockPos pos)
    {
    }

    @Override
    public boolean isPushable()
    {
        return false;
    }

    @Override
    protected void doPush(Entity entity)
    {
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<>(this, "flight_controller", 0, state -> state.setAndContinue(FLY)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag)
    {
        super.addAdditionalSaveData(tag);
        tag.putDouble("HomeX", homeX);
        tag.putDouble("HomeY", homeY);
        tag.putDouble("HomeZ", homeZ);
        tag.putBoolean("HomeSet", homeSet);
        tag.putInt("ChargeCooldown", chargeCooldown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag)
    {
        super.readAdditionalSaveData(tag);
        homeX = tag.getDouble("HomeX");
        homeY = tag.getDouble("HomeY");
        homeZ = tag.getDouble("HomeZ");
        homeSet = tag.getBoolean("HomeSet");
        chargeCooldown = tag.getInt("ChargeCooldown");
        setNoGravity(true);
    }

    private void orbitHome()
    {
        orbitTicks++;
        double angle = orbitTicks * 0.04D;
        Vec3 orbitPoint = new Vec3(
                homeX + Mth.cos((float)angle) * ORBIT_RADIUS,
                homeY + ORBIT_HEIGHT + Mth.sin((float)(angle * 0.7D)) * 3.0D,
                homeZ + Mth.sin((float)angle) * ORBIT_RADIUS
        );
        moveToward(orbitPoint, ORBIT_SPEED);
    }

    private void orbitTarget(LivingEntity target)
    {
        orbitTicks++;
        double angle = orbitTicks * 0.055D;
        Vec3 orbitPoint = new Vec3(
                target.getX() + Mth.cos((float)angle) * ORBIT_RADIUS,
                target.getY() + target.getBbHeight() + ORBIT_HEIGHT + Mth.sin((float)(angle * 0.6D)) * 2.0D,
                target.getZ() + Mth.sin((float)angle) * ORBIT_RADIUS
        );
        moveToward(orbitPoint, ORBIT_SPEED);
    }

    private void chargeAt(LivingEntity target)
    {
        Vec3 targetCenter = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D);
        Vec3 selfCenter = position().add(0.0D, getBbHeight() * 0.5D, 0.0D);
        Vec3 direction = targetCenter.subtract(selfCenter);
        if (direction.lengthSqr() > 0.01D) {
            Vec3 movement = direction.normalize().scale(CHARGE_SPEED);
            setDeltaMovement(movement);
            move(MoverType.SELF, movement);
            rotateTowardMovement(movement);
        }

        if (getBoundingBox().inflate(1.0D).intersects(target.getBoundingBox())) {
            target.invulnerableTime = 0;
            doHurtTarget(target);
            chargeTicks = 0;
            chargeCooldown = CHARGE_COOLDOWN;
            return;
        }

        chargeTicks--;
        if (chargeTicks <= 0) {
            setDeltaMovement(getDeltaMovement().scale(0.35D));
        }
    }

    private void biteIfClose(LivingEntity target)
    {
        if (biteCooldown > 0 || distanceToSqr(target) > 36.0D) {
            return;
        }

        target.invulnerableTime = 0;
        doHurtTarget(target);
        biteCooldown = BITE_COOLDOWN;
    }

    private void moveToward(Vec3 target, double speed)
    {
        Vec3 direction = target.subtract(position());
        if (direction.lengthSqr() <= 0.01D) {
            setDeltaMovement(getDeltaMovement().scale(0.8D));
            return;
        }

        Vec3 desired = direction.normalize().scale(speed);
        Vec3 current = getDeltaMovement();
        Vec3 next = current.add(desired.subtract(current).scale(0.12D));
        setDeltaMovement(next);
        move(MoverType.SELF, next);
        rotateTowardMovement(next);
    }

    private void rotateTowardMovement(Vec3 movement)
    {
        if (movement.lengthSqr() <= 0.001D) {
            return;
        }

        float yaw = (float)(Mth.atan2(movement.z, movement.x) * Mth.RAD_TO_DEG) - 90.0F;
        float pitch = (float)(-(Mth.atan2(movement.y, movement.horizontalDistance()) * Mth.RAD_TO_DEG));
        setYRot(lerpRotation(getYRot(), yaw, 8.0F));
        yBodyRot = getYRot();
        yHeadRot = getYRot();
        setXRot(lerpRotation(getXRot(), pitch, 6.0F));
    }

    private static float lerpRotation(float current, float target, float maxStep)
    {
        float delta = Mth.wrapDegrees(target - current);
        delta = Mth.clamp(delta, -maxStep, maxStep);
        return current + delta;
    }

    private void setHome(Vec3 position)
    {
        homeX = position.x;
        homeY = position.y;
        homeZ = position.z;
        homeSet = true;
    }

    public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.ARMOR, ARMOR)
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, 96.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }
}
