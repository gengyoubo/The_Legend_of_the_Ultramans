package github.com.gengyoubo.TeShe.item;

import github.com.gengyoubo.TeShe.world.KeanAntonlaWorldData;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;

public class MutagenicInducerItem extends Item
{
    public MutagenicInducerItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) {
            return InteractionResultHolder.success(stack);
        }

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResultHolder.pass(stack);
        }

        if (!canEnableKeanAntonla(serverLevel, player)) {
            player.displayClientMessage(Component.translatable("message.teshe.mutagenic_inducer.invalid_biome"), true);
            return InteractionResultHolder.fail(stack);
        }

        KeanAntonlaWorldData data = KeanAntonlaWorldData.get(serverLevel);
        boolean alreadyEnabled = data.isWorldgenEnabled();
        data.enableWorldgen();
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        player.displayClientMessage(Component.translatable(alreadyEnabled
                ? "message.teshe.mutagenic_inducer.already_enabled"
                : "message.teshe.mutagenic_inducer.enabled"), true);
        serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BREWING_STAND_BREW, SoundSource.PLAYERS, 1.0F, 0.65F);
        return InteractionResultHolder.consume(stack);
    }

    private static boolean canEnableKeanAntonla(ServerLevel level, Player player)
    {
        return level.getBiome(player.blockPosition()).is(BiomeTags.IS_BADLANDS)
                || level.getBiome(player.blockPosition()).is(Biomes.DESERT);
    }
}
