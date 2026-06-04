package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.item.BoosterCrystalLiberatorItem;
import github.com.gengyoubo.TeShe.item.BullibardEventItem;
import github.com.gengyoubo.TeShe.item.CosmicBullibardBossSpawnEggItem;
import github.com.gengyoubo.TeShe.item.GeckoArmorItem;
import github.com.gengyoubo.TeShe.item.GeckoModelItem;
import github.com.gengyoubo.TeShe.item.GeckoShieldItem;
import github.com.gengyoubo.TeShe.item.GeckoSwordItem;
import github.com.gengyoubo.TeShe.item.HatredStickItem;
import github.com.gengyoubo.TeShe.item.SmdrtkArmorMaterial;
import github.com.gengyoubo.TeShe.item.SmdrtkGunItem;
import github.com.gengyoubo.TeShe.item.SmdrtkMultiFunctionPistolItem;
import github.com.gengyoubo.TeShe.item.StarFlowerSeedItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public final class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TE.MODID);

    public static final RegistryObject<Item> HATRED_STICK = ITEMS.register(
            "hatred_stick",
            () -> new HatredStickItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON))
    );
    public static final RegistryObject<Item> SMDRTK_MULTI_FUNCTION_PISTOL = ITEMS.register(
            "smdrtk_multi_function_pistol",
            () -> new SmdrtkMultiFunctionPistolItem(new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.RARE))
    );
    public static final RegistryObject<Item> SMDRTK_HELMET = registerArmorItem("smdrtk_helmet", "mdrtk_helmet", SmdrtkArmorMaterial.HELMET, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> SMDRTK_CHESTPLATE = registerArmorItem("smdrtk_chestplate", "mdrtk_chestplate", SmdrtkArmorMaterial.CHESTPLATE, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> SMDRTK_LEGGINGS = registerArmorItem("smdrtk_leggings", "mdrtk_leggings", SmdrtkArmorMaterial.LEGGINGS, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> SMDRTK_BOOTS = registerArmorItem("smdrtk_boots", "mdrtk_boots", SmdrtkArmorMaterial.BOOTS, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> SMDRTK_TEAM_LOGO = registerSimpleItem("smdrtk_team_logo", Rarity.UNCOMMON);
    public static final RegistryObject<Item> SPACIUM_SUBSTANCE = registerSimpleItem("spacium_substance", Rarity.UNCOMMON);
    public static final RegistryObject<Item> SMDRTK_ENERGY_SMG = ITEMS.register(
            "smdrtk_energy_smg",
            () -> new SmdrtkGunItem("smdrtk_energy_smg", SmdrtkGunItem.GunKind.SMG, new Item.Properties().stacksTo(1).rarity(Rarity.RARE))
    );
    public static final RegistryObject<Item> SMDRTK_ENERGY_RIFLE = ITEMS.register(
            "smdrtk_energy_rifle",
            () -> new SmdrtkGunItem("smdrtk_energy_rifle", SmdrtkGunItem.GunKind.RIFLE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE))
    );
    public static final RegistryObject<Item> COURAGE_CUTTER = registerSwordItem("courage_cutter");
    public static final RegistryObject<Item> COURAGE_CUTTER_INCOMPLETE = registerSwordItem("courage_cutter_incomplete");
    public static final RegistryObject<Item> KE_E_BO_JUDGEMENT_BLADE = registerSwordItem("ke_e_bo_judgement_blade");
    public static final RegistryObject<Item> KE_E_BO_CRYSTAL = registerModelItem("ke_e_bo_crystal");
    public static final RegistryObject<Item> RAZOR_DEMAGA_CUTTER = registerSwordItem("razor_demaga_cutter");
    public static final RegistryObject<Item> RAZOR_DEMAGA_CRYSTAL = registerModelItem("razor_demaga_crystal");
    public static final RegistryObject<Item> GOMORA_LANCE = registerSwordItem("gomora_lance");
    public static final RegistryObject<Item> BASIC_CRYSTAL = registerModelItem("basic_crystal");
    public static final RegistryObject<Item> BULLIBARD_RIPPER_RIGHT = registerSwordItem("bullibard_ripper_right");
    public static final RegistryObject<Item> BULLIBARD_RIPPER_LEFT = registerSwordItem("bullibard_ripper_left");
    public static final RegistryObject<Item> BOOSTER_CUTTER_ALPHA = registerModelItem("booster_cutter_alpha");
    public static final RegistryObject<Item> BOOSTER_CUTTER_BETA = registerModelItem("booster_cutter_beta");
    public static final RegistryObject<Item> BOOSTER_RIPPER = registerModelItem("booster_ripper");
    public static final RegistryObject<Item> BOOSTER_CRYSTAL_LIBERATOR = ITEMS.register(
            "booster_crystal_liberator",
            () -> new BoosterCrystalLiberatorItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE))
    );
    public static final RegistryObject<Item> BLACK_KING_CRYSTAL = registerModelItem("black_king_crystal");
    public static final RegistryObject<Item> DIAMOND_CRYSTAL_SMASHER = registerModelItem("diamond_crystal_smasher");
    public static final RegistryObject<Item> TRUE_DIAMOND_CRYSTAL_SMASHER = registerModelItem("true_diamond_crystal_smasher");
    public static final RegistryObject<Item> PROPULSION_CUTTER = registerSwordItem("propulsion_cutter");
    public static final RegistryObject<Item> PROPULSOR_SOUL = registerSwordItem("propulsor_soul");
    public static final RegistryObject<Item> RIPPER_CRYSTAL = registerModelItem("ripper_crystal");
    public static final RegistryObject<Item> MODIFIED_BULLIBARD_CRYSTAL = registerModelItem("modified_bullibard_crystal");
    public static final RegistryObject<Item> NEBULA_RING_ENERGY_BLADE = registerSwordItem("nebula_ring_energy_blade");
    public static final RegistryObject<Item> ZETTON_ABSORBER = registerShieldItem("zetton_absorber");
    public static final RegistryObject<Item> ZETTON_CRYSTAL = registerModelItem("zetton_crystal");
    public static final RegistryObject<Item> HYPER_CRYSTAL = registerModelItem("hyper_crystal");
    public static final RegistryObject<Item> RUIN_EX_ELEKING_CRYSTAL = registerModelItem("ruin_ex_eleking_crystal");
    public static final RegistryObject<Item> BLANK_CRYSTAL = registerModelItem("blank_crystal");
    public static final RegistryObject<Item> STARLIGHT_CRYSTAL = registerModelItem("starlight_crystal");
    public static final RegistryObject<Item> KING_JOE_CRYSTAL = registerModelItem("king_joe_crystal");
    public static final RegistryObject<Item> KING_JOE_SMASHER = registerSwordItem("king_joe_smasher");
    public static final RegistryObject<Item> BLACK_KING_SLAUGHTER = registerSwordItem("black_king_slaughter");
    public static final RegistryObject<Item> CAMORRA = registerModelItem("camorra");
    public static final RegistryObject<Item> DEVOURER_RUIN = registerSwordItem("devourer_ruin");
    public static final RegistryObject<Item> MONSTER_SOUL = registerSimpleItem("monster_soul", Rarity.UNCOMMON);
    public static final RegistryObject<Item> STAR_FLOWER_SEED = ITEMS.register(
            "star_flower_seed",
            () -> new StarFlowerSeedItem(new Item.Properties().rarity(Rarity.RARE))
    );
    public static final RegistryObject<Item> PLASMA_CRYSTAL = registerSimpleItem("plasma_crystal", Rarity.UNCOMMON);
    public static final RegistryObject<Item> PLASMA_CORE_FRAGMENTS = registerSimpleItem("plasma_core_fragments", Rarity.UNCOMMON);
    public static final RegistryObject<Item> BULLIBARD_FEATHER = registerSimpleItem("bullibard_feather", Rarity.UNCOMMON);
    public static final RegistryObject<Item> BULLIBARD_FEATHER_STORY = registerSimpleItem("bullibard_feather_story", Rarity.RARE);
    public static final RegistryObject<Item> EVENT_FAREWELL = registerEventItem("event_farewell");
    public static final RegistryObject<Item> EVENT_APOCALYPSE_WAR = registerEventItem("event_apocalypse_war");
    public static final RegistryObject<Item> EVENT_APOCALYPSE_DEATH = registerEventItem("event_apocalypse_death");
    public static final RegistryObject<Item> EVENT_CHIMERA = registerEventItem("event_chimera");
    public static final RegistryObject<Item> EVENT_SISTER_AND_SINNER = registerEventItem("event_sister_and_sinner");
    public static final RegistryObject<Item> EVENT_SOUL_OF_MOUNTAINS = registerEventItem("event_soul_of_mountains");
    public static final RegistryObject<Item> EVENT_BULLIBARD = ITEMS.register(
            "bullibard",
            () -> new BullibardEventItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE))
    );
    public static final RegistryObject<Item> EVENT_WEAPON_OF_HOPE = registerEventItem("event_weapon_of_hope");
    public static final RegistryObject<Item> EVENT_MUTATED_DEMON = registerEventItem("event_mutated_demon");
    public static final RegistryObject<Item> EVENT_DEMON_LAKE = registerEventItem("event_demon_lake");
    public static final RegistryObject<Item> EVENT_DEMON_METEOR = registerEventItem("event_demon_meteor");
    public static final RegistryObject<Item> EVENT_UNKNOWN_THING = registerEventItem("event_unknown_thing");
    public static final RegistryObject<Item> EVENT_DIMENSIONAL_CHALLENGER = registerEventItem("event_dimensional_challenger");
    public static final RegistryObject<Item> EVENT_SOUL_ROAR_PRELUDE = registerEventItem("event_soul_roar_prelude");
    public static final RegistryObject<Item> EVENT_SOUL_ROAR = registerEventItem("event_soul_roar");
    public static final RegistryObject<Item> EVENT_STARRY_RADIANCE = registerEventItem("event_starry_radiance");
    public static final RegistryObject<Item> EVENT_ABDUCTION = registerEventItem("event_abduction");
    public static final RegistryObject<Item> EVENT_GREEDY_CITY = registerEventItem("event_greedy_city");
    public static final RegistryObject<Item> CHICKEN_SPAWN_EGG = ITEMS.register(
            "chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CHICKEN, 0xFFF0B5, 0xD84A34, new Item.Properties())
    );
    public static final RegistryObject<Item> GUARDIAN_ELDER_SPAWN_EGG = ITEMS.register(
            "guardian_elder_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.GUARDIAN_ELDER, 0x5E756C, 0xE1D09B, new Item.Properties())
    );
    public static final RegistryObject<Item> ALLAY_SPAWN_EGG = registerSpawnEgg("allay_spawn_egg", ModEntityTypes.ALLAY, 0x59B6D8, 0xF3E8C4);
    public static final RegistryObject<Item> ANIMATED_METEOR_BLAZMET_SPAWN_EGG = registerSpawnEgg("animated_meteor_blazmet_spawn_egg", ModEntityTypes.ANIMATED_METEOR_BLAZMET, 0x6C5548, 0xD87845);
    public static final RegistryObject<Item> ANIMATED_METEOR_BLAZMET_BOSS_SPAWN_EGG = registerSpawnEgg("animated_meteor_blazmet_boss_spawn_egg", ModEntityTypes.ANIMATED_METEOR_BLAZMET_BOSS, 0x6C5548, 0xD87845);
    public static final RegistryObject<Item> BATTLE_MECH_SPAWN_EGG = registerSpawnEgg("battle_mech_spawn_egg", ModEntityTypes.BATTLE_MECH, 0x4D5965, 0xE74C3C);
    public static final RegistryObject<Item> BLAZE_SPAWN_EGG = registerSpawnEgg("blaze_spawn_egg", ModEntityTypes.BLAZE, 0xF6B33F, 0x5B3216);
    public static final RegistryObject<Item> AXOLOTL_LUCY_SPAWN_EGG = registerSpawnEgg("axolotl_lucy_spawn_egg", ModEntityTypes.AXOLOTL_LUCY, 0xF2B8C6, 0x8A5C78);
    public static final RegistryObject<Item> CERBERUS_SPAWN_EGG = registerSpawnEgg("cerberus_spawn_egg", ModEntityTypes.CERBERUS, 0x2C2B32, 0xC0703A);
    public static final RegistryObject<Item> CAT_SPAWN_EGG = registerSpawnEgg("cat_spawn_egg", ModEntityTypes.CAT, 0xA37B5B, 0xE7C9A9);
    public static final RegistryObject<Item> COW_SPAWN_EGG = registerSpawnEgg("cow_spawn_egg", ModEntityTypes.COW, 0x443626, 0xD8C4A8);
    public static final RegistryObject<Item> COSMIC_BULLIBARD_SPAWN_EGG = registerSpawnEgg("bullibard_spawn_egg", ModEntityTypes.COSMIC_BULLIBARD, 0x242B42, 0xA8D8FF);
    public static final RegistryObject<Item> COSMIC_BULLIBARD_BOSS_SPAWN_EGG = ITEMS.register(
            "bullibard_boss_spawn_egg",
            () -> new CosmicBullibardBossSpawnEggItem(new Item.Properties().rarity(Rarity.UNCOMMON))
    );
    public static final RegistryObject<Item> CRYSTALLIZE_BLACK_KING_SPAWN_EGG = registerSpawnEgg("crystallize_black_king_spawn_egg", ModEntityTypes.CRYSTALLIZE_BLACK_KING, 0x2B2431, 0xA5D8FF);
    public static final RegistryObject<Item> CRYSTALLIZE_BLACK_KING_BOSS_SPAWN_EGG = registerSpawnEgg("crystallize_black_king_boss_spawn_egg", ModEntityTypes.CRYSTALLIZE_BLACK_KING_BOSS, 0x2B2431, 0xA5D8FF);
    public static final RegistryObject<Item> CRYSTALLIZEBLACKKING_SPAWN_EGG = registerSpawnEgg("crystallizeblackking_spawn_egg", ModEntityTypes.CRYSTALLIZEBLACKKING, 0x2B2431, 0x8FD6E8);
    public static final RegistryObject<Item> CRYSTALLIZEBLACKKING_BOSS_SPAWN_EGG = registerSpawnEgg("crystallizeblackking_boss_spawn_egg", ModEntityTypes.CRYSTALLIZEBLACKKING_BOSS, 0x2B2431, 0x8FD6E8);
    public static final RegistryObject<Item> DARK_SOUL_GESPIKET_SPAWN_EGG = registerSpawnEgg("dark_soul_gespiket_spawn_egg", ModEntityTypes.DARK_SOUL_GESPIKET, 0x1F1B28, 0x8B5BD6);
    public static final RegistryObject<Item> DARK_SOUL_GESPIKET_BOSS_SPAWN_EGG = registerSpawnEgg("dark_soul_gespiket_boss_spawn_egg", ModEntityTypes.DARK_SOUL_GESPIKET_BOSS, 0x1F1B28, 0x8B5BD6);
    public static final RegistryObject<Item> EMBER_GUARDIAN_SPAWN_EGG = registerSpawnEgg("ember_guardian_spawn_egg", ModEntityTypes.EMBER_GUARDIAN, 0x402820, 0xF08A3A);
    public static final RegistryObject<Item> ENDERMAN_SPAWN_EGG = registerSpawnEgg("enderman_spawn_egg", ModEntityTypes.ENDERMAN, 0x161616, 0xB05CFF);
    public static final RegistryObject<Item> FOX_SPAWN_EGG = registerSpawnEgg("fox_spawn_egg", ModEntityTypes.FOX, 0xD9822B, 0xF0E2C5);
    public static final RegistryObject<Item> GHAST_SPAWN_EGG = registerSpawnEgg("ghast_spawn_egg", ModEntityTypes.GHAST, 0xF0EAE2, 0xB84646);
    public static final RegistryObject<Item> GUARDIAN_SPAWN_EGG = registerSpawnEgg("guardian_spawn_egg", ModEntityTypes.GUARDIAN, 0x5E756C, 0xE1D09B);
    public static final RegistryObject<Item> HADES_ZAGI_SPAWN_EGG = registerSpawnEgg("hades_zagi_spawn_egg", ModEntityTypes.HADES_ZAGI, 0x171820, 0xC7354A);
    public static final RegistryObject<Item> LEBIC_DEMON_FORM_SPAWN_EGG = registerSpawnEgg("lebic_demon_form_spawn_egg", ModEntityTypes.LEBIC_DEMON_FORM, 0x29212B, 0x7C6CCF);
    public static final RegistryObject<Item> LEBIC_DEMON_FORM_BOSS_SPAWN_EGG = registerSpawnEgg("lebic_demon_form_boss_spawn_egg", ModEntityTypes.LEBIC_DEMON_FORM_BOSS, 0x29212B, 0x7C6CCF);
    public static final RegistryObject<Item> LERBIS_NEMESIS_THE_SIDEKICK_SPAWN_EGG = registerSpawnEgg("lerbis_nemesis_the_sidekick_spawn_egg", ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK, 0x2C2C35, 0x7DD7D2);
    public static final RegistryObject<Item> MOYINGLONG_SPAWN_EGG = registerSpawnEgg("moyinglong_spawn_egg", ModEntityTypes.MOYINGLONG, 0x1D2028, 0x6E87AA);
    public static final RegistryObject<Item> MODIFIED_BULLIBARD_SPAWN_EGG = registerSpawnEgg("modified_bullibard_spawn_egg", ModEntityTypes.MODIFIED_BULLIBARD, 0x343640, 0xD86C4D);
    public static final RegistryObject<Item> MODIFIED_BULLIBARD_BOSS_SPAWN_EGG = registerSpawnEgg("modified_bullibard_boss_spawn_egg", ModEntityTypes.MODIFIED_BULLIBARD_BOSS, 0x343640, 0xD86C4D);
    public static final RegistryObject<Item> PHANTOM_SPAWN_EGG = registerSpawnEgg("phantom_spawn_egg", ModEntityTypes.PHANTOM, 0x5B6175, 0xA7B3D8);
    public static final RegistryObject<Item> PIG_SPAWN_EGG = registerSpawnEgg("pig_spawn_egg", ModEntityTypes.PIG, 0xF2A5B6, 0x6F4C4C);
    public static final RegistryObject<Item> PIGLIN_SPAWN_EGG = registerSpawnEgg("piglin_spawn_egg", ModEntityTypes.PIGLIN, 0xC78C69, 0x3F2D2B);
    public static final RegistryObject<Item> PIGLIN_BRUTE_SPAWN_EGG = registerSpawnEgg("piglin_brute_spawn_egg", ModEntityTypes.PIGLIN_BRUTE, 0x6B4B3B, 0xD6B471);
    public static final RegistryObject<Item> RAZOR_DEMAGA_SPAWN_EGG = registerSpawnEgg("razor_demaga_spawn_egg", ModEntityTypes.RAZOR_DEMAGA, 0x4B3645, 0xD4584B);
    public static final RegistryObject<Item> RAZOR_DEMAGA_BOSS_SPAWN_EGG = registerSpawnEgg("razor_demaga_boss_spawn_egg", ModEntityTypes.RAZOR_DEMAGA_BOSS, 0x4B3645, 0xD4584B);
    public static final RegistryObject<Item> RINGUA_IGONOTA_SPAWN_EGG = registerSpawnEgg("ringua_igonota_spawn_egg", ModEntityTypes.RINGUA_IGONOTA, 0x3B3152, 0xB890FF);
    public static final RegistryObject<Item> RINGUA_IGONOTA_BOSS_SPAWN_EGG = registerSpawnEgg("ringua_igonota_boss_spawn_egg", ModEntityTypes.RINGUA_IGONOTA_BOSS, 0x3B3152, 0xB890FF);
    public static final RegistryObject<Item> RUIN_CHIMERA_SPAWN_EGG = registerSpawnEgg("ruin_chimera_spawn_egg", ModEntityTypes.RUIN_CHIMERA, 0x34252B, 0xD84A34);
    public static final RegistryObject<Item> RUIN_ANTONLA_SPAWN_EGG = registerSpawnEgg("ruin_antonla_spawn_egg", ModEntityTypes.RUIN_ANTONLA, 0xA77D4E, 0x533927);
    public static final RegistryObject<Item> RUIN_ANTONLA_BOSS_SPAWN_EGG = registerSpawnEgg("ruin_antonla_boss_spawn_egg", ModEntityTypes.RUIN_ANTONLA_BOSS, 0xA77D4E, 0x533927);
    public static final RegistryObject<Item> SATAN_HAND_SPAWN_EGG = registerSpawnEgg("satan_hand_spawn_egg", ModEntityTypes.SATAN_HAND, 0x342222, 0xC83C3C);
    public static final RegistryObject<Item> SOUL_OF_MOUNTAINS_SPAWN_EGG = registerSpawnEgg("soul_of_mountains_spawn_egg", ModEntityTypes.SOUL_OF_MOUNTAINS, 0x59636B, 0xE0C36F);
    public static final RegistryObject<Item> SPECIAL_EX_ELEKING_SPAWN_EGG = registerSpawnEgg("special_ex_eleking_spawn_egg", ModEntityTypes.SPECIAL_EX_ELEKING, 0xB7C057, 0x2F2C33);
    public static final RegistryObject<Item> YOUZHUSHOU_SPAWN_EGG = registerSpawnEgg("youzhushou_spawn_egg", ModEntityTypes.YOUZHUSHOU, 0x4B5660, 0x9DC85A);
    public static final RegistryObject<Item> ZOMBIE_SPAWN_EGG = registerSpawnEgg("zombie_spawn_egg", ModEntityTypes.ZOMBIE, 0x536B45, 0x7B5E45);

    public static final List<RegistryObject<Item>> MODEL_ITEMS = List.of(
            HATRED_STICK,
            SMDRTK_HELMET,
            SMDRTK_CHESTPLATE,
            SMDRTK_LEGGINGS,
            SMDRTK_BOOTS,
            SMDRTK_TEAM_LOGO,
            SPACIUM_SUBSTANCE,
            SMDRTK_MULTI_FUNCTION_PISTOL,
            SMDRTK_ENERGY_SMG,
            SMDRTK_ENERGY_RIFLE,

            COURAGE_CUTTER,
            COURAGE_CUTTER_INCOMPLETE,
            KE_E_BO_JUDGEMENT_BLADE,
            RAZOR_DEMAGA_CUTTER,
            GOMORA_LANCE,
            BULLIBARD_RIPPER_RIGHT,
            BULLIBARD_RIPPER_LEFT,
            BOOSTER_CUTTER_ALPHA,
            BOOSTER_CUTTER_BETA,
            BOOSTER_RIPPER,
            PROPULSION_CUTTER,
            PROPULSOR_SOUL,
            NEBULA_RING_ENERGY_BLADE,
            ZETTON_ABSORBER,
            KING_JOE_SMASHER,
            BLACK_KING_SLAUGHTER,
            DEVOURER_RUIN,
            MONSTER_SOUL,
            STAR_FLOWER_SEED,

            BASIC_CRYSTAL,
            BLANK_CRYSTAL,
            KE_E_BO_CRYSTAL,
            RAZOR_DEMAGA_CRYSTAL,
            BOOSTER_CRYSTAL_LIBERATOR,
            DIAMOND_CRYSTAL_SMASHER,
            TRUE_DIAMOND_CRYSTAL_SMASHER,
            BLACK_KING_CRYSTAL,
            RIPPER_CRYSTAL,
            MODIFIED_BULLIBARD_CRYSTAL,
            ZETTON_CRYSTAL,
            HYPER_CRYSTAL,
            RUIN_EX_ELEKING_CRYSTAL,
            STARLIGHT_CRYSTAL,
            KING_JOE_CRYSTAL,
            CAMORRA,
            EVENT_FAREWELL,
            EVENT_APOCALYPSE_WAR,
            EVENT_APOCALYPSE_DEATH,
            EVENT_CHIMERA,
            EVENT_SISTER_AND_SINNER,
            EVENT_SOUL_OF_MOUNTAINS,
            EVENT_BULLIBARD,
            EVENT_WEAPON_OF_HOPE,
            EVENT_MUTATED_DEMON,
            EVENT_DEMON_LAKE,
            EVENT_DEMON_METEOR,
            EVENT_UNKNOWN_THING,
            EVENT_DIMENSIONAL_CHALLENGER,
            EVENT_SOUL_ROAR_PRELUDE,
            EVENT_SOUL_ROAR,
            EVENT_STARRY_RADIANCE,
            EVENT_ABDUCTION,
            EVENT_GREEDY_CITY,
            BULLIBARD_FEATHER,
            BULLIBARD_FEATHER_STORY,

            ALLAY_SPAWN_EGG,
            BLAZE_SPAWN_EGG,
            CAT_SPAWN_EGG,
            COW_SPAWN_EGG,
            ENDERMAN_SPAWN_EGG,
            FOX_SPAWN_EGG,
            GUARDIAN_SPAWN_EGG,
            PHANTOM_SPAWN_EGG,
            PIG_SPAWN_EGG,
            PIGLIN_SPAWN_EGG,
            PIGLIN_BRUTE_SPAWN_EGG,
            ZOMBIE_SPAWN_EGG,

            CHICKEN_SPAWN_EGG,
            GUARDIAN_ELDER_SPAWN_EGG,
            AXOLOTL_LUCY_SPAWN_EGG,
            GHAST_SPAWN_EGG,

            ANIMATED_METEOR_BLAZMET_SPAWN_EGG,
            ANIMATED_METEOR_BLAZMET_BOSS_SPAWN_EGG,
            BATTLE_MECH_SPAWN_EGG,
            CERBERUS_SPAWN_EGG,
            COSMIC_BULLIBARD_SPAWN_EGG,
            COSMIC_BULLIBARD_BOSS_SPAWN_EGG,
            CRYSTALLIZE_BLACK_KING_SPAWN_EGG,
            CRYSTALLIZE_BLACK_KING_BOSS_SPAWN_EGG,
            CRYSTALLIZEBLACKKING_SPAWN_EGG,
            CRYSTALLIZEBLACKKING_BOSS_SPAWN_EGG,
            DARK_SOUL_GESPIKET_SPAWN_EGG,
            DARK_SOUL_GESPIKET_BOSS_SPAWN_EGG,
            EMBER_GUARDIAN_SPAWN_EGG,
            HADES_ZAGI_SPAWN_EGG,
            LEBIC_DEMON_FORM_SPAWN_EGG,
            LEBIC_DEMON_FORM_BOSS_SPAWN_EGG,
            LERBIS_NEMESIS_THE_SIDEKICK_SPAWN_EGG,
            MOYINGLONG_SPAWN_EGG,
            MODIFIED_BULLIBARD_SPAWN_EGG,
            MODIFIED_BULLIBARD_BOSS_SPAWN_EGG,
            RAZOR_DEMAGA_SPAWN_EGG,
            RAZOR_DEMAGA_BOSS_SPAWN_EGG,
            RINGUA_IGONOTA_SPAWN_EGG,
            RINGUA_IGONOTA_BOSS_SPAWN_EGG,
            RUIN_CHIMERA_SPAWN_EGG,
            RUIN_ANTONLA_SPAWN_EGG,
            RUIN_ANTONLA_BOSS_SPAWN_EGG,
            SATAN_HAND_SPAWN_EGG,
            SOUL_OF_MOUNTAINS_SPAWN_EGG,
            SPECIAL_EX_ELEKING_SPAWN_EGG,
            YOUZHUSHOU_SPAWN_EGG
    );

    private ModItems()
    {
    }

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }

    private static RegistryObject<Item> registerModelItem(String name)
    {
        return ITEMS.register(name, () -> new GeckoModelItem(name, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    }

    private static RegistryObject<Item> registerArmorItem(String name, ArmorItem.Type type)
    {
        return registerArmorItem(name, "smdrtk_suit", SmdrtkArmorMaterial.HELMET, type);
    }

    private static RegistryObject<Item> registerArmorItem(String name, String itemModelName, SmdrtkArmorMaterial material, ArmorItem.Type type)
    {
        return ITEMS.register(name, () -> new GeckoArmorItem(
                "smdrtk_suit",
                itemModelName,
                material,
                type,
                new Item.Properties().rarity(Rarity.RARE)
        ));
    }

    private static RegistryObject<Item> registerSwordItem(String name)
    {
        return ITEMS.register(name, () -> new GeckoSwordItem(name, Tiers.DIAMOND, 3, -2.4F, new Item.Properties().rarity(Rarity.UNCOMMON)));
    }

    private static RegistryObject<Item> registerShieldItem(String name)
    {
        return ITEMS.register(name, () -> new GeckoShieldItem(name, new Item.Properties().durability(336).rarity(Rarity.UNCOMMON)));
    }

    private static RegistryObject<Item> registerSimpleItem(String name, Rarity rarity)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties().rarity(rarity)));
    }

    private static RegistryObject<Item> registerEventItem(String name)
    {
        return ITEMS.register(name, () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    }

    private static RegistryObject<Item> registerSpawnEgg(
            String name,
            RegistryObject<? extends EntityType<? extends Mob>> entityType,
            int backgroundColor,
            int highlightColor
    )
    {
        return ITEMS.register(name, () -> new ForgeSpawnEggItem(entityType, backgroundColor, highlightColor, new Item.Properties()));
    }
}
