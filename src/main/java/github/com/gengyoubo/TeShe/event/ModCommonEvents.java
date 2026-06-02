package github.com.gengyoubo.TeShe.event;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.item.HatredStickItem;
import github.com.gengyoubo.TeShe.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TE.MODID)
public final class ModCommonEvents
{
    private ModCommonEvents()
    {
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event)
    {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        if (!stack.is(ModItems.HATRED_STICK.get()) || !(event.getTarget() instanceof LivingEntity target)) {
            return;
        }

        event.setCanceled(true);
        if (player.level().isClientSide()) {
            return;
        }

        HatredStickItem.applyHatred(stack, player, target, (ServerLevel)player.level());
    }
}
