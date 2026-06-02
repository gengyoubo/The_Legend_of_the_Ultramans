package github.com.gengyoubo.TeShe.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HatredStickItem extends Item
{
    private static final String SOURCE_ENTITY_UUID = "SourceEntity";
    private static final Map<UUID, UUID> SELECTED_SOURCES = new HashMap<>();

    public HatredStickItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand)
    {
        Level level = player.level();
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (player.isShiftKeyDown()) {
            clearSource(stack, player);
            return InteractionResult.SUCCESS;
        }

        selectSource(stack, player, target);
        return InteractionResult.SUCCESS;
    }

    private static void selectSource(ItemStack stack, Player player, LivingEntity target)
    {
        if (!(target instanceof Mob)) {
            player.displayClientMessage(Component.translatable("item.teshe.hatred_stick.message.source_must_be_mob"), true);
            return;
        }

        stack.getOrCreateTag().putUUID(SOURCE_ENTITY_UUID, target.getUUID());
        SELECTED_SOURCES.put(player.getUUID(), target.getUUID());
        player.displayClientMessage(Component.translatable("item.teshe.hatred_stick.message.source_selected", target.getDisplayName()), true);
    }

    public static void applyHatred(ItemStack stack, Player player, LivingEntity target, ServerLevel level)
    {
        UUID sourceUuid = getSelectedSource(stack, player);
        if (sourceUuid == null) {
            player.displayClientMessage(Component.translatable("item.teshe.hatred_stick.message.no_source"), true);
            return;
        }

        Entity sourceEntity = level.getEntity(sourceUuid);
        if (!(sourceEntity instanceof Mob)) {
            clearSource(stack, player);
            player.displayClientMessage(Component.translatable("item.teshe.hatred_stick.message.source_missing"), true);
            return;
        }

        Mob source = (Mob)sourceEntity;
        if (source == target) {
            player.displayClientMessage(Component.translatable("item.teshe.hatred_stick.message.same_entity"), true);
            return;
        }

        source.setTarget(target);
        clearSourceData(stack, player);
        player.displayClientMessage(Component.translatable("item.teshe.hatred_stick.message.applied", source.getDisplayName(), target.getDisplayName()), true);
    }

    private static void clearSource(ItemStack stack, Player player)
    {
        clearSourceData(stack, player);
        player.displayClientMessage(Component.translatable("item.teshe.hatred_stick.message.cleared"), true);
    }

    private static UUID getSelectedSource(ItemStack stack, Player player)
    {
        UUID sourceUuid = SELECTED_SOURCES.get(player.getUUID());
        if (sourceUuid != null) {
            return sourceUuid;
        }

        if (stack.hasTag() && stack.getTag().hasUUID(SOURCE_ENTITY_UUID)) {
            return stack.getTag().getUUID(SOURCE_ENTITY_UUID);
        }

        return null;
    }

    private static void clearSourceData(ItemStack stack, Player player)
    {
        if (stack.hasTag()) {
            stack.getTag().remove(SOURCE_ENTITY_UUID);
        }
        SELECTED_SOURCES.remove(player.getUUID());
    }
}
