package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.GenericTesheGeoMob;
import github.com.gengyoubo.TeShe.entity.GuardianElderEntity;
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
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> BLAZE = registerGeoMob("blaze", 0.6F, 1.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> BOOSTER_ULTRAMAN_BASIC = registerGeoMob("booster_ultraman_basic", 0.9F, 2.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> BOOSTER_ULTRAMAN_COURAGE_BURNING = registerGeoMob("booster_ultraman_courage_burning", 0.9F, 2.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> BOOSTER_ULTRAMAN_STARLIGHT_BURST = registerGeoMob("booster_ultraman_starlight_burst", 0.9F, 2.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> CERBERUS = registerGeoMob("cerberus", 1.4F, 1.4F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> CRYSTALLIZE_BLACK_KING = registerGeoMob("crystallize_black_king", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> CRYSTALLIZEBLACKKING = registerGeoMob("crystallizeblackking", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> ENDERMAN = registerGeoMob("enderman", 0.6F, 2.9F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> GHAST = registerGeoMob("ghast", 4.0F, 4.0F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> HADES_ZAGI = registerGeoMob("hades_zagi", 0.9F, 2.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> JIEDUN_NANO_GOLD_ANCIENT_BRIDGE_FUSION = registerGeoMob("jiedun_nano_gold_ancient_bridge_fusion", 1.4F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> LEBIC_DEMON_FORM = registerGeoMob("lebic_demon_form", 0.9F, 2.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> LERBIS_NEMESIS_THE_SIDEKICK = registerGeoMob("lerbis_nemesis_the_sidekick", 0.9F, 2.8F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> MOYINGLONG = registerGeoMob("moyinglong", 4.0F, 4.0F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> NAKELIANS = registerGeoMob("nakelians", 0.9F, 2.2F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> RINGUA_IGONOTA = registerGeoMob("ringua_igonota", 1.2F, 2.6F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> YOUZHUSHOU = registerGeoMob("youzhushou", 1.0F, 2.0F);
    public static final RegistryObject<EntityType<GenericTesheGeoMob>> ZOMBIE = registerGeoMob("zombie", 0.6F, 1.95F);

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
}
