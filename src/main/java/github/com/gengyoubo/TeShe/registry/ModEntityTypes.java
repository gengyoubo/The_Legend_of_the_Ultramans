package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.CosmicBullibardEntity;
import github.com.gengyoubo.TeShe.entity.CrystallizeBlackKingEntity;
import github.com.gengyoubo.TeShe.entity.GenericTesheGeoMob;
import github.com.gengyoubo.TeShe.entity.GuardianElderEntity;
import github.com.gengyoubo.TeShe.entity.LerbisNemesisLeaderEntity;
import github.com.gengyoubo.TeShe.entity.LerbisNemesisSidekickEntity;
import github.com.gengyoubo.TeShe.entity.PlagueAllayEntity;
import github.com.gengyoubo.TeShe.entity.PlagueBlazeEntity;
import github.com.gengyoubo.TeShe.entity.PlagueCatEntity;
import github.com.gengyoubo.TeShe.entity.PlagueCowEntity;
import github.com.gengyoubo.TeShe.entity.PlagueEndermanEntity;
import github.com.gengyoubo.TeShe.entity.PlagueFoxEntity;
import github.com.gengyoubo.TeShe.entity.PlagueGuardianEntity;
import github.com.gengyoubo.TeShe.entity.PlaguePhantomEntity;
import github.com.gengyoubo.TeShe.entity.PlaguePigEntity;
import github.com.gengyoubo.TeShe.entity.PlaguePiglinBruteEntity;
import github.com.gengyoubo.TeShe.entity.PlaguePiglinEntity;
import github.com.gengyoubo.TeShe.entity.PlagueZombieEntity;
import github.com.gengyoubo.TeShe.entity.TesheChickenEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntityTypes
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TE.MODID);

    public static final RegistryObject<EntityType<TesheChickenEntity>> CHICKEN = ENTITY_TYPES.register(
            "chicken",
            () -> EntityType.Builder.of(TesheChickenEntity::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":chicken")
    );

    public static final RegistryObject<EntityType<GuardianElderEntity>> GUARDIAN_ELDER = ENTITY_TYPES.register(
            "guardian_elder",
            () -> EntityType.Builder.of(GuardianElderEntity::new, MobCategory.MONSTER)
                    .sized(2.0F, 1.4F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":guardian_elder")
    );
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> BATTLE_MECH = registerGeoMob("battle_mech", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<PlagueAllayEntity>> ALLAY = ENTITY_TYPES.register(
            "allay",
            () -> EntityType.Builder.of(PlagueAllayEntity::new, MobCategory.CREATURE)
                    .sized(0.35F, 0.6F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":allay")
    );
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> ANIMATED_METEOR_BLAZMET = registerGeoMob("animated_meteor_blazmet", 1.2F, 1.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> ANIMATED_METEOR_BLAZMET_BOSS =
            registerGeoMobBoss("animated_meteor_blazmet_boss", "animated_meteor_blazmet", 1.2F, 1.8F);
    public static final RegistryObject<EntityType<PlagueBlazeEntity>> BLAZE = ENTITY_TYPES.register(
            "blaze",
            () -> EntityType.Builder.of(PlagueBlazeEntity::new, MobCategory.MONSTER)
                    .fireImmune()
                    .sized(0.6F, 1.8F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":blaze")
    );
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> AXOLOTL_LUCY = registerGeoMob("axolotl_lucy", 0.75F, 0.42F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> CERBERUS = registerGeoMob("cerberus", 1.4F, 1.4F);
    public static final RegistryObject<EntityType<PlagueCatEntity>> CAT = ENTITY_TYPES.register(
            "cat",
            () -> EntityType.Builder.of(PlagueCatEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.7F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":cat")
    );
    public static final RegistryObject<EntityType<PlagueCowEntity>> COW = ENTITY_TYPES.register(
            "cow",
            () -> EntityType.Builder.of(PlagueCowEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":cow")
    );
    public static final RegistryObject<EntityType<CosmicBullibardEntity>> COSMIC_BULLIBARD = ENTITY_TYPES.register(
            "bullibard",
            () -> EntityType.Builder.of(CosmicBullibardEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.6F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":bullibard")
    );
    public static final RegistryObject<EntityType<CrystallizeBlackKingEntity>> CRYSTALLIZE_BLACK_KING = ENTITY_TYPES.register(
            "crystallize_black_king",
            () -> EntityType.Builder.of(CrystallizeBlackKingEntity::new, MobCategory.MONSTER)
                    .sized(1.4F, 2.6F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":crystallize_black_king")
    );
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> CRYSTALLIZE_BLACK_KING_BOSS =
            registerGeoMobBoss("crystallize_black_king_boss", "crystallize_black_king", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> CRYSTALLIZEBLACKKING = registerGeoMob("crystallizeblackking", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> CRYSTALLIZEBLACKKING_BOSS =
            registerGeoMobBoss("crystallizeblackking_boss", "crystallizeblackking", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> DARK_SOUL_GESPIKET = registerGeoMob("dark_soul_gespiket", 1.4F, 2.2F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> DARK_SOUL_GESPIKET_BOSS =
            registerGeoMobBoss("dark_soul_gespiket_boss", "dark_soul_gespiket", 1.4F, 2.2F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> EMBER_GUARDIAN = registerAnimatedGeoMob("ember_guardian", 1.2F, 2.2F, "pose1");
    public static final RegistryObject<EntityType<PlagueEndermanEntity>> ENDERMAN = ENTITY_TYPES.register(
            "enderman",
            () -> EntityType.Builder.of(PlagueEndermanEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 2.9F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":enderman")
    );
    public static final RegistryObject<EntityType<PlagueFoxEntity>> FOX = ENTITY_TYPES.register(
            "fox",
            () -> EntityType.Builder.of(PlagueFoxEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.7F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":fox")
    );
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> GHAST = registerGeoMob("ghast", 4.0F, 4.0F);
    public static final RegistryObject<EntityType<PlagueGuardianEntity>> GUARDIAN = ENTITY_TYPES.register(
            "guardian",
            () -> EntityType.Builder.of(PlagueGuardianEntity::new, MobCategory.MONSTER)
                    .sized(0.85F, 0.85F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":guardian")
    );
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> HADES_ZAGI = registerAnimatedGeoMob("hades_zagi", 0.9F, 2.8F, "nova.animation");
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> LEBIC_DEMON_FORM = registerGeoMob("lebic_demon_form", 0.9F, 2.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> LEBIC_DEMON_FORM_BOSS =
            registerGeoMobBoss("lebic_demon_form_boss", "lebic_demon_form", 0.9F, 2.8F);
    public static final RegistryObject<EntityType<LerbisNemesisSidekickEntity>> LERBIS_NEMESIS_THE_SIDEKICK = ENTITY_TYPES.register(
            "lerbis_nemesis_the_sidekick",
            () -> EntityType.Builder.of(LerbisNemesisSidekickEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 2.8F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":lerbis_nemesis_the_sidekick")
    );
    public static final RegistryObject<EntityType<LerbisNemesisLeaderEntity>> LERBIS_NEMESIS_THE_LEADER = ENTITY_TYPES.register(
            "lerbis_nemesis_the_leader",
            () -> EntityType.Builder.of(LerbisNemesisLeaderEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 2.8F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":lerbis_nemesis_the_leader")
    );
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> MOYINGLONG = registerGeoMob("moyinglong", 4.0F, 4.0F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> MODIFIED_BULLIBARD = registerAnimatedGeoMob("modified_bullibard", 1.0F, 1.6F, "fly");
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> MODIFIED_BULLIBARD_BOSS =
            registerAnimatedGeoMobBoss("modified_bullibard_boss", "modified_bullibard", 1.0F, 1.6F, "fly");
    public static final RegistryObject<EntityType<PlaguePhantomEntity>> PHANTOM = ENTITY_TYPES.register(
            "phantom",
            () -> EntityType.Builder.of(PlaguePhantomEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 0.5F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":phantom")
    );
    public static final RegistryObject<EntityType<PlaguePigEntity>> PIG = ENTITY_TYPES.register(
            "pig",
            () -> EntityType.Builder.of(PlaguePigEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.9F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":pig")
    );
    public static final RegistryObject<EntityType<PlaguePiglinEntity>> PIGLIN = ENTITY_TYPES.register(
            "piglin",
            () -> EntityType.Builder.of(PlaguePiglinEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":piglin")
    );
    public static final RegistryObject<EntityType<PlaguePiglinBruteEntity>> PIGLIN_BRUTE = ENTITY_TYPES.register(
            "piglin_brute",
            () -> EntityType.Builder.of(PlaguePiglinBruteEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":piglin_brute")
    );
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> RAZOR_DEMAGA = registerGeoMob("razor_demaga", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> RAZOR_DEMAGA_BOSS =
            registerGeoMobBoss("razor_demaga_boss", "razor_demaga", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> RINGUA_IGONOTA = registerGeoMob("ringua_igonota", 1.2F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> RINGUA_IGONOTA_BOSS =
            registerGeoMobBoss("ringua_igonota_boss", "ringua_igonota", 1.2F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> RUIN_CHIMERA = registerGeoMob("ruin_chimera", 1.8F, 2.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> RUIN_ANTONLA = registerAnimatedGeoMob("ruin_antonla", 1.4F, 2.6F, "\u884c\u8d70");
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> RUIN_ANTONLA_BOSS =
            registerAnimatedGeoMobBoss("ruin_antonla_boss", "ruin_antonla", 1.4F, 2.6F, "\u884c\u8d70");
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> SATAN_HAND = registerGeoMob("satan_hand", 1.0F, 2.0F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> SOUL_OF_MOUNTAINS = registerGeoMob("soul_of_mountains", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> SPECIAL_EX_ELEKING = registerGeoMob("special_ex_eleking", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> YOUZHUSHOU = registerGeoMob("youzhushou", 1.0F, 2.0F);
    public static final RegistryObject<EntityType<PlagueZombieEntity>> ZOMBIE = ENTITY_TYPES.register(
            "zombie",
            () -> EntityType.Builder.of(PlagueZombieEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":zombie")
    );

    private ModEntityTypes()
    {
    }

    public static void register(IEventBus bus)
    {
        ENTITY_TYPES.register(bus);
    }

    private static RegistryObject<EntityType<GenericTesheGeoMob>> registerGeoMob(String name, float width, float height)
    {
        return ENTITY_TYPES.register(
                name,
                () -> EntityType.Builder.<GenericTesheGeoMob>of((type, level) -> new GenericTesheGeoMob(type, level, name), MobCategory.MONSTER)
                        .sized(width, height)
                        .clientTrackingRange(10)
                        .build(TE.MODID + ":" + name)
        );
    }

    private static RegistryObject<EntityType<GenericTesheGeoMob>> registerAnimatedGeoMob(String name, float width, float height, String defaultAnimationName)
    {
        return registerAnimatedGeoMob(name, name, width, height, defaultAnimationName);
    }

    private static RegistryObject<EntityType<GenericTesheGeoMob>> registerAnimatedGeoMob(String name, String animationsName, float width, float height, String defaultAnimationName)
    {
        return ENTITY_TYPES.register(
                name,
                () -> EntityType.Builder.<GenericTesheGeoMob>of((type, level) -> new GenericTesheGeoMob(type, level, name, animationsName, defaultAnimationName), MobCategory.MONSTER)
                        .sized(width, height)
                        .clientTrackingRange(10)
                        .build(TE.MODID + ":" + name)
        );
    }

    private static RegistryObject<EntityType<GenericTesheGeoMob>> registerGeoMobBoss(String name, String modelName, float width, float height)
    {
        return ENTITY_TYPES.register(
                name,
                () -> EntityType.Builder.<GenericTesheGeoMob>of((type, level) -> new GenericTesheGeoMob(type, level, modelName, "entity." + TE.MODID + "." + modelName + ".boss"), MobCategory.MONSTER)
                        .sized(width, height)
                        .clientTrackingRange(10)
                        .build(TE.MODID + ":" + name)
        );
    }

    private static RegistryObject<EntityType<GenericTesheGeoMob>> registerAnimatedGeoMobBoss(String name, String modelName, float width, float height, String defaultAnimationName)
    {
        return ENTITY_TYPES.register(
                name,
                () -> EntityType.Builder.<GenericTesheGeoMob>of((type, level) -> new GenericTesheGeoMob(type, level, modelName, modelName, defaultAnimationName, "entity." + TE.MODID + "." + modelName + ".boss"), MobCategory.MONSTER)
                        .sized(width, height)
                        .clientTrackingRange(10)
                        .build(TE.MODID + ":" + name)
        );
    }
}
