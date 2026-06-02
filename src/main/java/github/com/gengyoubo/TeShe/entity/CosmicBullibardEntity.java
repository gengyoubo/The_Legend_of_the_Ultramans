package github.com.gengyoubo.TeShe.entity;

import github.com.gengyoubo.TeShe.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.thip.entity.projectile.LargeProjectile;
import net.thip.init.THIPModEntities;
import net.thip.init.THIPModEffects;
import net.thip.network.Skill;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3f;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class CosmicBullibardEntity extends GenericTesheGeoMob
{
    public static final int NORMAL_VARIANT = 0;
    public static final int BOSS_VARIANT = 1;
    public static final double NORMAL_MAX_HEALTH = 500.0D;
    public static final double BOSS_MAX_HEALTH = 5000.0D;

    private static final int BEAM_SEGMENTS = 20;
    private static final int BEAM_TICKS_PER_SEGMENT = 1;
    private static final float BEAM_DAMAGE = 10.0F;
    private static final int BEAM_COOLDOWN_TICKS = 80;
    private static final double BEAM_RANGE = 32.0D;
    private static final float BEAM_PROJECTILE_SPEED = 1.0F;
    private static final String BULLIBARD_BEAM_FORWARD = "spark_to_sparkblue";
    private static final String BULLIBARD_BEAM_REVERSE = "sparkblue_to_spark";
    private static final String BULLIBARD_BEAM_ABILITY = "PaleHeatWave";
    private static final float DASH_DAMAGE = 100.0F;
    private static final int DASH_COOLDOWN_TICKS = 120;
    private static final int DASH_MAX_TICKS = 18;
    private static final double DASH_MIN_DISTANCE_SQR = 25.0D;
    private static final double DASH_MAX_DISTANCE_SQR = 576.0D;
    private static final double PROJECTILE_REFLECT_RANGE = 3.5D;
    private static final int BEAM_REFLECTION_DISABLE_TICKS = 300;
    private static final int REFLECTION_BREAK_MIN_HITS = 5;
    private static final int REFLECTION_BREAK_MAX_HITS = 10;
    private static final double REFLECTION_BREAK_START_CHANCE = 0.2D;
    private static final double FLIGHT_VERTICAL_TRIGGER_RANGE = 16.0D;
    private static final double FLIGHT_CHASE_SPEED = 0.9D;
    private static final int FLIGHT_HIT_COOLDOWN_TICKS = 40;
    private static final int ATTACK_ANIMATION_TICKS = 15;
    private static final float BULLIBARD_FEATHER_DROP_CHANCE = 0.4F;
    private static final EntityDataAccessor<Integer> ATTACK_TICKS = SynchedEntityData.defineId(CosmicBullibardEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPECIAL_INDIVIDUAL = SynchedEntityData.defineId(CosmicBullibardEntity.class, EntityDataSerializers.INT);
    private static final DustParticleOptions WHITE_BEAM_HIT_PARTICLE = new DustParticleOptions(new Vector3f(1.0F, 1.0F, 1.0F), 2.5F);
    private static final DustParticleOptions BLUE_BEAM_HIT_PARTICLE = new DustParticleOptions(new Vector3f(0.25F, 0.55F, 1.0F), 2.5F);
    private static final RawAnimation FLY = RawAnimation.begin().thenLoop("fly");
    private static final RawAnimation RUN = RawAnimation.begin().thenLoop("run");
    private static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("attack");

    private int beamCooldown;
    private int dashCooldown;
    private int dashTicks;
    private int activeBeamSegment;
    private int activeBeamMoveDelay;
    private int reflectionDisableTicks;
    private int flightHitCooldown;
    private int meleeHitsTaken;
    private boolean projectileReflectionBroken;
    private boolean sustainedFlightMode;
    private LivingEntity dashTarget;
    private LivingEntity activeBeamTarget;
    private boolean activeBeamStartsBlue;
    private final ServerBossEvent bossInfo = new ServerBossEvent(
            Component.empty(),
            BossEvent.BossBarColor.BLUE,
            BossEvent.BossBarOverlay.PROGRESS
    );

    public CosmicBullibardEntity(EntityType<? extends PathfinderMob> entityType, Level level)
    {
        super(entityType, level, "cosmic_bullibard", "cosmic_bullibard", null);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        entityData.define(ATTACK_TICKS, 0);
        entityData.define(SPECIAL_INDIVIDUAL, NORMAL_VARIANT);
    }

    @Override
    public void aiStep()
    {
        super.aiStep();

        if (!level().isClientSide() && getAttackTicks() > 0) {
            entityData.set(ATTACK_TICKS, getAttackTicks() - 1);
        }

        if (level().isClientSide()) {
            return;
        }

        updateBossInfo();

        if (beamCooldown > 0) {
            beamCooldown--;
        }
        if (dashCooldown > 0) {
            dashCooldown--;
        }
        if (reflectionDisableTicks > 0) {
            reflectionDisableTicks--;
        }
        if (flightHitCooldown > 0) {
            flightHitCooldown--;
        }

        reflectNearbyProjectiles();
        if (hasActiveFreezingBeam()) {
            tickFreezingBeam();
            return;
        }

        LivingEntity target = getTarget();
        if (target == null || !target.isAlive()) {
            finishDash();
            stopSustainedFlight();
            return;
        }

        if (shouldSustainFlight(target)) {
            tickSustainedFlight(target);
            return;
        }

        stopSustainedFlight();

        if (dashTicks > 0) {
            tickDash();
            return;
        }

        double distanceSqr = distanceToSqr(target);
        if (dashCooldown <= 0 && shouldStartFlightDash(target, distanceSqr)) {
            startDash(target);
            return;
        }

        if (!isFlying() && beamCooldown <= 0 && distanceSqr <= BEAM_RANGE * BEAM_RANGE && hasLineOfSight(target)) {
            startFreezingBeam(target);
            beamCooldown = BEAM_COOLDOWN_TICKS;
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity)
    {
        if (isFlying()) {
            return false;
        }

        if (entity instanceof LivingEntity target) {
            faceTarget(target);
        }
        startAttackAnimation();
        return super.doHurtTarget(entity);
    }

    @Override
    public boolean hurt(DamageSource source, float amount)
    {
        boolean meleeAttack = isMeleeAttack(source);
        boolean hurt = super.hurt(source, amount);
        if (hurt) {
            LivingEntity retaliationTarget = getRetaliationTarget(source);
            if (retaliationTarget != null) {
                setTarget(retaliationTarget);
            }

            if (meleeAttack && !projectileReflectionBroken) {
                recordMeleeHit();
            }
        }

        return hurt;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit)
    {
        super.dropCustomDeathLoot(source, looting, recentlyHit);

        if (isBossVariant()) {
            dropStack(Items.NETHERITE_INGOT, 10);
            dropStack(Items.IRON_BLOCK, 5);
            dropStack(resolveDropItem("thip", "plasma_core", ModItems.PLASMA_CORE.get()), 4);
            dropStack(Items.GOLD_BLOCK, 2);
            dropStack(Items.ICE, 16);
            dropStack(ModItems.BULLIBARD_FEATHER_STORY.get(), 1);
            return;
        }

        dropStack(resolveDropItem("thip", "plasma_crystal", ModItems.PLASMA_CRYSTAL.get()), 3 + random.nextInt(2));
        dropStack(resolveDropItem("thip", "plasma_core_fragments", ModItems.PLASMA_CORE_FRAGMENTS.get()), random.nextInt(3));
        if (random.nextFloat() < BULLIBARD_FEATHER_DROP_CHANCE) {
            dropStack(ModItems.BULLIBARD_FEATHER.get(), 1);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag)
    {
        super.addAdditionalSaveData(tag);
        tag.putInt("SpecialIndividual", getSpecialIndividual());
        tag.putInt("MeleeHitsTaken", meleeHitsTaken);
        tag.putBoolean("ProjectileReflectionBroken", projectileReflectionBroken);
        tag.putInt("ReflectionDisableTicks", reflectionDisableTicks);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag)
    {
        int specialIndividual = tag.getInt("SpecialIndividual");
        setSpecialIndividual(specialIndividual);
        super.readAdditionalSaveData(tag);
        setSpecialIndividual(specialIndividual);
        meleeHitsTaken = tag.getInt("MeleeHitsTaken");
        projectileReflectionBroken = tag.getBoolean("ProjectileReflectionBroken");
        reflectionDisableTicks = tag.getInt("ReflectionDisableTicks");
    }

    @Override
    public Component getDisplayName()
    {
        if (isBossVariant()) {
            return Component.translatable("entity.teshe.cosmic_bullibard.boss");
        }

        return super.getDisplayName();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player)
    {
        super.startSeenByPlayer(player);
        if (isBossVariant()) {
            bossInfo.addPlayer(player);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player)
    {
        super.stopSeenByPlayer(player);
        bossInfo.removePlayer(player);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<>(this, "movement_controller", 0, state -> {
            if (!isFlying() && getAttackTicks() > 0) {
                return state.setAndContinue(ATTACK);
            }

            if (isFlying()) {
                return state.setAndContinue(FLY);
            }

            if (state.isMoving()) {
                return state.setAndContinue(RUN);
            }

            return PlayState.STOP;
        }));
    }

    public boolean isFlying()
    {
        return sustainedFlightMode || dashTicks > 0 || (!onGround() && !isInWaterOrBubble());
    }

    public void setSpecialIndividual(int specialIndividual)
    {
        boolean wasFullHealth = getHealth() >= getMaxHealth();
        entityData.set(SPECIAL_INDIVIDUAL, specialIndividual);
        updateVariantMaxHealth(wasFullHealth);
        if (isBossVariant()) {
            setPersistenceRequired();
            bossInfo.setName(getDisplayName());
        } else {
            bossInfo.removeAllPlayers();
        }
    }

    public int getSpecialIndividual()
    {
        return entityData.get(SPECIAL_INDIVIDUAL);
    }

    public boolean isBossVariant()
    {
        return getSpecialIndividual() == BOSS_VARIANT;
    }

    private void updateVariantMaxHealth(boolean healToFull)
    {
        AttributeInstance maxHealth = getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }

        double targetMaxHealth = isBossVariant() ? BOSS_MAX_HEALTH : NORMAL_MAX_HEALTH;
        if (maxHealth.getBaseValue() != targetMaxHealth) {
            maxHealth.setBaseValue(targetMaxHealth);
        }

        if (healToFull) {
            setHealth(getMaxHealth());
        } else if (getHealth() > getMaxHealth()) {
            setHealth(getMaxHealth());
        }
    }

    private void updateBossInfo()
    {
        if (!isBossVariant()) {
            return;
        }

        bossInfo.setName(getDisplayName());
        bossInfo.setProgress(getHealth() / getMaxHealth());
    }

    private void reflectNearbyProjectiles()
    {
        if (projectileReflectionBroken || reflectionDisableTicks > 0) {
            return;
        }

        AABB area = getBoundingBox().inflate(PROJECTILE_REFLECT_RANGE);
        for (Projectile projectile : level().getEntitiesOfClass(Projectile.class, area, this::canReflectProjectile)) {
            reflectProjectile(projectile);
        }
    }

    private boolean canReflectProjectile(Projectile projectile)
    {
        if (!projectile.isAlive() || projectile.getOwner() == this) {
            return false;
        }

        Vec3 velocity = projectile.getDeltaMovement();
        if (velocity.lengthSqr() <= 0.01D) {
            return false;
        }

        Vec3 toBullibard = position().add(0.0D, getBbHeight() * 0.5D, 0.0D).subtract(projectile.position());
        return velocity.dot(toBullibard) > 0.0D;
    }

    private void reflectProjectile(Projectile projectile)
    {
        Entity owner = projectile.getOwner();
        Vec3 direction;
        if (owner != null && owner.isAlive()) {
            direction = owner.getEyePosition().subtract(projectile.position());
        } else {
            direction = projectile.position().subtract(position().add(0.0D, getBbHeight() * 0.5D, 0.0D));
        }

        if (direction.lengthSqr() <= 0.01D) {
            direction = getLookAngle().reverse();
        }

        double speed = Math.max(projectile.getDeltaMovement().length(), 1.5D);
        projectile.setOwner(this);
        projectile.setDeltaMovement(direction.normalize().scale(speed));
        projectile.setPos(projectile.getX(), projectile.getY() + 0.05D, projectile.getZ());
        if (projectile instanceof AbstractArrow arrow) {
            arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
        }

        if (isBeamProjectile(projectile)) {
            reflectionDisableTicks = BEAM_REFLECTION_DISABLE_TICKS;
        }
    }

    private void startFreezingBeam(LivingEntity target)
    {
        Vec3 start = getEyePosition();
        Vec3 end = target.getEyePosition();
        Vec3 direction = end.subtract(start);
        double distance = Math.min(direction.length(), BEAM_RANGE);
        if (distance <= 0.01D) {
            return;
        }

        faceTarget(target);
        activeBeamTarget = target;
        activeBeamStartsBlue = random.nextBoolean();
        activeBeamSegment = 0;
        activeBeamMoveDelay = 0;
        startAttackAnimation();
    }

    private boolean hasActiveFreezingBeam()
    {
        return activeBeamTarget != null && activeBeamSegment < BEAM_SEGMENTS;
    }

    private void tickFreezingBeam()
    {
        if (!(level() instanceof ServerLevel serverLevel) || activeBeamTarget == null || !activeBeamTarget.isAlive()) {
            stopFreezingBeam();
            return;
        }

        startAttackAnimation();
        faceTarget(activeBeamTarget);
        if (activeBeamMoveDelay > 0) {
            activeBeamMoveDelay--;
            return;
        }

        spawnFreezingBeamProjectile(serverLevel, activeBeamTarget);
        activeBeamSegment++;
        activeBeamMoveDelay = BEAM_TICKS_PER_SEGMENT - 1;
        if (activeBeamSegment >= BEAM_SEGMENTS) {
            stopFreezingBeam();
        }
    }

    @SuppressWarnings("unchecked")
    private void spawnFreezingBeamProjectile(ServerLevel serverLevel, LivingEntity target)
    {
        Vec3 start = new Vec3(getX(), getEyeY() - 0.1D, getZ());
        Vec3 end = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        Vec3 direction = end.subtract(start);
        if (direction.lengthSqr() <= 0.01D) {
            return;
        }

        String beamName = activeBeamStartsBlue ? BULLIBARD_BEAM_REVERSE : BULLIBARD_BEAM_FORWARD;
        LargeProjectile projectile = Skill.spawnCustomProjectile(
                (EntityType<LargeProjectile>)THIPModEntities.LARGE.get(),
                serverLevel,
                this,
                BEAM_DAMAGE,
                1,
                (byte)0,
                largeProjectile -> {
                    largeProjectile.getEntityData().set(LargeProjectile.ABILITY, BULLIBARD_BEAM_ABILITY);
                    largeProjectile.getEntityData().set(LargeProjectile.BEAM, beamName);
                    largeProjectile.getEntityData().set(LargeProjectile.BANG, true);
                }
        );
        projectile.setPos(start.x, start.y, start.z);
        Vec3 normalizedDirection = direction.normalize();
        projectile.shoot(normalizedDirection.x, normalizedDirection.y, normalizedDirection.z, BEAM_PROJECTILE_SPEED, 0.0F);
        serverLevel.addFreshEntity(projectile);
    }

    private void stopFreezingBeam()
    {
        activeBeamTarget = null;
        activeBeamStartsBlue = false;
        activeBeamSegment = 0;
        activeBeamMoveDelay = 0;
    }

    private void startDash(LivingEntity target)
    {
        stopFreezingBeam();
        dashTarget = target;
        dashTicks = DASH_MAX_TICKS;
        dashCooldown = DASH_COOLDOWN_TICKS;
        setNoGravity(true);
        getNavigation().stop();
    }

    private boolean shouldStartFlightDash(LivingEntity target, double distanceSqr)
    {
        return Math.abs(target.getY() - getY()) <= FLIGHT_VERTICAL_TRIGGER_RANGE
                && distanceSqr >= DASH_MIN_DISTANCE_SQR
                && distanceSqr <= DASH_MAX_DISTANCE_SQR
                && hasLineOfSight(target);
    }

    private boolean shouldSustainFlight(LivingEntity target)
    {
        if (!(target instanceof Player player)) {
            return false;
        }

        return !player.onGround() && (player.getAbilities().flying || player.isFallFlying());
    }

    private void tickSustainedFlight(LivingEntity target)
    {
        stopFreezingBeam();
        sustainedFlightMode = true;
        dashTicks = 0;
        dashTarget = null;
        setNoGravity(true);
        getNavigation().stop();

        Vec3 direction = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D)
                .subtract(position().add(0.0D, getBbHeight() * 0.5D, 0.0D));
        if (direction.lengthSqr() > 0.01D) {
            setDeltaMovement(direction.normalize().scale(FLIGHT_CHASE_SPEED));
            lookAt(target, 30.0F, 30.0F);
        }

        if (flightHitCooldown <= 0 && getBoundingBox().inflate(0.35D).intersects(target.getBoundingBox())) {
            hurtWithNoInvulnerability(target, DASH_DAMAGE);
            flightHitCooldown = FLIGHT_HIT_COOLDOWN_TICKS;
        }
    }

    private void stopSustainedFlight()
    {
        if (!sustainedFlightMode) {
            return;
        }

        sustainedFlightMode = false;
        setNoGravity(false);
        setDeltaMovement(getDeltaMovement().multiply(0.3D, 0.0D, 0.3D).add(0.0D, -0.2D, 0.0D));
    }

    private void tickDash()
    {
        LivingEntity target = dashTarget == null ? getTarget() : dashTarget;
        if (target == null || !target.isAlive()) {
            finishDash();
            return;
        }

        Vec3 direction = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D)
                .subtract(position().add(0.0D, getBbHeight() * 0.5D, 0.0D));
        if (direction.lengthSqr() > 0.01D) {
            setDeltaMovement(direction.normalize().scale(1.55D));
            lookAt(target, 30.0F, 30.0F);
        }

        AABB hitBox = getBoundingBox().inflate(0.35D);
        if (hitBox.intersects(target.getBoundingBox())) {
            hurtWithNoInvulnerability(target, DASH_DAMAGE);
            finishDash();
            return;
        }

        dashTicks--;
        if (dashTicks <= 0) {
            finishDash();
        }
    }

    private void finishDash()
    {
        if (dashTicks <= 0 && dashTarget == null && !isNoGravity()) {
            return;
        }

        dashTicks = 0;
        dashTarget = null;
        setNoGravity(false);
        setDeltaMovement(getDeltaMovement().multiply(0.2D, 0.0D, 0.2D).add(0.0D, -0.2D, 0.0D));
    }

    private void hurtWithNoInvulnerability(LivingEntity target, float damage)
    {
        target.invulnerableTime = 0;
        target.hurtTime = 0;
        target.hurt(damageSources().mobAttack(this), damage);
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

    private static void applyCold(LivingEntity target)
    {
        target.setTicksFrozen(Math.max(target.getTicksFrozen(), target.getTicksRequiredToFreeze() + 80));
        target.addEffect(new MobEffectInstance(THIPModEffects.FROZEN.get(), 100, 0));
    }

    public static void handleBullibardBeamHit(ServerLevel serverLevel, LargeProjectile projectile, LivingEntity target)
    {
        String beamName = projectile.getEntityData().get(LargeProjectile.BEAM);
        if (!isBullibardBeam(beamName)) {
            return;
        }

        applyCold(target);
        spawnBeamHitParticles(serverLevel, target, isBullibardBeamFrameBlue(beamName, projectile.tickCount));
    }

    public static boolean isBullibardBeamProjectile(LargeProjectile projectile)
    {
        return isBullibardBeam(projectile.getEntityData().get(LargeProjectile.BEAM));
    }

    private static void spawnBeamHitParticles(ServerLevel serverLevel, LivingEntity target, boolean blue)
    {
        double x = target.getX();
        double y = target.getY() + target.getBbHeight() * 0.55D;
        double z = target.getZ();
        DustParticleOptions colorParticle = blue ? BLUE_BEAM_HIT_PARTICLE : WHITE_BEAM_HIT_PARTICLE;
        serverLevel.sendParticles(colorParticle, x, y, z, 16, 0.55D, 0.6D, 0.55D, 0.04D);
    }

    private static boolean isBullibardBeam(String beamName)
    {
        return BULLIBARD_BEAM_FORWARD.equals(beamName) || BULLIBARD_BEAM_REVERSE.equals(beamName);
    }

    private static boolean isBullibardBeamFrameBlue(String beamName, int tickCount)
    {
        int frame = Math.min(7, tickCount * 8 / 8);
        boolean firstHalf = frame < 4;
        return BULLIBARD_BEAM_REVERSE.equals(beamName) ? firstHalf : !firstHalf;
    }

    private void faceTarget(LivingEntity target)
    {
        lookAt(target, 60.0F, 60.0F);
        getLookControl().setLookAt(target, 60.0F, 60.0F);
    }

    private boolean isMeleeAttack(DamageSource source)
    {
        Entity attacker = source.getEntity();
        Entity directAttacker = source.getDirectEntity();
        return attacker instanceof LivingEntity && attacker == directAttacker && attacker != this;
    }

    private LivingEntity getRetaliationTarget(DamageSource source)
    {
        Entity attacker = source.getEntity();
        LivingEntity livingAttacker = attacker instanceof LivingEntity ? (LivingEntity)attacker : null;
        if (livingAttacker == null) {
            Entity directAttacker = source.getDirectEntity();
            if (directAttacker instanceof LivingEntity livingDirectAttacker) {
                livingAttacker = livingDirectAttacker;
            } else {
                return null;
            }
        }

        if (livingAttacker == this || !livingAttacker.isAlive()) {
            return null;
        }

        if (livingAttacker instanceof Player player && (player.isCreative() || player.isSpectator())) {
            return null;
        }

        return livingAttacker;
    }

    private boolean isBeamProjectile(Projectile projectile)
    {
        ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(projectile.getType());
        String typeName = key == null ? "" : key.getPath();
        String className = projectile.getClass().getName().toLowerCase();
        return containsBeamName(typeName) || containsBeamName(className);
    }

    private Item resolveDropItem(String namespace, String path, Item fallback)
    {
        Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath(namespace, path));
        return item == null || item == Items.AIR ? fallback : item;
    }

    private void dropStack(Item item, int count)
    {
        if (count <= 0 || item == Items.AIR) {
            return;
        }

        spawnAtLocation(new ItemStack(item, count));
    }

    private boolean containsBeamName(String name)
    {
        return name.contains("beam") || name.contains("ray") || name.contains("laser");
    }

    private void recordMeleeHit()
    {
        meleeHitsTaken++;
        if (meleeHitsTaken < REFLECTION_BREAK_MIN_HITS) {
            return;
        }

        double chance = getReflectionBreakChance();
        if (random.nextDouble() < chance) {
            projectileReflectionBroken = true;
        }
    }

    private double getReflectionBreakChance()
    {
        if (meleeHitsTaken >= REFLECTION_BREAK_MAX_HITS) {
            return 1.0D;
        }

        double progress = (double)(meleeHitsTaken - REFLECTION_BREAK_MIN_HITS)
                / (double)(REFLECTION_BREAK_MAX_HITS - REFLECTION_BREAK_MIN_HITS);
        return REFLECTION_BREAK_START_CHANCE + progress * (1.0D - REFLECTION_BREAK_START_CHANCE);
    }
}
