package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.thip.init.THIPModItems;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class LerbisNemesisSidekickEntity extends GenericTesheGeoMob
{
    public static final double MAX_HEALTH = 30.0D;
    public static final double ARMOR = 10.0D;
    public static final double ATTACK_DAMAGE = 5.0D;
    public static final double MOVEMENT_SPEED = 0.33D;
    private static final int ATTACK_ANIMATION_TICKS = 8;
    private static final EntityDataAccessor<Integer> ATTACK_TICKS = SynchedEntityData.defineId(LerbisNemesisSidekickEntity.class, EntityDataSerializers.INT);
    private static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
    private static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("attack");

    public LerbisNemesisSidekickEntity(EntityType<? extends PathfinderMob> entityType, Level level)
    {
        super(entityType, level, "lerbis_nemesis_the_sidekick", "general", null);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
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
        if (!level().isClientSide() && getAttackTicks() > 0) {
            entityData.set(ATTACK_TICKS, getAttackTicks() - 1);
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
        spawnAtLocation(new ItemStack(THIPModItems.SPACIUM_SUBSTANCE.get(), 23));
        spawnAtLocation(new ItemStack(Items.IRON_INGOT, 12));
    }
}
