package github.com.gengyoubo.TeShe.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.List;

public class BoosterCrystalLiberatorItem extends GeckoModelItem
{
    public static final String SHIELD_UNTIL_TICK = "TesheBoosterLiberatorShieldUntil";
    public static final String SLASH_UNTIL_TICK = "TesheBoosterLiberatorSlashUntil";
    private static final String MODE_TAG = "BoosterLiberatorMode";
    private static final double RANGE = 48.0D;
    private static final double TRAIL_STEP = 1.2D;
    private static final DustParticleOptions YELLOW_PARTICLE = new DustParticleOptions(new Vector3f(1.0F, 0.86F, 0.15F), 1.25F);
    private static final DustParticleOptions BLUE_PARTICLE = new DustParticleOptions(new Vector3f(0.2F, 0.55F, 1.0F), 1.25F);

    public BoosterCrystalLiberatorItem(Properties properties)
    {
        super("booster_crystal_liberator", properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResultHolder.pass(stack);
        }

        if (player.isShiftKeyDown()) {
            cycleMode(stack, player);
            return InteractionResultHolder.consume(stack);
        }

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResultHolder.consume(stack);
        }

        LiberatorMode mode = getMode(stack);
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.consume(stack);
        }

        switch (mode) {
            case CUTTER -> fireCutter(serverLevel, player);
            case RAY -> fireRay(serverLevel, player);
            case SHIELD -> activateShield(serverLevel, player);
            case SLASH -> activateSlash(serverLevel, player);
        }

        player.getCooldowns().addCooldown(this, mode.cooldownTicks);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        tooltip.add(Component.translatable("item.teshe.booster_crystal_liberator.mode", getMode(stack).displayName()).withStyle(ChatFormatting.AQUA));
    }

    private static void cycleMode(ItemStack stack, Player player)
    {
        LiberatorMode nextMode = LiberatorMode.values()[(getMode(stack).ordinal() + 1) % LiberatorMode.values().length];
        stack.getOrCreateTag().putInt(MODE_TAG, nextMode.ordinal());
        if (!player.level().isClientSide()) {
            player.displayClientMessage(Component.translatable("item.teshe.booster_crystal_liberator.mode", nextMode.displayName()), true);
        }
    }

    private static LiberatorMode getMode(ItemStack stack)
    {
        CompoundTag tag = stack.getOrCreateTag();
        int mode = tag.getInt(MODE_TAG);
        if (mode < 0 || mode >= LiberatorMode.values().length) {
            return LiberatorMode.CUTTER;
        }

        return LiberatorMode.values()[mode];
    }

    private static void fireCutter(ServerLevel level, Player player)
    {
        EntityHitResult hit = fireRayLikeAttack(level, player, 5.0F, 3, true, YELLOW_PARTICLE);
        Vec3 hitLocation = hit == null ? player.getEyePosition().add(player.getViewVector(1.0F).scale(RANGE)) : hit.getLocation();
        level.sendParticles(YELLOW_PARTICLE, hitLocation.x, hitLocation.y, hitLocation.z, 12, 0.18D, 0.18D, 0.18D, 0.02D);
    }

    private static void fireRay(ServerLevel level, Player player)
    {
        fireRayLikeAttack(level, player, 15.0F, 10, true, BLUE_PARTICLE);
    }

    private static void activateShield(ServerLevel level, Player player)
    {
        player.getPersistentData().putLong(SHIELD_UNTIL_TICK, level.getGameTime() + 200L);
        player.setAbsorptionAmount(Math.max(player.getAbsorptionAmount(), 20.0F));
        level.sendParticles(BLUE_PARTICLE, player.getX(), player.getY() + 1.0D, player.getZ(), 32, 0.45D, 0.75D, 0.45D, 0.02D);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.8F, 1.35F);
    }

    private static void activateSlash(ServerLevel level, Player player)
    {
        player.getPersistentData().putLong(SLASH_UNTIL_TICK, level.getGameTime() + 200L);
        level.sendParticles(YELLOW_PARTICLE, player.getX(), player.getY() + 1.0D, player.getZ(), 24, 0.35D, 0.6D, 0.35D, 0.02D);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 0.8F, 1.2F);
    }

    private static EntityHitResult fireRayLikeAttack(ServerLevel level, Player player, float damage, int hits, boolean ignoreInvulnerability, DustParticleOptions particle)
    {
        Vec3 start = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = start.add(look.scale(RANGE));
        HitResult blockHit = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        Vec3 hitLocation = blockHit.getLocation();
        double hitDistanceSqr = start.distanceToSqr(hitLocation);
        AABB searchBox = player.getBoundingBox().expandTowards(look.scale(RANGE)).inflate(1.0D);
        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(level, player, start, end, searchBox, entity -> entity.isPickable() && !entity.isSpectator());
        if (entityHit != null && start.distanceToSqr(entityHit.getLocation()) <= hitDistanceSqr) {
            hitLocation = entityHit.getLocation();
            Entity target = entityHit.getEntity();
            for (int i = 0; i < hits; i++) {
                if (ignoreInvulnerability && target instanceof LivingEntity livingTarget) {
                    livingTarget.invulnerableTime = 0;
                    livingTarget.hurtTime = 0;
                }
                target.hurt(level.damageSources().playerAttack(player), damage);
            }
        }

        spawnMixedTrail(level, start, hitLocation, particle);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 0.75F, 1.45F);
        return entityHit;
    }

    private static void spawnMixedTrail(ServerLevel level, Vec3 start, Vec3 end, DustParticleOptions primary)
    {
        Vec3 path = end.subtract(start);
        double length = path.length();
        if (length <= 0.01D) {
            return;
        }

        Vec3 step = path.normalize().scale(TRAIL_STEP);
        for (double distance = 0.0D; distance < length; distance += TRAIL_STEP) {
            Vec3 particlePosition = start.add(step.scale(distance / TRAIL_STEP));
            level.sendParticles(primary, particlePosition.x, particlePosition.y, particlePosition.z, 1, 0.01D, 0.01D, 0.01D, 0.0D);
            level.sendParticles(YELLOW_PARTICLE, particlePosition.x, particlePosition.y, particlePosition.z, 1, 0.01D, 0.01D, 0.01D, 0.0D);
        }
    }

    private enum LiberatorMode
    {
        CUTTER(60),
        RAY(200),
        SHIELD(600),
        SLASH(300);

        private final int cooldownTicks;

        LiberatorMode(int cooldownTicks)
        {
            this.cooldownTicks = cooldownTicks;
        }

        private Component displayName()
        {
            return Component.translatable("item.teshe.booster_crystal_liberator.mode." + name().toLowerCase());
        }
    }
}
