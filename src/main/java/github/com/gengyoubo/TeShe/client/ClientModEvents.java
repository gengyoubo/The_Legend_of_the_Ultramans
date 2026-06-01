package github.com.gengyoubo.TeShe.client;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.client.renderer.GenericGeoEntityRenderer;
import github.com.gengyoubo.TeShe.entity.TesheGeoMob;
import github.com.gengyoubo.TeShe.registry.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents
{
    private ClientModEvents()
    {
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        registerRenderer(event, ModEntityTypes.CHICKEN, "chicken", 0.3F);
        registerRenderer(event, ModEntityTypes.GUARDIAN_ELDER, "guardian_elder", 0.8F);
        registerRenderer(event, ModEntityTypes.BATTLE_MECH, "battle_mech", 0.8F);
        registerRenderer(event, ModEntityTypes.BLAZE, "blaze", 0.5F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_BASIC, "booster_ultraman_basic", 0.8F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_COURAGE_BURNING, "booster_ultraman_courage_burning", 0.8F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_STARLIGHT_BURST, "booster_ultraman_starlight_burst", 0.8F);
        registerRenderer(event, ModEntityTypes.CERBERUS, "cerberus", 0.7F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING, "crystallize_black_king", 0.8F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZEBLACKKING, "crystallizeblackking", 0.8F);
        registerRenderer(event, ModEntityTypes.ENDERMAN, "enderman", 0.5F);
        registerRenderer(event, ModEntityTypes.GHAST, "ghast", 1.2F);
        registerRenderer(event, ModEntityTypes.HADES_ZAGI, "hades_zagi", 0.8F);
        registerRenderer(event, ModEntityTypes.JIEDUN_NANO_GOLD_ANCIENT_BRIDGE_FUSION, "jiedun_nano_gold_ancient_bridge_fusion", 0.8F);
        registerRenderer(event, ModEntityTypes.LEBIC_DEMON_FORM, "lebic_demon_form", 0.8F);
        registerRenderer(event, ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK, "lerbis_nemesis_the_sidekick", 0.8F);
        registerRenderer(event, ModEntityTypes.MOYINGLONG, "moyinglong", 1.2F);
        registerRenderer(event, ModEntityTypes.NAKELIANS, "nakelians", 0.7F);
        registerRenderer(event, ModEntityTypes.RINGUA_IGONOTA, "ringua_igonota", 0.8F);
        registerRenderer(event, ModEntityTypes.YOUZHUSHOU, "youzhushou", 0.7F);
        registerRenderer(event, ModEntityTypes.ZOMBIE, "zombie", 0.5F);
    }

    private static <T extends TesheGeoMob> void registerRenderer(
            EntityRenderersEvent.RegisterRenderers event,
            RegistryObject<EntityType<T>> entityType,
            String modelName,
            float shadowRadius
    )
    {
        event.registerEntityRenderer(entityType.get(), context -> new GenericGeoEntityRenderer<>(context, modelName, shadowRadius));
    }
}
