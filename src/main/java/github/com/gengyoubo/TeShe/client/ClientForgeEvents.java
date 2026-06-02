package github.com.gengyoubo.TeShe.client;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.client.bossbar.BossBarRanderContext;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = TE.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEvents
{
    private static final ResourceLocation COSMIC_BULLIBARD_BOSS_BAR =
            ResourceLocation.fromNamespaceAndPath(TE.MODID, "textures/gui/boss_bar/img.png");
    private static final Component COSMIC_BULLIBARD_BOSS_NAME =
            Component.translatable("entity.teshe.cosmic_bullibard.boss");
    private static final BossBarRanderContext COSMIC_BULLIBARD_BOSS_BAR_CONTEXT =
            new BossBarRanderContext(COSMIC_BULLIBARD_BOSS_BAR);

    private ClientForgeEvents()
    {
    }

    @SubscribeEvent
    public static void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event)
    {
        if (!isCosmicBullibardBossBar(event)) {
            return;
        }

        try {
            int increment = COSMIC_BULLIBARD_BOSS_BAR_CONTEXT.render(
                    event.getGuiGraphics(),
                    Minecraft.getInstance().font,
                    event.getBossEvent().getName(),
                    event.getWindow().getGuiScaledWidth(),
                    event.getY(),
                    event.getBossEvent().getProgress()
            );
            event.setCanceled(true);
            event.setIncrement(increment);
        } catch (IOException ignored) {
            event.setCanceled(false);
        }
    }

    private static boolean isCosmicBullibardBossBar(CustomizeGuiOverlayEvent.BossEventProgress event)
    {
        return event.getBossEvent().getName().getString().equals(COSMIC_BULLIBARD_BOSS_NAME.getString());
    }
}
