package github.com.gengyoubo.TeShe.client;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.client.renderer.BossBarRanderContext;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.util.List;

@Mod.EventBusSubscriber(modid = TE.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEvents
{
    private static final List<BossBarBinding> BOSS_BAR_BINDINGS = List.of(
            bossBar("entity.teshe.cosmic_bullibard.boss", "cosmic_bullibard_boss.png"),
            bossBar("entity.teshe.animated_meteor_blazmet.boss", "animated_meteor_blazmet_boss.png"),
            bossBar("entity.teshe.crystallize_black_king.boss", "crystallize_black_king_boss.png"),
            bossBar("entity.teshe.crystallizeblackking.boss", "crystallize_black_king_boss.png"),
            bossBar("entity.teshe.dark_soul_gespiket.boss", "dark_soul_gespiket_boss.png"),
            bossBar("entity.teshe.lebic_demon_form.boss", "lebic_demon_form_boss.png"),
            bossBar("entity.teshe.modified_bullibard.boss", "modified_bullibard_boss.png"),
            bossBar("entity.teshe.razor_demaga.boss", "razor_demaga_boss.png"),
            bossBar("entity.teshe.ringua_igonota.boss", "ringua_igonota_boss.png"),
            bossBar("entity.teshe.ruin_antonla.boss", "ruin_antonla_boss.png")
    );

    private ClientForgeEvents()
    {
    }

    @SubscribeEvent
    public static void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event)
    {
        BossBarBinding binding = findBossBarBinding(event);
        if (binding == null) {
            return;
        }

        try {
            int increment = binding.context.render(
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

    private static BossBarBinding findBossBarBinding(CustomizeGuiOverlayEvent.BossEventProgress event)
    {
        String eventName = event.getBossEvent().getName().getString();
        for (BossBarBinding binding : BOSS_BAR_BINDINGS) {
            if (eventName.equals(Component.translatable(binding.translationKey).getString())) {
                return binding;
            }
        }

        return null;
    }

    private static BossBarBinding bossBar(String translationKey, String textureName)
    {
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(TE.MODID, "textures/gui/boss_bar/" + textureName);
        return new BossBarBinding(translationKey, new BossBarRanderContext(texture));
    }

    private static final class BossBarBinding
    {
        private final String translationKey;
        private final BossBarRanderContext context;

        private BossBarBinding(String translationKey, BossBarRanderContext context)
        {
            this.translationKey = translationKey;
            this.context = context;
        }
    }
}
