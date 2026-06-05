package github.com.gengyoubo.TeShe.client;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.client.particle.SparkParticle;
import github.com.gengyoubo.TeShe.client.renderer.BlazePlagueSymbiosisRenderer;
import github.com.gengyoubo.TeShe.client.renderer.ChickenPlagueSymbiosisRenderer;
import github.com.gengyoubo.TeShe.client.renderer.EndermanPlagueSymbiosisRenderer;
import github.com.gengyoubo.TeShe.client.renderer.GenericGeoEntityRenderer;
import github.com.gengyoubo.TeShe.client.renderer.GuardianElderPlagueSymbiosisRenderer;
import github.com.gengyoubo.TeShe.client.renderer.PlagueAllayRenderer;
import github.com.gengyoubo.TeShe.client.renderer.PlagueVanillaRenderers;
import github.com.gengyoubo.TeShe.client.renderer.ZombiePlagueSymbiosisRenderer;
import github.com.gengyoubo.TeShe.model.AllayPlagueSymbiosis;
import github.com.gengyoubo.TeShe.model.BlazePlagueSymbiosis;
import github.com.gengyoubo.TeShe.model.ChickenPlagueSymbiosis;
import github.com.gengyoubo.TeShe.model.EndermanPlagueSymbiosis;
import github.com.gengyoubo.TeShe.model.GuardianElderPlagueSymbiosis;
import github.com.gengyoubo.TeShe.model.ZombiePlagueSymbiosis;
import github.com.gengyoubo.TeShe.registry.ModEntityTypes;
import github.com.gengyoubo.TeShe.registry.ModParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
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
        event.registerEntityRenderer(ModEntityTypes.CHICKEN.get(), ChickenPlagueSymbiosisRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.GUARDIAN_ELDER.get(), GuardianElderPlagueSymbiosisRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.ALLAY.get(), PlagueAllayRenderer::new);
        registerRenderer(event, ModEntityTypes.ANIMATED_METEOR_BLAZMET, "animated_meteor_blazmet", 0.7F);
        registerRenderer(event, ModEntityTypes.ANIMATED_METEOR_BLAZMET_BOSS, "animated_meteor_blazmet", 0.7F);
        event.<Axolotl>registerEntityRenderer(ModEntityTypes.AXOLOTL_LUCY.get(), PlagueVanillaRenderers.AxolotlMob::new);
        registerRenderer(event, ModEntityTypes.BATTLE_MECH, "battle_mech", 0.8F);
        event.registerEntityRenderer(ModEntityTypes.BLAZE.get(), context -> new BlazePlagueSymbiosisRenderer<>(context, "blaze"));
        event.registerEntityRenderer(ModEntityTypes.BLAZE_ALT.get(), context -> new BlazePlagueSymbiosisRenderer<>(context, "blaze_alt"));
        registerRenderer(event, ModEntityTypes.CERBERUS, "cerberus", 0.7F);
        event.<Cat>registerEntityRenderer(ModEntityTypes.CAT.get(), PlagueVanillaRenderers.CatMob::new);
        event.<Cow>registerEntityRenderer(ModEntityTypes.COW.get(), PlagueVanillaRenderers.CowMob::new);
        registerRenderer(event, ModEntityTypes.COSMIC_BULLIBARD, "cosmic_bullibard", "cosmic_bullibard", 0.6F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING, "crystallize_black_king", "general", 0.8F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING_BOSS, "crystallize_black_king", 0.8F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZEBLACKKING, "crystallizeblackking", 0.8F);
        registerRenderer(event, ModEntityTypes.CRYSTALLIZEBLACKKING_BOSS, "crystallizeblackking", 0.8F);
        registerRenderer(event, ModEntityTypes.DARK_SOUL_GESPIKET, "dark_soul_gespiket", 0.8F);
        registerRenderer(event, ModEntityTypes.DARK_SOUL_GESPIKET_BOSS, "dark_soul_gespiket", 0.8F);
        registerRenderer(event, ModEntityTypes.EMBER_GUARDIAN, "ember_guardian", "ember_guardian", 0.8F);
        event.registerEntityRenderer(ModEntityTypes.ENDERMAN.get(), EndermanPlagueSymbiosisRenderer::new);
        event.<Fox>registerEntityRenderer(ModEntityTypes.FOX.get(), PlagueVanillaRenderers.FoxMob::new);
        event.<Ghast>registerEntityRenderer(ModEntityTypes.GHAST.get(), PlagueVanillaRenderers.GhastMob::new);
        event.<Guardian>registerEntityRenderer(ModEntityTypes.GUARDIAN.get(), PlagueVanillaRenderers.GuardianMob::new);
        registerRenderer(event, ModEntityTypes.HADES_ZAGI, "hades_zagi", "hades_zagi", 0.8F);
        event.registerEntityRenderer(ModEntityTypes.HUSK.get(), context -> new ZombiePlagueSymbiosisRenderer<>(context, "husk", false));
        registerRenderer(event, ModEntityTypes.LEBIC_DEMON_FORM, "lebic_demon_form", 0.8F);
        registerRenderer(event, ModEntityTypes.LEBIC_DEMON_FORM_BOSS, "lebic_demon_form", 0.8F);
        registerRenderer(event, ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK, "lerbis_nemesis_the_sidekick", "general", 0.8F);
        registerRenderer(event, ModEntityTypes.LERBIS_NEMESIS_THE_LEADER, "lerbis_nemesis_the_leader", "general", 0.8F);
        event.<EnderDragon>registerEntityRenderer(ModEntityTypes.MOYINGLONG.get(), PlagueVanillaRenderers.EnderDragonMob::new);
        registerRenderer(event, ModEntityTypes.MODIFIED_BULLIBARD, "modified_bullibard", "modified_bullibard", 0.6F);
        registerRenderer(event, ModEntityTypes.MODIFIED_BULLIBARD_BOSS, "modified_bullibard", "modified_bullibard", 0.6F);
        event.<Phantom>registerEntityRenderer(ModEntityTypes.PHANTOM.get(), PlagueVanillaRenderers.PhantomMob::new);
        event.<Pig>registerEntityRenderer(ModEntityTypes.PIG.get(), PlagueVanillaRenderers.PigMob::new);
        event.<Mob>registerEntityRenderer(ModEntityTypes.PIGLIN.get(), PlagueVanillaRenderers.PiglinMob::new);
        event.<Mob>registerEntityRenderer(ModEntityTypes.PIGLIN_BRUTE.get(), PlagueVanillaRenderers.PiglinBruteMob::new);
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
        event.<Hoglin>registerEntityRenderer(ModEntityTypes.YOUZHUSHOU.get(), PlagueVanillaRenderers.HoglinMob::new);
        event.registerEntityRenderer(ModEntityTypes.ZOMBIE.get(), context -> new ZombiePlagueSymbiosisRenderer<>(context, "zombie", false));
        event.registerEntityRenderer(ModEntityTypes.DROWNED.get(), context -> new ZombiePlagueSymbiosisRenderer<>(context, "drowned", true));
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(AllayPlagueSymbiosis.LAYER_LOCATION, AllayPlagueSymbiosis::createBodyLayer);
        event.registerLayerDefinition(BlazePlagueSymbiosis.LAYER_LOCATION, BlazePlagueSymbiosis::createBodyLayer);
        event.registerLayerDefinition(ChickenPlagueSymbiosis.LAYER_LOCATION, ChickenPlagueSymbiosis::createBodyLayer);
        event.registerLayerDefinition(EndermanPlagueSymbiosis.LAYER_LOCATION, EndermanPlagueSymbiosis::createBodyLayer);
        event.registerLayerDefinition(GuardianElderPlagueSymbiosis.LAYER_LOCATION, GuardianElderPlagueSymbiosis::createBodyLayer);
        event.registerLayerDefinition(ZombiePlagueSymbiosis.LAYER_LOCATION, ZombiePlagueSymbiosis::createBodyLayer);
        event.registerLayerDefinition(ZombiePlagueSymbiosis.OUTER_LAYER_LOCATION, ZombiePlagueSymbiosis::createBodyLayer);
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

