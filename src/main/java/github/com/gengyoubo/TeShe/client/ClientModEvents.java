package github.com.gengyoubo.TeShe.client;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.client.renderer.GenericGeoEntityRenderer;
import github.com.gengyoubo.TeShe.registry.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents
{
    private ClientModEvents()
    {
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntityTypes.CHICKEN.get(), context -> new GenericGeoEntityRenderer<>(context, "chicken", 0.3F));
        event.registerEntityRenderer(ModEntityTypes.GUARDIAN_ELDER.get(), context -> new GenericGeoEntityRenderer<>(context, "guardian_elder", 0.8F));
    }
}
