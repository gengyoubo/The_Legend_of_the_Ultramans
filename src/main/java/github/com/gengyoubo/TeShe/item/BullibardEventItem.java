package github.com.gengyoubo.TeShe.item;

import github.com.gengyoubo.TeShe.event.ModCommonEvents;
import github.com.gengyoubo.TeShe.network.ModNetwork;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BullibardEventItem extends Item
{
    public BullibardEventItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (level instanceof ServerLevel serverLevel && ModCommonEvents.startBullibardStory(serverLevel, player)) {
            if (player instanceof ServerPlayer serverPlayer) {
                ModNetwork.sendItemActivation(serverPlayer, stack.copyWithCount(1));
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.consume(stack);
    }
}
