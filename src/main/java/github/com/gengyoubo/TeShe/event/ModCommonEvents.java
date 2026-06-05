package github.com.gengyoubo.TeShe.event;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.CosmicBullibardEntity;
import github.com.gengyoubo.TeShe.item.BoosterCrystalLiberatorItem;
import github.com.gengyoubo.TeShe.item.HatredStickItem;
import github.com.gengyoubo.TeShe.item.SmdrtkGunItem;
import github.com.gengyoubo.TeShe.network.ModNetwork;
import github.com.gengyoubo.TeShe.registry.ModEntityTypes;
import github.com.gengyoubo.TeShe.registry.ModItems;
import github.com.gengyoubo.TeShe.registry.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.thip.entity.projectile.LargeProjectile;
import org.joml.Vector3f;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = TE.MODID)
public final class ModCommonEvents
{
    private static final String SMDRTK_ABSORPTION_REFRESH_TIME = "TesheSmdrtkAbsorptionRefreshTime";
    private static final float SMDRTK_DAMAGE_REDUCTION_PER_PIECE = 0.0625F;
    private static final int SMDRTK_FATAL_PROTECTION_DURABILITY_COST = 1000;
    private static final int SMDRTK_EFFECT_DURATION_TICKS = 260;
    private static final int SMDRTK_ABSORPTION_REFRESH_TICKS = 200;
    private static final float SMDRTK_ABSORPTION_AMOUNT = 20.0F;
    private static final int SMDRTK_TEAM_LOGO_COOLDOWN_TICKS = 200;
    private static final float SPACIUM_DOT_DAMAGE = 20.0F;
    private static final float BOOSTER_LIBERATOR_PASSIVE_REDUCTION = 0.25F;
    private static final float BOOSTER_LIBERATOR_SHIELD_REDUCTION = 0.8F;
    private static final int BOOSTER_LIBERATOR_EFFECT_DURATION_TICKS = 260;
    private static final int BULLIBARD_STORY_LINE_INTERVAL_TICKS = 60;
    private static final DustParticleOptions BOOSTER_BLUE_PARTICLE = new DustParticleOptions(new Vector3f(0.2F, 0.55F, 1.0F), 1.4F);
    private static final DustParticleOptions BOOSTER_GOLD_PARTICLE = new DustParticleOptions(new Vector3f(1.0F, 0.82F, 0.1F), 1.4F);
    private static final Map<UUID, BullibardStoryState> BULLIBARD_STORIES = new ConcurrentHashMap<>();
    private static final String[] BULLIBARD_STORY_LINES = {
            "<独孤灵蛇>：队长！有高速物体接近城市！",
            "<南宫逸纪>：立刻通知在那里的李甜副队长，灵蛇，你去现场辅助副队长疏散人员。",
            "<独孤灵蛇>：是！",
            "……",
            "<灵蛇声线的旁白>：就在李甜她们撤离时，怪兽也到达了，因此，灵蛇不得不变身迎战。",
            "<李甜>：竟然是这家伙嘛！喂！巨人！它的羽毛能反射光线！先拔了它的毛！"
    };

