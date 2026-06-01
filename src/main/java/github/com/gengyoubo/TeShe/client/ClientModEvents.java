package github.com.gengyoubo.TeShe.client;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.client.renderer.GenericGeoEntityRenderer;
import github.com.gengyoubo.TeShe.registry.ModEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
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
        registerRenderer(event, ModEntityTypes.ALLAY, "allay", 0.3F);
        registerRenderer(event, ModEntityTypes.ANIMATED_METEOR_BLAZMET, "animated_meteor_blazmet", 0.7F);
        registerRenderer(event, ModEntityTypes.AXOLOTL_LUCY, "axolotl_lucy", 0.3F);
        registerRenderer(event, ModEntityTypes.BABARU_ALIEN, "babaru_alien", 0.8F);
        registerRenderer(event, ModEntityTypes.BATTLE_MECH, "battle_mech", 0.8F);
        registerRenderer(event, ModEntityTypes.BLAZE, "blaze", 0.5F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_BASIC, "booster_ultraman_basic", 0.8F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_COURAGE_BURNING, "booster_ultraman_courage_burning", 0.8F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_HYPER, "booster_ultraman_hyper", 0.8F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_HYPER_CERBERUS_ARMED, "booster_ultraman_hyper_cerberus_armed", 0.8F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_HYPER_YULUAN_ARMED, "booster_ultraman_hyper_yuluan_armed", 0.8F);
        registerRenderer(event, ModEntityTypes.BOOSTER_ULTRAMAN_STARLIGHT_BURST, "booster_ultraman_starlight_burst", 0.8F);
        registerRenderer(event, ModEntityTypes.CERBERUS, "cerberus", 0.7F);
        registerRenderer(event, ModEntityTypes.CAT, "cat", 0.4F);
        registerRenderer(event, ModEntityTypes.COW, "cow", 0.5F);
        registerRenderer(event, ModEntityTypes.COSMIC_BULLIBARD, "cosmic_bullibard", 0.6F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING, "crystallize_black_king", 0.8F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZEBLACKKING, "crystallizeblackking", 0.8F);
        registerRenderer(event, ModEntityTypes.DARK_SOUL_GESPIKET, "dark_soul_gespiket", 0.8F);
        registerRenderer(event, ModEntityTypes.DIAMOND_ULTRAMAN_BASIC, "diamond_ultraman_basic", 0.8F);
        registerRenderer(event, ModEntityTypes.DIAMOND_ULTRAMAN_RUIN_FLOWER_DEMON, "diamond_ultraman_ruin_flower_demon", 0.8F);
        registerRenderer(event, ModEntityTypes.DIAMOND_ULTRAMAN_SATAN_DEMON_KING, "diamond_ultraman_satan_demon_king", 0.8F);
        registerRenderer(event, ModEntityTypes.DIAMOND_ULTRAMAN_SATAN_RITUAL, "diamond_ultraman_satan_ritual", 0.8F);
        registerRenderer(event, ModEntityTypes.DIAMOND_ULTRAMAN_SECOND, "diamond_ultraman_second", 0.8F);
        registerRenderer(event, ModEntityTypes.EMBER_GUARDIAN, "ember_guardian", 0.8F);
        registerRenderer(event, ModEntityTypes.ENDERMAN, "enderman", 0.5F);
        registerRenderer(event, ModEntityTypes.FOX, "fox", 0.4F);
        registerRenderer(event, ModEntityTypes.GHAST, "ghast", 1.2F);
        registerRenderer(event, ModEntityTypes.GUARDIAN, "guardian", 0.6F);
        registerRenderer(event, ModEntityTypes.HADES_ZAGI, "hades_zagi", 0.8F);
        registerRenderer(event, ModEntityTypes.JIEDUN_NANO_GOLD_ANCIENT_BRIDGE_FUSION, "jiedun_nano_gold_ancient_bridge_fusion", 0.8F);
        registerRenderer(event, ModEntityTypes.LEBIC_DEMON_FORM, "lebic_demon_form", 0.8F);
        registerRenderer(event, ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK, "lerbis_nemesis_the_sidekick", 0.8F);
        registerRenderer(event, ModEntityTypes.MOYINGLONG, "moyinglong", 1.2F);
        registerRenderer(event, ModEntityTypes.MODIFIED_BULLIBARD, "modified_bullibard", 0.6F);
        registerRenderer(event, ModEntityTypes.NAKELIANS, "nakelians", 0.7F);
        registerRenderer(event, ModEntityTypes.PHANTOM, "phantom", 0.5F);
        registerRenderer(event, ModEntityTypes.PIG, "pig", 0.5F);
        registerRenderer(event, ModEntityTypes.PIGLIN, "piglin", 0.5F);
        registerRenderer(event, ModEntityTypes.PIGLIN_BRUTE, "piglin_brute", 0.5F);
        registerRenderer(event, ModEntityTypes.RAZOR_DEMAGA, "razor_demaga", 0.8F);
        registerRenderer(event, ModEntityTypes.RINGUA_IGONOTA, "ringua_igonota", 0.8F);
        registerRenderer(event, ModEntityTypes.RUIN_CHIMERA, "ruin_chimera", 1.0F);
        registerRenderer(event, ModEntityTypes.RUIN_ANTONLA, "ruin_antonla", 0.8F);
        registerRenderer(event, ModEntityTypes.SATAN_HAND, "satan_hand", 0.7F);
        registerRenderer(event, ModEntityTypes.SOUL_OF_MOUNTAINS, "soul_of_mountains", 0.8F);
        registerRenderer(event, ModEntityTypes.SPECIAL_EX_ELEKING, "special_ex_eleking", 0.8F);
        registerRenderer(event, ModEntityTypes.YOUZHUSHOU, "youzhushou", 0.7F);
        registerRenderer(event, ModEntityTypes.ZOMBIE, "zombie", 0.5F);
    }

    private static <T extends Entity & GeoAnimatable> void registerRenderer(
            EntityRenderersEvent.RegisterRenderers event,
            RegistryObject<EntityType<T>> entityType,
            String modelName,
            float shadowRadius
    )
    {
        event.registerEntityRenderer(entityType.get(), context -> new GenericGeoEntityRenderer<>(context, modelName, shadowRadius));
    }
}
