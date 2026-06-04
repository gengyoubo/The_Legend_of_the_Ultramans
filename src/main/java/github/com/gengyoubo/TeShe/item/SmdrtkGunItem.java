package github.com.gengyoubo.TeShe.item;

import github.com.gengyoubo.TeShe.registry.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.thip.init.THIPModEffects;
import net.thip.init.THIPModItems;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SmdrtkGunItem extends GeckoModelItem
{
    public static final String SPACIUM_DOT_TICKS = "TesheSmdrtkSpaciumDotTicks";

    private static final String AMMO_QUEUE = "SmdrtkAmmoQueue";
    private static final String AMMO_TYPE = "Type";
    private static final double TRAIL_STEP = 1.25D;
    private static final double RANGE = 64.0D;

    private final GunKind gunKind;

    public SmdrtkGunItem(String geckoModelName, GunKind gunKind, Properties properties)
    {
        super(geckoModelName, properties);
        this.gunKind = gunKind;
    }

    public boolean isAutomatic()
    {
        return gunKind == GunKind.SMG;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResultHolder.pass(stack);
        }

        if (level.isClientSide) {
            return canLoadAmmo(stack, player) ? new InteractionResultHolder<>(InteractionResult.CONSUME, stack) : InteractionResultHolder.pass(stack);
        }

        if (loadAmmoFromInventory(stack, player)) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 0.75F, 1.25F);
            player.awardStat(Stats.ITEM_USED.get(this));
            return new InteractionResultHolder<>(InteractionResult.CONSUME, stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    public static boolean tryFireFromLeftClick(Player player)
    {
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof SmdrtkGunItem gun) || player.getCooldowns().isOnCooldown(gun)) {
            return false;
        }

        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return true;
        }

        boolean fired = gun.fire(serverLevel, player, stack);
        if (fired) {
            player.getCooldowns().addCooldown(gun, gun.gunKind.cooldownTicks);
            player.awardStat(Stats.ITEM_USED.get(gun));
            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, owner -> owner.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            }
        }

        return fired;
    }

    private boolean loadAmmoFromInventory(ItemStack gunStack, Player player)
    {
        ListTag queue = getAmmoQueue(gunStack);
        if (queue.size() >= gunKind.maxAmmo || (gunKind != GunKind.PISTOL && !queue.isEmpty())) {
            return false;
        }

        int loaded = 0;
        loaded += loadAmmoFromStack(queue, player.getOffhandItem(), player);
        for (ItemStack ammoStack : player.getInventory().items) {
            if (queue.size() >= gunKind.maxAmmo) {
                break;
            }
            loaded += loadAmmoFromStack(queue, ammoStack, player);
        }

        if (loaded > 0) {
            gunStack.getOrCreateTag().put(AMMO_QUEUE, queue);
        }

        return loaded > 0;
    }

    private boolean canLoadAmmo(ItemStack gunStack, Player player)
    {
        ListTag queue = getAmmoQueue(gunStack);
        if (queue.size() >= gunKind.maxAmmo || (gunKind != GunKind.PISTOL && !queue.isEmpty())) {
            return false;
        }

        if (AmmoType.fromStack(player.getOffhandItem(), gunKind) != null) {
            return true;
        }

        for (ItemStack ammoStack : player.getInventory().items) {
            if (AmmoType.fromStack(ammoStack, gunKind) != null) {
                return true;
            }
        }

        return false;
    }

    private int loadAmmoFromStack(ListTag queue, ItemStack ammoStack, Player player)
    {
        AmmoType ammoType = AmmoType.fromStack(ammoStack, gunKind);
        if (ammoType == null) {
            return 0;
        }

        int loaded = 0;
        if (player.getAbilities().instabuild) {
            while (queue.size() < gunKind.maxAmmo) {
                queue.add(createAmmoTag(ammoType));
                loaded++;
            }

            return loaded;
        }

        while (!ammoStack.isEmpty() && queue.size() < gunKind.maxAmmo) {
            queue.add(createAmmoTag(ammoType));
            ammoStack.shrink(1);
            loaded++;
        }

        return loaded;
    }

    private boolean fire(ServerLevel level, Player player, ItemStack stack)
    {
        AmmoType ammoType = pollAmmo(stack);
        if (ammoType == null) {
            return false;
        }

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
            target.hurt(level.damageSources().playerAttack(player), ammoType.damageFor(gunKind));
            if (target instanceof LivingEntity livingTarget) {
                applyAmmoEffect(livingTarget, ammoType);
            }
        }

        spawnTrail(level, start, hitLocation, ammoType);
        level.sendParticles(ammoType.particle, hitLocation.x, hitLocation.y, hitLocation.z, 18, 0.18D, 0.18D, 0.18D, 0.03D);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 0.75F, gunKind.pitch);
        return true;
    }

    private AmmoType pollAmmo(ItemStack stack)
    {
        CompoundTag tag = stack.getOrCreateTag();
        ListTag queue = getAmmoQueue(stack);
        if (queue.isEmpty()) {
            return null;
        }

        CompoundTag ammoTag = queue.getCompound(0);
        AmmoType ammoType = AmmoType.byId(ammoTag.getString(AMMO_TYPE));
        queue.remove(0);
        tag.put(AMMO_QUEUE, queue);
        return ammoType;
    }

    private static CompoundTag createAmmoTag(AmmoType ammoType)
    {
        CompoundTag ammoTag = new CompoundTag();
        ammoTag.putString(AMMO_TYPE, ammoType.id);
        return ammoTag;
    }

    private static ListTag getAmmoQueue(ItemStack stack)
    {
        return stack.getOrCreateTag().getList(AMMO_QUEUE, Tag.TAG_COMPOUND);
    }

    private static void applyAmmoEffect(LivingEntity target, AmmoType ammoType)
    {
        switch (ammoType) {
            case ICE -> {
                target.setTicksFrozen(Math.max(target.getTicksFrozen(), target.getTicksRequiredToFreeze() + 200));
                target.addEffect(new MobEffectInstance(THIPModEffects.FROZEN.get(), 200, 0));
            }
            case SPACIUM -> {
                int duration = target.getPersistentData().getInt(SPACIUM_DOT_TICKS);
                target.getPersistentData().putInt(SPACIUM_DOT_TICKS, Math.max(duration, ammoType.dotTicks));
            }
            case LANTERN -> {
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 600, 0));
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0));
            }
            case POISONOUS_POTATO -> target.addEffect(new MobEffectInstance(MobEffects.POISON, 400, 0));
            default -> {
            }
        }
    }

    private static void spawnTrail(ServerLevel level, Vec3 start, Vec3 end, AmmoType ammoType)
    {
        Vec3 path = end.subtract(start);
        double length = path.length();
        if (length <= 0.01D) {
            return;
        }

        Vec3 step = path.normalize().scale(TRAIL_STEP);
        for (double distance = 0.0D; distance < length; distance += TRAIL_STEP) {
            Vec3 particlePosition = start.add(step.scale(distance / TRAIL_STEP));
            level.sendParticles(ammoType.particle, particlePosition.x, particlePosition.y, particlePosition.z, 1, 0.01D, 0.01D, 0.01D, 0.0D);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        ListTag queue = getAmmoQueue(stack);
        tooltip.add(Component.translatable("item.teshe.smdrtk_gun.loaded", queue.size(), gunKind.maxAmmo).withStyle(ChatFormatting.AQUA));
        if (!queue.isEmpty()) {
            tooltip.add(Component.translatable("item.teshe.smdrtk_gun.next_ammo", AmmoType.byId(queue.getCompound(0).getString(AMMO_TYPE)).displayName()).withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("item.teshe.smdrtk_gun.ammo_summary", getAmmoSummary(queue)).withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    private static Component getAmmoSummary(ListTag queue)
    {
        Map<AmmoType, Integer> counts = new LinkedHashMap<>();
        for (int i = 0; i < queue.size(); i++) {
            AmmoType ammoType = AmmoType.byId(queue.getCompound(i).getString(AMMO_TYPE));
            counts.put(ammoType, counts.getOrDefault(ammoType, 0) + 1);
        }

        MutableComponent summary = Component.empty();
        for (Map.Entry<AmmoType, Integer> entry : counts.entrySet()) {
            if (!summary.getString().isEmpty()) {
                summary.append(", ");
            }
            summary.append(Component.literal(entry.getValue() + "x "));
            summary.append(entry.getKey().displayName());
        }

        return summary;
    }

    public enum GunKind
    {
        PISTOL(10, 18, 1.25F),
        SMG(100, 3, 1.65F),
        RIFLE(25, 9, 0.95F);

        private final int maxAmmo;
        private final int cooldownTicks;
        private final float pitch;

        GunKind(int maxAmmo, int cooldownTicks, float pitch)
        {
            this.maxAmmo = maxAmmo;
            this.cooldownTicks = cooldownTicks;
            this.pitch = pitch;
        }
    }

    private enum AmmoType
    {
        ICE("ice", new DustParticleOptions(new Vector3f(0.25F, 0.55F, 1.0F), 1.35F), 20.0F, 0.0F, 0.0F, 0),
        REDSTONE("redstone", new DustParticleOptions(new Vector3f(1.0F, 0.05F, 0.03F), 1.35F), 60.0F, 100.0F, 400.0F, 0),
        SPACIUM("spacium", new DustParticleOptions(new Vector3f(1.0F, 1.0F, 1.0F), 1.45F), 100.0F, 200.0F, 600.0F, 60),
        LANTERN("lantern", new DustParticleOptions(new Vector3f(1.0F, 0.82F, 0.1F), 1.35F), 20.0F, 0.0F, 0.0F, 0),
        POISONOUS_POTATO("poisonous_potato", new DustParticleOptions(new Vector3f(0.15F, 0.85F, 0.2F), 1.35F), 30.0F, 0.0F, 0.0F, 0);

        private final String id;
        private final DustParticleOptions particle;
        private final float pistolDamage;
        private final float smgDamage;
        private final float rifleDamage;
        private final int dotTicks;

        AmmoType(String id, DustParticleOptions particle, float pistolDamage, float smgDamage, float rifleDamage, int dotTicks)
        {
            this.id = id;
            this.particle = particle;
            this.pistolDamage = pistolDamage;
            this.smgDamage = smgDamage;
            this.rifleDamage = rifleDamage;
            this.dotTicks = dotTicks;
        }

        private float damageFor(GunKind gunKind)
        {
            return switch (gunKind) {
                case PISTOL -> pistolDamage;
                case SMG -> smgDamage;
                case RIFLE -> rifleDamage;
            };
        }

        private static AmmoType fromStack(ItemStack stack, GunKind gunKind)
        {
            if (stack.isEmpty()) {
                return null;
            }

            if (stack.is(Items.REDSTONE)) {
                return REDSTONE;
            }
            if (stack.is(THIPModItems.SPACIUM_SUBSTANCE.get())) {
                return SPACIUM;
            }
            if (gunKind != GunKind.PISTOL) {
                return null;
            }

            if (stack.is(Items.ICE)) {
                return ICE;
            }
            if (stack.is(Items.LANTERN)) {
                return LANTERN;
            }
            if (stack.is(Items.POISONOUS_POTATO)) {
                return POISONOUS_POTATO;
            }

            return null;
        }

        private static AmmoType byId(String id)
        {
            for (AmmoType ammoType : values()) {
                if (ammoType.id.equals(id)) {
                    return ammoType;
                }
            }

            return REDSTONE;
        }

        private Component displayName()
        {
            return Component.translatable("item.teshe.smdrtk_gun.ammo." + id);
        }
    }
}
