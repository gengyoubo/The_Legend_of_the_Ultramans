package github.com.gengyoubo.TeShe.item;

import net.minecraft.ChatFormatting;
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
import net.minecraft.core.particles.ParticleTypes;

import javax.annotation.Nullable;
import java.util.List;

public class SmdrtkMultiFunctionPistolItem extends GeckoModelItem
{
    private static final float DAMAGE = 8.0F;
    private static final double RANGE = 48.0D;
    private static final int COOLDOWN_TICKS = 8;
    private static final double TRAIL_STEP = 1.5D;

    public SmdrtkMultiFunctionPistolItem(Properties properties)
    {
        super("smdrtk_multi_function_pistol", properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            fire((ServerLevel) level, player);
            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
            player.awardStat(Stats.ITEM_USED.get(this));

            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, owner -> owner.broadcastBreakEvent(hand));
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private void fire(ServerLevel level, Player player)
    {
        Vec3 start = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = start.add(look.scale(RANGE));

        HitResult blockHit = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        Vec3 hitLocation = blockHit.getLocation();
        double hitDistanceSqr = start.distanceToSqr(hitLocation);

        AABB searchBox = player.getBoundingBox().expandTowards(look.scale(RANGE)).inflate(1.0D);
        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                level,
                player,
                start,
                end,
                searchBox,
                entity -> entity.isPickable() && !entity.isSpectator()
        );

        if (entityHit != null && start.distanceToSqr(entityHit.getLocation()) <= hitDistanceSqr) {
            Entity target = entityHit.getEntity();
            hitLocation = entityHit.getLocation();
            target.hurt(level.damageSources().playerAttack(player), DAMAGE);

            if (target instanceof LivingEntity livingTarget) {
                livingTarget.knockback(0.35D, -look.x, -look.z);
            }
        }

        spawnTrail(level, start, hitLocation);
        level.sendParticles(ParticleTypes.END_ROD, hitLocation.x, hitLocation.y, hitLocation.z, 10, 0.08D, 0.08D, 0.08D, 0.02D);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 0.8F, 1.45F);
    }

    private void spawnTrail(ServerLevel level, Vec3 start, Vec3 end)
    {
        Vec3 path = end.subtract(start);
        double length = path.length();
        Vec3 step = path.normalize().scale(TRAIL_STEP);

        for (double distance = 0.0D; distance < length; distance += TRAIL_STEP) {
            Vec3 particlePosition = start.add(step.scale(distance / TRAIL_STEP));
            level.sendParticles(ParticleTypes.ELECTRIC_SPARK, particlePosition.x, particlePosition.y, particlePosition.z, 1, 0.01D, 0.01D, 0.01D, 0.0D);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        tooltip.add(Component.translatable("item.teshe.smdrtk_multi_function_pistol.tooltip").withStyle(ChatFormatting.AQUA));
    }
}
