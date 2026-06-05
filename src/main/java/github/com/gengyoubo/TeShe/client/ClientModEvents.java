package github.com.gengyoubo.TeShe.client;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.client.particle.SparkParticle;
import github.com.gengyoubo.TeShe.client.renderer.BlazeInfectionRenderer;
import github.com.gengyoubo.TeShe.client.renderer.ChickenInfectionRenderer;
import github.com.gengyoubo.TeShe.client.renderer.EndermanInfectionRenderer;
import github.com.gengyoubo.TeShe.client.renderer.GenericGeoEntityRenderer;
import github.com.gengyoubo.TeShe.client.renderer.GuardianElderInfectionRenderer;
import github.com.gengyoubo.TeShe.client.renderer.PlagueAllayRenderer;
import github.com.gengyoubo.TeShe.client.renderer.ZombieInfectionRenderer;
import github.com.gengyoubo.TeShe.model.AllayInfection;
import github.com.gengyoubo.TeShe.model.BlazeInfection;
import github.com.gengyoubo.TeShe.model.ChickenInfection;
import github.com.gengyoubo.TeShe.model.EndermanInfection;
import github.com.gengyoubo.TeShe.model.Guardian_elderInfection;
import github.com.gengyoubo.TeShe.model.ZombieInfection;
import github.com.gengyoubo.TeShe.registry.ModEntityTypes;
import github.com.gengyoubo.TeShe.registry.ModParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
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
        event.registerEntityRenderer(ModEntityTypes.CHICKEN.get(), ChickenInfectionRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.GUARDIAN_ELDER.get(), GuardianElderInfectionRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.ALLAY.get(), PlagueAllayRenderer::new);
        registerRenderer(event, ModEntityTypes.ANIMATED_METEOR_BLAZMET, "animated_meteor_blazmet", 0.7F);
        registerRenderer(event, ModEntityTypes.ANIMATED_METEOR_BLAZMET_BOSS, "animated_meteor_blazmet", 0.7F);
        registerRenderer(event, ModEntityTypes.AXOLOTL_LUCY, "axolotl_lucy", 0.3F);
        registerRenderer(event, ModEntityTypes.BATTLE_MECH, "battle_mech", 0.8F);
        event.registerEntityRenderer(ModEntityTypes.BLAZE.get(), context -> new BlazeInfectionRenderer<>(context, "blaze"));
        event.registerEntityRenderer(ModEntityTypes.BLAZE_ALT.get(), context -> new BlazeInfectionRenderer<>(context, "blaze_alt"));
        registerRenderer(event, ModEntityTypes.CERBERUS, "cerberus", 0.7F);
        registerRenderer(event, ModEntityTypes.CAT, "cat", 0.4F);
        registerRenderer(event, ModEntityTypes.COW, "cow", 0.5F);
        registerRenderer(event, ModEntityTypes.COSMIC_BULLIBARD, "cosmic_bullibard", "cosmic_bullibard", 0.6F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING, "crystallize_black_king", "general", 0.8F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING_BOSS, "crystallize_black_king", 0.8F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZEBLACKKING, "crystallizeblackking", 0.8F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZEBLACKKING_BOSS, "crystallizeblackking", 0.8F);
        registerRenderer(event, ModEntityTypes.DARK_SOUL_GESPIKET, "dark_soul_gespiket", 0.8F);
        registerRenderer(event, ModEntityTypes.DARK_SOUL_GESPIKET_BOSS, "dark_soul_gespiket", 0.8F);
        registerRenderer(event, ModEntityTypes.EMBER_GUARDIAN, "ember_guardian", "ember_guardian", 0.8F);
        event.registerEntityRenderer(ModEntityTypes.ENDERMAN.get(), EndermanInfectionRenderer::new);
        registerRenderer(event, ModEntityTypes.FOX, "fox", 0.4F);
        registerRenderer(event, ModEntityTypes.GHAST, "ghast", 1.2F);
        registerRenderer(event, ModEntityTypes.GUARDIAN, "guardian", 0.6F);
        registerRenderer(event, ModEntityTypes.HADES_ZAGI, "hades_zagi", "hades_zagi", 0.8F);
        event.registerEntityRenderer(ModEntityTypes.HUSK.get(), context -> new ZombieInfectionRenderer<>(context, "husk", false));
        registerRenderer(event, ModEntityTypes.LEBIC_DEMON_FORM, "lebic_demon_form", 0.8F);
        registerRenderer(event, ModEntityTypes.LEBIC_DEMON_FORM_BOSS, "lebic_demon_form", 0.8F);
        registerRenderer(event, ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK, "lerbis_nemesis_the_sidekick", "general", 0.8F);
        registerRenderer(event, ModEntityTypes.LERBIS_NEMESIS_THE_LEADER, "lerbis_nemesis_the_leader", "general", 0.8F);
        registerRenderer(event, ModEntityTypes.MOYINGLONG, "moyinglong", 1.2F);
        registerRenderer(event, ModEntityTypes.MODIFIED_BULLIBARD, "modified_bullibard", "modified_bullibard", 0.6F);
        registerRenderer(event, ModEntityTypes.MODIFIED_BULLIBARD_BOSS, "modified_bullibard", "modified_bullibard", 0.6F);
        registerRenderer(event, ModEntityTypes.PHANTOM, "phantom", 0.5F);
        registerRenderer(event, ModEntityTypes.PIG, "pig", 0.5F);
        registerRenderer(event, ModEntityTypes.PIGLIN, "piglin", 0.5F);
        registerRenderer(event, ModEntityTypes.PIGLIN_BRUTE, "piglin_brute", 0.5F);
        registerRenderer(event, ModEntityTypes.RAZOR_DEMAGA, "razor_demaga", 0.8F);
        registerRenderer(event, ModEntityTypes.RAZOR_DEMAGA_BOSS, "razor_demaga", 0.8F);
        registerRenderer(event, ModEntityTypes.RINGUA_IGONOTA, "ringua_igonota", 0.8F);
        registerRenderer(event, ModEntityTypes.RINGUA_IGONOTA_BOSS, "ringua_igonota", 0.8F);
        registerRenderer(event, ModEntityTypes.RUIN_CHIMERA, "ruin_chimera", 1.0F);
        registerRenderer(event, ModEntityTypes.KEAN_ANTONLA, "kean_antonla", "kean_antonla", 0.8F);
        registerRenderer(event, ModEntityTypes.RUIN_ANTONLA, "ruin_antonla", "ruin_antonla", 0.8F);
        registerRenderer(event, ModEntityTypes.RUIN_ANTONLA_BOSS, "ruin_antonla", "ruin_antonla", 0.8F);
        registerRenderer(event, ModEntityTypes.SATAN_HAND, "satan_hand", 0.7F);
        registerRenderer(event, ModEntityTypes.SOUL_OF_MOUNTAINS, "soul_of_mountains", 0.8F);
        registerRenderer(event, ModEntityTypes.SPECIAL_EX_ELEKING, "special_ex_eleking", 0.8F);
        registerRenderer(event, ModEntityTypes.YOUZHUSHOU, "youzhushou", 0.7F);
        event.registerEntityRenderer(ModEntityTypes.ZOMBIE.get(), context -> new ZombieInfectionRenderer<>(context, "zombie", false));
        event.registerEntityRenderer(ModEntityTypes.DROWNED.get(), context -> new ZombieInfectionRenderer<>(context, "drowned", true));
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(AllayInfection.LAYER_LOCATION, AllayInfection::createBodyLayer);
        event.registerLayerDefinition(BlazeInfection.LAYER_LOCATION, BlazeInfection::createBodyLayer);
        event.registerLayerDefinition(ChickenInfection.LAYER_LOCATION, ChickenInfection::createBodyLayer);
        event.registerLayerDefinition(EndermanInfection.LAYER_LOCATION, EndermanInfection::createBodyLayer);
        event.registerLayerDefinition(Guardian_elderInfection.LAYER_LOCATION, Guardian_elderInfection::createBodyLayer);
        event.registerLayerDefinition(ZombieInfection.LAYER_LOCATION, ZombieInfection::createBodyLayer);
        event.registerLayerDefinition(ZombieInfection.OUTER_LAYER_LOCATION, ZombieInfection::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event)
    {
        event.registerSpriteSet(ModParticleTypes.SPARK.get(), SparkParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.SPARKBLUE.get(), SparkParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.RED_SPARK.get(), SparkParticle.BeamProvider::new);
        event.registerSpriteSet(ModParticleTypes.SPARK_TO_SPARKBLUE.get(), SparkParticle.BeamProvider::new);
        event.registerSpriteSet(ModParticleTypes.SPARKBLUE_TO_SPARK.get(), SparkParticle.BeamProvider::new);
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

    private static <T extends Entity & GeoAnimatable> void registerRenderer(
            EntityRenderersEvent.RegisterRenderers event,
            RegistryObject<EntityType<T>> entityType,
            String modelName,
            String animationsName,
            float shadowRadius
    )
    {
        event.registerEntityRenderer(entityType.get(), context -> new GenericGeoEntityRenderer<>(context, modelName, animationsName, shadowRadius));
    }
}
