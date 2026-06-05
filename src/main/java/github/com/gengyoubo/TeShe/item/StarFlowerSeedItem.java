package github.com.gengyoubo.TeShe.item;

import github.com.gengyoubo.TeShe.registry.ModMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StarFlowerSeedItem extends Item
{
    private static final int EFFECT_DURATION_TICKS = 6000;

    public StarFlowerSeedItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (level instanceof ServerLevel serverLevel) {
            if (serverLevel.random.nextBoolean()) {
                player.addEffect(new MobEffectInstance(ModMobEffects.LIGHT_SEED.get(), EFFECT_DURATION_TICKS, 0, false, true, true));
            } else {
                player.addEffect(new MobEffectInstance(ModMobEffects.PLUNDER.get(), EFFECT_DURATION_TICKS, 0, false, true, true));
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.consume(stack);
    }
}