    private ModCommonEvents()
    {
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event)
    {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        if (SmdrtkGunItem.tryFireFromLeftClick(player)) {
            event.setCanceled(true);
            return;
        }

        if (!stack.is(ModItems.HATRED_STICK.get()) || !(event.getTarget() instanceof LivingEntity target)) {
            return;
        }

        event.setCanceled(true);
        if (player.level().isClientSide()) {
            return;
        }

        HatredStickItem.applyHatred(stack, player, target, (ServerLevel)player.level());
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event)
    {
        if (event.getSource().getEntity() instanceof Player attacker
                && event.getEntity() != attacker
                && hasBoosterLiberator(attacker)) {
            long gameTime = attacker.level().getGameTime();
            boolean slashActive = attacker.getPersistentData().getLong(BoosterCrystalLiberatorItem.SLASH_UNTIL_TICK) > gameTime;
            event.setAmount(Math.max(event.getAmount(), slashActive ? 40.0F : 20.0F));
            if (slashActive && event.getEntity().level() instanceof ServerLevel serverLevel) {
                LivingEntity target = event.getEntity();
                serverLevel.sendParticles(BOOSTER_BLUE_PARTICLE, target.getX(), target.getY() + target.getBbHeight() * 0.5D, target.getZ(), 12, 0.35D, 0.45D, 0.35D, 0.02D);
                serverLevel.sendParticles(BOOSTER_GOLD_PARTICLE, target.getX(), target.getY() + target.getBbHeight() * 0.5D, target.getZ(), 12, 0.35D, 0.45D, 0.35D, 0.02D);
                serverLevel.sendParticles(ParticleTypes.FIREWORK, target.getX(), target.getY() + target.getBbHeight() * 0.5D, target.getZ(), 8, 0.25D, 0.35D, 0.25D, 0.02D);
            }
        }

        Entity directEntity = event.getSource().getDirectEntity();
        if (!(directEntity instanceof LargeProjectile projectile)
                || !(projectile.getOwner() instanceof CosmicBullibardEntity)
                || !CosmicBullibardEntity.isBullibardBeamProjectile(projectile)) {
            return;
        }

        LivingEntity target = event.getEntity();
        target.invulnerableTime = 0;
        target.hurtTime = 0;
        if (target.level() instanceof ServerLevel serverLevel) {
            CosmicBullibardEntity.handleBullibardBeamHit(serverLevel, projectile, target);
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event)
    {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        float amount = event.getAmount();
        int armorPieces = countSmdrtkArmorPieces(player);
        if (armorPieces > 0) {
            amount *= Math.max(0.0F, 1.0F - armorPieces * SMDRTK_DAMAGE_REDUCTION_PER_PIECE);
        }

        if (hasBoosterLiberator(player)) {
            amount *= 1.0F - BOOSTER_LIBERATOR_PASSIVE_REDUCTION;
        }

        if (player.getPersistentData().getLong(BoosterCrystalLiberatorItem.SHIELD_UNTIL_TICK) > player.level().getGameTime()) {
            amount *= 1.0F - BOOSTER_LIBERATOR_SHIELD_REDUCTION;
        }

        if (amount >= player.getHealth() && tryUseSmdrtkTeamLogo(player)) {
            event.setAmount(0.0F);
            player.setHealth(Math.max(player.getHealth(), 1.0F));
            return;
        }

        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        if (amount >= player.getHealth() && chestplate.is(ModItems.SMDRTK_CHESTPLATE.get())) {
            chestplate.hurtAndBreak(SMDRTK_FATAL_PROTECTION_DURABILITY_COST, player, owner -> owner.broadcastBreakEvent(EquipmentSlot.CHEST));
            event.setAmount(0.0F);
            player.setHealth(Math.max(player.getHealth(), 1.0F));
            return;
        }

        event.setAmount(amount);
    }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        if (event.getAction() != PlayerInteractEvent.LeftClickBlock.Action.START) {
            return;
        }

        if (SmdrtkGunItem.tryFireFromLeftClick(event.getEntity())) {
            event.setCanceled(true);
            event.setUseBlock(net.minecraftforge.eventbus.api.Event.Result.DENY);
            event.setUseItem(net.minecraftforge.eventbus.api.Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event)
    {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide()) {
            return;
        }

        if (entity.hasEffect(ModMobEffects.LIGHT_SEED.get())) {
            removeHarmfulEffects(entity);
        }

        int spaciumDotTicks = entity.getPersistentData().getInt(SmdrtkGunItem.SPACIUM_DOT_TICKS);
        if (spaciumDotTicks <= 0) {
            return;
        }

        if (entity.tickCount % 20 == 0) {
            entity.hurt(entity.damageSources().magic(), SPACIUM_DOT_DAMAGE);
        }

        entity.getPersistentData().putInt(SmdrtkGunItem.SPACIUM_DOT_TICKS, spaciumDotTicks - 1);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) {
            return;
        }

        Player player = event.player;
        boolean helmet = player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.SMDRTK_HELMET.get());
        boolean chestplate = player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.SMDRTK_CHESTPLATE.get());
        boolean leggings = player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.SMDRTK_LEGGINGS.get());
        boolean boots = player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.SMDRTK_BOOTS.get());
        boolean fullSet = helmet && chestplate && leggings && boots;

        if (player.tickCount % 20 != 0) {
            return;
        }

        if (helmet) {
            addEffect(player, MobEffects.NIGHT_VISION, 0);
            addEffect(player, MobEffects.WATER_BREATHING, 0);
        }

        int speedAmplifier = -1;
        if (leggings || boots) {
            speedAmplifier = 0;
        }

        if (fullSet) {
            addEffect(player, MobEffects.DAMAGE_BOOST, 1);
            speedAmplifier = 2;
            refreshSmdrtkAbsorption(player);
        }

        if (speedAmplifier >= 0) {
            addEffect(player, MobEffects.MOVEMENT_SPEED, speedAmplifier);
        }

        if (hasBoosterLiberator(player)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, BOOSTER_LIBERATOR_EFFECT_DURATION_TICKS, 2, true, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, BOOSTER_LIBERATOR_EFFECT_DURATION_TICKS, 2, true, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, BOOSTER_LIBERATOR_EFFECT_DURATION_TICKS, 2, true, false, true));
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END || BULLIBARD_STORIES.isEmpty()) {
            return;
        }

        tickBullibardStories(event.getServer());
    }

    @SubscribeEvent
    public static void onMobEffectApplicable(MobEffectEvent.Applicable event)
    {
        MobEffectInstance effect = event.getEffectInstance();
        if (effect != null
                && effect.getEffect().getCategory() == MobEffectCategory.HARMFUL
                && event.getEntity().hasEffect(ModMobEffects.LIGHT_SEED.get())) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void onLootingLevel(LootingLevelEvent event)
    {
        if (event.getDamageSource().getEntity() instanceof Player player && player.hasEffect(ModMobEffects.PLUNDER.get())) {
            event.setLootingLevel(Math.max(event.getLootingLevel(), 10));
        }
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event)
    {
        if (!(event.getSource().getEntity() instanceof Player player)
                || !player.hasEffect(ModMobEffects.PLUNDER.get())
                || !(player.level() instanceof ServerLevel level)
                || level.random.nextFloat() >= 0.25F) {
            return;
        }

        List<ItemEntity> copies = new ArrayList<>();
        for (ItemEntity drop : event.getDrops()) {
            ItemStack copy = drop.getItem().copy();
            if (!copy.isEmpty()) {
                copies.add(new ItemEntity(level, drop.getX(), drop.getY(), drop.getZ(), copy));
            }
        }

        event.getDrops().addAll(copies);
    }

    public static boolean startBullibardStory(ServerLevel level, Player player)
    {
        UUID playerId = player.getUUID();
        if (BULLIBARD_STORIES.containsKey(playerId)) {
            return false;
        }
        if (!hasOpenBullibardEventSpace(level, player)) {
            player.displayClientMessage(Component.translatable("message.teshe.bullibard_event.not_enough_space"), true);
            return false;
        }

        BULLIBARD_STORIES.put(playerId, new BullibardStoryState(level.dimension(), playerId));
        level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, player.getX(), player.getY() + 1.0D, player.getZ(), 48, 0.4D, 0.7D, 0.4D, 0.02D);
        return true;
    }

    private static void addEffect(Player player, net.minecraft.world.effect.MobEffect effect, int amplifier)
    {
        player.addEffect(new MobEffectInstance(effect, SMDRTK_EFFECT_DURATION_TICKS, amplifier, true, false, true));
    }

    private static void refreshSmdrtkAbsorption(Player player)
    {
        long gameTime = player.level().getGameTime();
        long lastRefreshTime = player.getPersistentData().getLong(SMDRTK_ABSORPTION_REFRESH_TIME);
        if (lastRefreshTime > 0 && gameTime - lastRefreshTime < SMDRTK_ABSORPTION_REFRESH_TICKS) {
            return;
        }

        player.getPersistentData().putLong(SMDRTK_ABSORPTION_REFRESH_TIME, gameTime);
        player.setAbsorptionAmount(Math.max(player.getAbsorptionAmount(), SMDRTK_ABSORPTION_AMOUNT));
    }

    private static boolean tryUseSmdrtkTeamLogo(Player player)
    {
        if (player.getCooldowns().isOnCooldown(ModItems.SMDRTK_TEAM_LOGO.get())) {
            return false;
        }

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offhand = player.getOffhandItem();
        if (!mainHand.is(ModItems.SMDRTK_TEAM_LOGO.get()) && !offhand.is(ModItems.SMDRTK_TEAM_LOGO.get())) {
            return false;
        }

        player.getCooldowns().addCooldown(ModItems.SMDRTK_TEAM_LOGO.get(), SMDRTK_TEAM_LOGO_COOLDOWN_TICKS);
        if (player.level() instanceof ServerLevel serverLevel) {
            if (player instanceof ServerPlayer serverPlayer) {
                ItemStack activationStack = mainHand.is(ModItems.SMDRTK_TEAM_LOGO.get()) ? mainHand : offhand;
                ModNetwork.sendItemActivation(serverPlayer, activationStack.copyWithCount(1));
            }
            serverLevel.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, player.getX(), player.getY() + 1.0D, player.getZ(), 32, 0.35D, 0.6D, 0.35D, 0.02D);
            serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 0.9F, 1.0F);
        }

        return true;
    }

    private static int countSmdrtkArmorPieces(Player player)
    {
        int count = 0;
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.SMDRTK_HELMET.get())) {
            count++;
        }
        if (player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.SMDRTK_CHESTPLATE.get())) {
            count++;
        }
        if (player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.SMDRTK_LEGGINGS.get())) {
            count++;
        }
        if (player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.SMDRTK_BOOTS.get())) {
            count++;
        }

        return count;
    }

    private static void removeHarmfulEffects(LivingEntity entity)
    {
        List<MobEffect> harmfulEffects = new ArrayList<>();
        for (MobEffectInstance effect : entity.getActiveEffects()) {
            if (effect.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                harmfulEffects.add(effect.getEffect());
            }
        }

        for (MobEffect effect : harmfulEffects) {
            entity.removeEffect(effect);
        }
    }

    private static boolean hasBoosterLiberator(Player player)
    {
        if (player.getMainHandItem().is(ModItems.BOOSTER_CRYSTAL_LIBERATOR.get())
                || player.getOffhandItem().is(ModItems.BOOSTER_CRYSTAL_LIBERATOR.get())) {
            return true;
        }

        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ModItems.BOOSTER_CRYSTAL_LIBERATOR.get())) {
                return true;
            }
        }

        return false;
    }

    private static void tickBullibardStories(MinecraftServer server)
    {
        Iterator<Map.Entry<UUID, BullibardStoryState>> iterator = BULLIBARD_STORIES.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, BullibardStoryState> entry = iterator.next();
            BullibardStoryState state = entry.getValue();
            ServerLevel level = server.getLevel(state.dimension);
            ServerPlayer player = server.getPlayerList().getPlayer(state.playerId);
            if (level == null || player == null || player.isRemoved()) {
                iterator.remove();
                continue;
            }

            state.ticks++;
            if (state.ticks % BULLIBARD_STORY_LINE_INTERVAL_TICKS != 1) {
                continue;
            }

            if (state.lineIndex < BULLIBARD_STORY_LINES.length) {
                player.sendSystemMessage(Component.literal(BULLIBARD_STORY_LINES[state.lineIndex]));
                state.lineIndex++;
                continue;
            }

            spawnBullibardBoss(level, player);
            iterator.remove();
        }
    }

    private static void spawnBullibardBoss(ServerLevel level, ServerPlayer player)
    {
        CosmicBullibardEntity boss = ModEntityTypes.COSMIC_BULLIBARD.get().create(level);
        if (boss == null) {
            return;
        }

        Vec3 look = player.getLookAngle();
        Vec3 horizontalLook = new Vec3(look.x, 0.0D, look.z);
        if (horizontalLook.lengthSqr() < 0.001D) {
            double yawRadians = Math.toRadians(player.getYRot());
            horizontalLook = new Vec3(-Math.sin(yawRadians), 0.0D, Math.cos(yawRadians));
        }

        Vec3 spawnCenter = player.position().add(horizontalLook.normalize().scale(4.0D));
        BlockPos spawnColumn = BlockPos.containing(spawnCenter.x, player.getY(), spawnCenter.z);
        BlockPos spawnPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, spawnColumn);
        boss.setSpecialIndividual(CosmicBullibardEntity.BOSS_VARIANT);
        boss.moveTo(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D, player.getYRot() + 180.0F, 0.0F);
        boss.finalizeSpawn(level, level.getCurrentDifficultyAt(spawnPos), MobSpawnType.EVENT, null, null);
        boss.setSpecialIndividual(CosmicBullibardEntity.BOSS_VARIANT);
        level.addFreshEntity(boss);
    }

    private static boolean hasOpenBullibardEventSpace(ServerLevel level, Player player)
    {
        BlockPos center = player.blockPosition();
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-2, 0, -2), center.offset(2, 4, 2))) {
            BlockState state = level.getBlockState(pos);
            if (!state.getCollisionShape(level, pos).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private static final class BullibardStoryState
    {
        private final ResourceKey<Level> dimension;
        private final UUID playerId;
        private int ticks;
        private int lineIndex;

        private BullibardStoryState(ResourceKey<Level> dimension, UUID playerId)
        {
            this.dimension = dimension;
            this.playerId = playerId;
        }
    }
}
