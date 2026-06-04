package github.com.gengyoubo.TeShe.entity;

import github.com.gengyoubo.TeShe.world.KeanAntonlaWorldData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
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
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.thip.init.THIPModEffects;
import net.thip.init.THIPModItems;
import org.joml.Vector3f;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class KeanAntonlaEntity extends GenericTesheGeoMob
{
    public static final double MAX_HEALTH = 5000.0D;
    public static final double ARMOR = 120.0D;
    public static final double ATTACK_DAMAGE = 60.0D;
    public static final double MOVEMENT_SPEED = 0.24D;
    private static final int BEAM_COOLDOWN_TICKS = 600;
    private static final int BEAM_CHARGE_TICKS = 35;
    private static final int BEAM_SEGMENTS = 30;
    private static final float BEAM_DAMAGE = 50.0F;
    private static final double BEAM_RANGE = 34.0D;
    private static final double BEAM_STEP = 0.45D;
    private static final int BOMBARD_COOLDOWN_TICKS = 500;
    private static final int BOMBARD_PROJECTILES = 20;
    private static final float BOMBARD_DAMAGE = 100.0F;
    private static final double BOMBARD_RANGE = 20.0D;
    private static final int DASH_COOLDOWN_TICKS = 220;
    private static final float DASH_DAMAGE = 300.0F;
    private static final double DASH_DISTANCE = 10.0D;
    private static final EntityDataAccessor<Integer> ACTION_TICKS = SynchedEntityData.defineId(KeanAntonlaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ACTION_KIND = SynchedEntityData.defineId(KeanAntonlaEntity.class, EntityDataSerializers.INT);
    private static final RawAnimation WALK = RawAnimation.begin().thenLoop("行走");
    private static final RawAnimation BEAM = RawAnimation.begin().thenPlay("电磁能量炮");
    private static final RawAnimation BOMBARD = RawAnimation.begin().thenPlay("切换为炮火模式");
    private static final DustParticleOptions YELLOW_PLASMA = new DustParticleOptions(new Vector3f(1.0F, 0.86F, 0.08F), 1.45F);
    private static final DustParticleOptions PURPLE_PLASMA = new DustParticleOptions(new Vector3f(0.62F, 0.16F, 1.0F), 1.55F);
    private int beamCooldown;
    private int beamChargeTicks;
    private int bombardCooldown;
    private int dashCooldown;
    private LivingEntity chargedTarget;

    public KeanAntonlaEntity(EntityType<? extends PathfinderMob> entityType, Level level)
    {
        super(entityType, level, "kean_antonla", "kean_antonla", null);
    }

    public static boolean canSpawn(
            EntityType<KeanAntonlaEntity> entityType,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random
    )
    {
        if (!KeanAntonlaWorldData.get(level.getLevel()).isWorldgenEnabled()
                || random.nextFloat() >= 0.02F
                || !isDesertOrBadlands(level, pos)) {
            return false;
        }

        return Mob.checkMobSpawnRules(entityType, level, spawnType, pos, random);
    }

    @Override
    protected void registerGoals()
    {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 18.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        entityData.define(ACTION_TICKS, 0);
        entityData.define(ACTION_KIND, 0);
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        if (level().isClientSide()) {
            return;
        }

        tickCooldowns();
        LivingEntity target = getTarget();
        boolean flying = target != null && target.getY() > getY() + 4.0D;
        setNoGravity(flying);
        if (target == null) {
            chargedTarget = null;
            beamChargeTicks = 0;
            return;
        }

        getLookControl().setLookAt(target, 30.0F, 30.0F);
        if (flying) {
            flyToward(target);
        }

        if (beamChargeTicks > 0) {
            tickBeamCharge(target);
            return;
        }

        double distanceSqr = distanceToSqr(target);
        if (beamCooldown <= 0 && distanceSqr <= BEAM_RANGE * BEAM_RANGE && hasLineOfSight(target)) {
            beginBeamCharge(target);
            return;
        }

        if (!flying && dashCooldown <= 0 && distanceSqr > 25.0D && distanceSqr <= 324.0D && random.nextFloat() < 0.08F) {
            dashAt(target);
            dashCooldown = DASH_COOLDOWN_TICKS;
            return;
        }

        if (!flying && bombardCooldown <= 0 && distanceSqr <= BOMBARD_RANGE * BOMBARD_RANGE && random.nextFloat() < 0.05F) {
            bombard();
            bombardCooldown = BOMBARD_COOLDOWN_TICKS;
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount)
    {
        if (source.getDirectEntity() instanceof Projectile) {
            amount *= 0.5F;
        }

        return super.hurt(source, amount);
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source)
    {
        return false;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<>(this, "movement_controller", 0, state -> {
            int actionKind = entityData.get(ACTION_KIND);
            if (entityData.get(ACTION_TICKS) > 0) {
                state.setControllerSpeed(1.0F);
                return state.setAndContinue(actionKind == 2 ? BOMBARD : BEAM);
            }

            if (state.isMoving()) {
                state.setControllerSpeed(getWalkAnimationSpeed());
                return state.setAndContinue(WALK);
            }

            state.setControllerSpeed(1.0F);
            return PlayState.STOP;
        }));
    }

    private static boolean isDesertOrBadlands(LevelAccessor level, BlockPos pos)
    {
        return level.getBiome(pos).is(BiomeTags.IS_BADLANDS)
                || level.getBiome(pos).is(Biomes.DESERT);
    }

    private void tickCooldowns()
    {
        if (entityData.get(ACTION_TICKS) > 0) {
            entityData.set(ACTION_TICKS, entityData.get(ACTION_TICKS) - 1);
        }
        if (beamCooldown > 0) {
            beamCooldown--;
        }
        if (bombardCooldown > 0) {
            bombardCooldown--;
        }
        if (dashCooldown > 0) {
            dashCooldown--;
        }
    }

    private void beginBeamCharge(LivingEntity target)
    {
        chargedTarget = target;
        beamChargeTicks = BEAM_CHARGE_TICKS;
        entityData.set(ACTION_KIND, 1);
        entityData.set(ACTION_TICKS, BEAM_CHARGE_TICKS + 10);
        level().playSound(null, getX(), getY(), getZ(), SoundEvents.BEACON_POWER_SELECT, SoundSource.HOSTILE, 1.0F, 0.6F);
    }

    private void tickBeamCharge(LivingEntity fallbackTarget)
    {
        beamChargeTicks--;
        LivingEntity target = chargedTarget != null && chargedTarget.isAlive() ? chargedTarget : fallbackTarget;
        getLookControl().setLookAt(target, 30.0F, 30.0F);
        if (beamChargeTicks <= 0) {
            fireElectromagneticBeam(target);
            beamCooldown = BEAM_COOLDOWN_TICKS;
            chargedTarget = null;
        }
    }

    private void fireElectromagneticBeam(LivingEntity target)
    {
        if (!(level() instanceof ServerLevel serverLevel)) {
            return;
        }

        Vec3 start = getEyePosition().add(getViewVector(1.0F).scale(0.7D));
        Vec3 direction = target.getEyePosition().subtract(start).normalize();
        Vec3 maxEnd = start.add(direction.scale(BEAM_RANGE));
        HitResult blockHit = level().clip(new ClipContext(start, maxEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        Vec3 end = blockHit.getLocation();
        LivingEntity hitTarget = findFirstLivingOnLine(start, end, 1.35D);
        if (hitTarget != null) {
            end = hitTarget.getEyePosition();
            for (int i = 0; i < BEAM_SEGMENTS; i++) {
                hitTarget.invulnerableTime = 0;
                hitTarget.hurtTime = 0;
                hitTarget.hurt(damageSources().mobAttack(this), BEAM_DAMAGE);
            }
            hitTarget.addEffect(new net.minecraft.world.effect.MobEffectInstance(THIPModEffects.MOVEMENT_BIND.get(), 100, 1));
        }

        spawnElectromagneticBeam(serverLevel, start, end);
        serverLevel.playSound(null, getX(), getY(), getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 1.1F, 1.35F);
    }

    private void bombard()
    {
        if (!(level() instanceof ServerLevel serverLevel)) {
            return;
        }

        entityData.set(ACTION_KIND, 2);
        entityData.set(ACTION_TICKS, 45);
        Vec3 origin = position().add(0.0D, getBbHeight() * 0.78D, 0.0D);
        for (int i = 0; i < BOMBARD_PROJECTILES; i++) {
            double angle = (Math.PI * 2.0D * i / BOMBARD_PROJECTILES) + random.nextDouble() * 0.18D;
            double pitch = -0.22D - random.nextDouble() * 0.18D;
            Vec3 direction = new Vec3(Math.cos(angle), pitch, Math.sin(angle)).normalize();
            Vec3 end = origin.add(direction.scale(BOMBARD_RANGE));
            HitResult blockHit = level().clip(new ClipContext(origin, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            Vec3 impact = blockHit.getLocation();
            LivingEntity hitTarget = findFirstLivingOnLine(origin, impact, 1.25D);
            if (hitTarget != null) {
                impact = hitTarget.position().add(0.0D, hitTarget.getBbHeight() * 0.5D, 0.0D);
                hitTarget.hurt(damageSources().mobAttack(this), BOMBARD_DAMAGE);
            }
            spawnPurpleShot(serverLevel, origin, impact);
        }
        serverLevel.playSound(null, getX(), getY(), getZ(), SoundEvents.FIREWORK_ROCKET_LARGE_BLAST, SoundSource.HOSTILE, 1.0F, 0.55F);
    }

    private void dashAt(LivingEntity target)
    {
        if (!(level() instanceof ServerLevel serverLevel)) {
            return;
        }

        entityData.set(ACTION_KIND, 1);
        entityData.set(ACTION_TICKS, 16);
        Vec3 direction = new Vec3(target.getX() - getX(), 0.0D, target.getZ() - getZ()).normalize();
        if (direction.lengthSqr() < 0.001D) {
            return;
        }

        Vec3 start = position();
        Vec3 end = start.add(direction.scale(DASH_DISTANCE));
        Set<UUID> damaged = new HashSet<>();
        for (double distance = 0.0D; distance <= DASH_DISTANCE; distance += 0.75D) {
            Vec3 current = start.add(direction.scale(distance));
            destroyDashBlocks(serverLevel, BlockPos.containing(current));
            AABB hitBox = new AABB(current.x - 1.2D, current.y, current.z - 1.2D, current.x + 1.2D, current.y + getBbHeight(), current.z + 1.2D);
            for (LivingEntity entity : serverLevel.getEntitiesOfClass(LivingEntity.class, hitBox, entity -> entity != this && entity.isAlive())) {
                if (damaged.add(entity.getUUID())) {
                    entity.hurt(damageSources().mobAttack(this), DASH_DAMAGE);
                }
            }
        }

        BlockPos landingColumn = BlockPos.containing(end);
        BlockPos landing = serverLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, landingColumn);
        moveTo(landing.getX() + 0.5D, landing.getY(), landing.getZ() + 0.5D, getYRot(), getXRot());
        serverLevel.playSound(null, getX(), getY(), getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 1.0F, 0.8F);
    }

    private void destroyDashBlocks(ServerLevel level, BlockPos center)
    {
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-1, 0, -1), center.offset(1, Mth.ceil(getBbHeight()), 1))) {
            BlockState state = level.getBlockState(pos);
            if (!state.isAir() && state.getDestroySpeed(level, pos) >= 0.0F) {
                level.destroyBlock(pos, true, this);
            }
        }
    }

    private LivingEntity findFirstLivingOnLine(Vec3 start, Vec3 end, double inflate)
    {
        AABB searchBox = new AABB(start, end).inflate(inflate);
        EntityHitResult closest = null;
        double closestDistance = Double.MAX_VALUE;
        List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, searchBox, entity -> entity != this && entity.isAlive() && entity.isPickable() && !entity.isSpectator());
        for (LivingEntity entity : entities) {
            EntityHitResult hit = entity.getBoundingBox().inflate(inflate).clip(start, end)
                    .map(location -> new EntityHitResult(entity, location))
                    .orElse(null);
            if (hit == null) {
                continue;
            }

            double distance = start.distanceToSqr(hit.getLocation());
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = hit;
            }
        }

        return closest != null && closest.getEntity() instanceof LivingEntity living ? living : null;
    }

    private static void spawnElectromagneticBeam(ServerLevel level, Vec3 start, Vec3 end)
    {
        Vec3 path = end.subtract(start);
        double length = path.length();
        if (length <= 0.01D) {
            return;
        }

        Vec3 direction = path.normalize();
        for (double distance = 0.0D; distance <= length; distance += BEAM_STEP) {
            Vec3 position = start.add(direction.scale(distance));
            float hue = (float)((distance * 0.08D) % 1.0D);
            int rgb = java.awt.Color.HSBtoRGB(hue, 0.95F, 1.0F);
            float red = ((rgb >> 16) & 255) / 255.0F;
            float green = ((rgb >> 8) & 255) / 255.0F;
            float blue = (rgb & 255) / 255.0F;
            DustParticleOptions rainbow = new DustParticleOptions(new Vector3f(red, green, blue), 1.25F);
            level.sendParticles(rainbow, position.x, position.y, position.z, 1, 0.04D, 0.04D, 0.04D, 0.0D);
            level.sendParticles(YELLOW_PLASMA, position.x, position.y, position.z, 1, 0.04D, 0.04D, 0.04D, 0.0D);
        }
        level.sendParticles(YELLOW_PLASMA, end.x, end.y, end.z, 36, 0.35D, 0.35D, 0.35D, 0.02D);
    }

    private static void spawnPurpleShot(ServerLevel level, Vec3 start, Vec3 end)
    {
        Vec3 path = end.subtract(start);
        double length = path.length();
        if (length <= 0.01D) {
            return;
        }

        Vec3 direction = path.normalize();
        for (double distance = 0.0D; distance <= length; distance += 0.55D) {
            Vec3 position = start.add(direction.scale(distance));
            level.sendParticles(PURPLE_PLASMA, position.x, position.y, position.z, 1, 0.035D, 0.035D, 0.035D, 0.0D);
        }
        level.sendParticles(PURPLE_PLASMA, end.x, end.y, end.z, 20, 0.3D, 0.3D, 0.3D, 0.02D);
    }

    private void flyToward(LivingEntity target)
    {
        Vec3 direction = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D).subtract(position()).normalize();
        setDeltaMovement(getDeltaMovement().scale(0.55D).add(direction.scale(0.08D)));
    }

    private float getWalkAnimationSpeed()
    {
        double horizontalSpeed = getDeltaMovement().horizontalDistance();
        return (float)Mth.clamp(horizontalSpeed / 0.12D, 0.45D, 1.25D);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit)
    {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        spawnAtLocation(new ItemStack(THIPModItems.PLASMA_CRYSTAL.get(), 15 + random.nextInt(2)));
        spawnAtLocation(new ItemStack(THIPModItems.SPACIUM_SUBSTANCE.get(), 20 + random.nextInt(11)));
        spawnAtLocation(new ItemStack(github.com.gengyoubo.TeShe.registry.ModItems.KEAN_ANTONLA_BROKEN_HORN.get()));
        spawnAtLocation(new ItemStack(THIPModItems.PLASMA_CORE_FRAGMENTS.get(), 3 + random.nextInt(3)));
        spawnAtLocation(new ItemStack(Items.NETHERITE_SCRAP, 10 + random.nextInt(6)));
        spawnAtLocation(new ItemStack(THIPModItems.MONSTER_SOUL.get(), 3 + random.nextInt(3)));
    }
}
