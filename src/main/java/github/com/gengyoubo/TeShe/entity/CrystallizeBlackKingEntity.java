package github.com.gengyoubo.TeShe.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.thip.init.THIPModItems;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class CrystallizeBlackKingEntity extends GenericTesheGeoMob
{
    public static final double MAX_HEALTH = 600.0D;
    public static final double ARMOR = 40.0D;
    public static final double ATTACK_DAMAGE = 25.0D;
    public static final double MOVEMENT_SPEED = 0.27D;
    private static final int ATTACK_ANIMATION_TICKS = 8;
    private static final int FLAME_BREATH_COOLDOWN_TICKS = 400;
    private static final int FLAME_BREATH_SEGMENTS = 10;
    private static final float FLAME_BREATH_DAMAGE = 20.0F;
    private static final double FLAME_BREATH_RANGE = 26.0D;
    private static final double FLAME_BREATH_STEP = 0.35D;
    private static final EntityDataAccessor<Integer> ATTACK_TICKS = SynchedEntityData.defineId(CrystallizeBlackKingEntity.class, EntityDataSerializers.INT);
    private static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
    private static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("attack");
    private int flameBreathCooldown;

    public CrystallizeBlackKingEntity(EntityType<? extends PathfinderMob> entityType, Level level)
    {
        super(entityType, level, "crystallize_black_king", "general", null);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        entityData.define(ATTACK_TICKS, 0);
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        if (level().isClientSide()) {
            return;
        }

        if (getAttackTicks() > 0) {
            entityData.set(ATTACK_TICKS, getAttackTicks() - 1);
        }
        if (flameBreathCooldown > 0) {
            flameBreathCooldown--;
        }

        LivingEntity target = getTarget();
        if (target != null && flameBreathCooldown <= 0 && distanceToSqr(target) <= FLAME_BREATH_RANGE * FLAME_BREATH_RANGE && hasLineOfSight(target)) {
            breatheFire(target);
            flameBreathCooldown = FLAME_BREATH_COOLDOWN_TICKS;
        }
    }

    @Override
    public boolean doHurtTarget(Entity target)
    {
        startAttackAnimation();
        return super.doHurtTarget(target);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<>(this, "movement_controller", 0, state -> {
            if (getAttackTicks() > 0) {
                state.setControllerSpeed(1.0F);
                return state.setAndContinue(ATTACK);
            }

            if (state.isMoving()) {
                state.setControllerSpeed(getWalkAnimationSpeed());
                return state.setAndContinue(WALK);
            }

            state.setControllerSpeed(1.0F);
            return PlayState.STOP;
        }));
    }

    private void breatheFire(LivingEntity target)
    {
        if (!(level() instanceof ServerLevel serverLevel)) {
            return;
        }

        startAttackAnimation();
        getLookControl().setLookAt(target, 30.0F, 30.0F);
        Vec3 start = getEyePosition().add(getViewVector(1.0F).scale(0.5D));
        Vec3 direction = target.getEyePosition().subtract(start).normalize();
        Vec3 maxEnd = start.add(direction.scale(FLAME_BREATH_RANGE));
        HitResult blockHit = level().clip(new ClipContext(start, maxEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        Vec3 end = blockHit.getLocation();
        double blockDistanceSqr = start.distanceToSqr(end);
        AABB searchBox = getBoundingBox().expandTowards(direction.scale(FLAME_BREATH_RANGE)).inflate(1.25D);
        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(level(), this, start, end, searchBox, entity -> entity instanceof LivingEntity && entity.isPickable() && !entity.isSpectator());
        if (entityHit != null && start.distanceToSqr(entityHit.getLocation()) <= blockDistanceSqr) {
            end = entityHit.getLocation();
            if (entityHit.getEntity() instanceof LivingEntity hitTarget) {
                for (int i = 0; i < FLAME_BREATH_SEGMENTS; i++) {
                    hitTarget.invulnerableTime = 0;
                    hitTarget.hurtTime = 0;
                    hitTarget.hurt(damageSources().onFire(), FLAME_BREATH_DAMAGE);
                    hitTarget.setSecondsOnFire(6);
                }
            }
        }

        spawnFlameStream(serverLevel, start, end);
        serverLevel.playSound(null, getX(), getY(), getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 1.0F, 0.65F);
    }

    private static void spawnFlameStream(ServerLevel level, Vec3 start, Vec3 end)
    {
        Vec3 path = end.subtract(start);
        double length = path.length();
        if (length <= 0.01D) {
            return;
        }

        Vec3 step = path.normalize().scale(FLAME_BREATH_STEP);
        for (double distance = 0.0D; distance <= length; distance += FLAME_BREATH_STEP) {
            Vec3 position = start.add(step.scale(distance / FLAME_BREATH_STEP));
            level.sendParticles(ParticleTypes.FLAME, position.x, position.y, position.z, 2, 0.04D, 0.04D, 0.04D, 0.01D);
        }
        level.sendParticles(ParticleTypes.FLAME, end.x, end.y, end.z, 24, 0.22D, 0.22D, 0.22D, 0.03D);
    }

    private float getWalkAnimationSpeed()
    {
        double horizontalSpeed = getDeltaMovement().horizontalDistance();
        return (float)Mth.clamp(horizontalSpeed / 0.12D, 0.45D, 1.35D);
    }

    private void startAttackAnimation()
    {
        if (!level().isClientSide()) {
            entityData.set(ATTACK_TICKS, ATTACK_ANIMATION_TICKS);
        }
    }

    private int getAttackTicks()
    {
        return entityData.get(ATTACK_TICKS);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit)
    {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        spawnAtLocation(new ItemStack(THIPModItems.PLASMA_CRYSTAL.get(), 34));
        spawnAtLocation(new ItemStack(THIPModItems.SPACIUM_SUBSTANCE.get(), 56));
        spawnAtLocation(new ItemStack(THIPModItems.MONSTER_SOUL.get(), 1 + random.nextInt(2)));
    }
}
