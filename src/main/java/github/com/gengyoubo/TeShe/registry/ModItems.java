package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.item.GeckoModelItem;
import github.com.gengyoubo.TeShe.item.GeckoShieldItem;
import github.com.gengyoubo.TeShe.item.GeckoSwordItem;
import github.com.gengyoubo.TeShe.item.SmdrtkMultiFunctionPistolItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
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

    public static final RegistryObject<Item> SMDRTK_MULTI_FUNCTION_PISTOL = ITEMS.register(
            "smdrtk_multi_function_pistol",
            () -> new SmdrtkMultiFunctionPistolItem(new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.RARE))
    );
    public static final RegistryObject<Item> SMDRTK_SUIT = registerModelItem("smdrtk_suit");
    public static final RegistryObject<Item> SMDRTK_ENERGY_SMG = registerModelItem("smdrtk_energy_smg");
    public static final RegistryObject<Item> SMDRTK_ENERGY_RIFLE = registerModelItem("smdrtk_energy_rifle");
    public static final RegistryObject<Item> COURAGE_CUTTER = registerSwordItem("courage_cutter");
    public static final RegistryObject<Item> COURAGE_CUTTER_INCOMPLETE = registerSwordItem("courage_cutter_incomplete");
    public static final RegistryObject<Item> KE_E_BO_JUDGEMENT_BLADE = registerSwordItem("ke_e_bo_judgement_blade");
    public static final RegistryObject<Item> KE_E_BO_CRYSTAL = registerModelItem("ke_e_bo_crystal");
    public static final RegistryObject<Item> RAZOR_DEMAGA_CUTTER = registerSwordItem("razor_demaga_cutter");
    public static final RegistryObject<Item> RAZOR_DEMAGA_CRYSTAL = registerModelItem("razor_demaga_crystal");
    public static final RegistryObject<Item> GOMORA_LANCE = registerSwordItem("gomora_lance");
    public static final RegistryObject<Item> BASIC_CRYSTAL = registerModelItem("basic_crystal");
    public static final RegistryObject<Item> RIDGE_GOMORA_CRYSTAL = registerModelItem("ridge_gomora_crystal");
    public static final RegistryObject<Item> BULLIBARD_RIPPER_RIGHT = registerSwordItem("bullibard_ripper_right");
    public static final RegistryObject<Item> BULLIBARD_RIPPER_LEFT = registerSwordItem("bullibard_ripper_left");
    public static final RegistryObject<Item> BOOSTER_CUTTER_ALPHA = registerModelItem("booster_cutter_alpha");
    public static final RegistryObject<Item> BOOSTER_CUTTER_BETA = registerModelItem("booster_cutter_beta");
    public static final RegistryObject<Item> BOOSTER_RIPPER = registerModelItem("booster_ripper");
    public static final RegistryObject<Item> BOOSTER_CRYSTAL_LIBERATOR = registerModelItem("booster_crystal_liberator");
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
    public static final RegistryObject<Item> BATTLE_MECH_SPAWN_EGG = registerSpawnEgg("battle_mech_spawn_egg", ModEntityTypes.BATTLE_MECH, 0x4D5965, 0xE74C3C);
    public static final RegistryObject<Item> BLAZE_SPAWN_EGG = registerSpawnEgg("blaze_spawn_egg", ModEntityTypes.BLAZE, 0xF6B33F, 0x5B3216);
    public static final RegistryObject<Item> AXOLOTL_LUCY_SPAWN_EGG = registerSpawnEgg("axolotl_lucy_spawn_egg", ModEntityTypes.AXOLOTL_LUCY, 0xF2B8C6, 0x8A5C78);
    public static final RegistryObject<Item> BABARU_ALIEN_SPAWN_EGG = registerSpawnEgg("babaru_alien_spawn_egg", ModEntityTypes.BABARU_ALIEN, 0x444048, 0xB4A072);
    public static final RegistryObject<Item> BOOSTER_ULTRAMAN_BASIC_SPAWN_EGG = registerSpawnEgg("booster_ultraman_basic_spawn_egg", ModEntityTypes.BOOSTER_ULTRAMAN_BASIC, 0xC9D0D8, 0xD84A34);
    public static final RegistryObject<Item> BOOSTER_ULTRAMAN_COURAGE_BURNING_SPAWN_EGG = registerSpawnEgg("booster_ultraman_courage_burning_spawn_egg", ModEntityTypes.BOOSTER_ULTRAMAN_COURAGE_BURNING, 0xD95032, 0xF0C050);
    public static final RegistryObject<Item> BOOSTER_ULTRAMAN_HYPER_SPAWN_EGG = registerSpawnEgg("booster_ultraman_hyper_spawn_egg", ModEntityTypes.BOOSTER_ULTRAMAN_HYPER, 0xC9D0D8, 0x5DADE2);
    public static final RegistryObject<Item> BOOSTER_ULTRAMAN_HYPER_CERBERUS_ARMED_SPAWN_EGG = registerSpawnEgg("booster_ultraman_hyper_cerberus_armed_spawn_egg", ModEntityTypes.BOOSTER_ULTRAMAN_HYPER_CERBERUS_ARMED, 0x36313A, 0xD3543C);
    public static final RegistryObject<Item> BOOSTER_ULTRAMAN_HYPER_YULUAN_ARMED_SPAWN_EGG = registerSpawnEgg("booster_ultraman_hyper_yuluan_armed_spawn_egg", ModEntityTypes.BOOSTER_ULTRAMAN_HYPER_YULUAN_ARMED, 0x36313A, 0x5DADE2);
    public static final RegistryObject<Item> BOOSTER_ULTRAMAN_STARLIGHT_BURST_SPAWN_EGG = registerSpawnEgg("booster_ultraman_starlight_burst_spawn_egg", ModEntityTypes.BOOSTER_ULTRAMAN_STARLIGHT_BURST, 0xD7E7FF, 0x3F78C5);
    public static final RegistryObject<Item> CERBERUS_SPAWN_EGG = registerSpawnEgg("cerberus_spawn_egg", ModEntityTypes.CERBERUS, 0x2C2B32, 0xC0703A);
    public static final RegistryObject<Item> CAT_SPAWN_EGG = registerSpawnEgg("cat_spawn_egg", ModEntityTypes.CAT, 0xA37B5B, 0xE7C9A9);
    public static final RegistryObject<Item> COW_SPAWN_EGG = registerSpawnEgg("cow_spawn_egg", ModEntityTypes.COW, 0x443626, 0xD8C4A8);
    public static final RegistryObject<Item> COSMIC_BULLIBARD_SPAWN_EGG = registerSpawnEgg("cosmic_bullibard_spawn_egg", ModEntityTypes.COSMIC_BULLIBARD, 0x242B42, 0xA8D8FF);
    public static final RegistryObject<Item> CRYSTALLIZE_BLACK_KING_SPAWN_EGG = registerSpawnEgg("crystallize_black_king_spawn_egg", ModEntityTypes.CRYSTALLIZE_BLACK_KING, 0x2B2431, 0xA5D8FF);
    public static final RegistryObject<Item> CRYSTALLIZEBLACKKING_SPAWN_EGG = registerSpawnEgg("crystallizeblackking_spawn_egg", ModEntityTypes.CRYSTALLIZEBLACKKING, 0x2B2431, 0x8FD6E8);
    public static final RegistryObject<Item> DARK_SOUL_GESPIKET_SPAWN_EGG = registerSpawnEgg("dark_soul_gespiket_spawn_egg", ModEntityTypes.DARK_SOUL_GESPIKET, 0x1F1B28, 0x8B5BD6);
    public static final RegistryObject<Item> DIAMOND_ULTRAMAN_BASIC_SPAWN_EGG = registerSpawnEgg("diamond_ultraman_basic_spawn_egg", ModEntityTypes.DIAMOND_ULTRAMAN_BASIC, 0xD8DCE8, 0x4A8FD8);
    public static final RegistryObject<Item> DIAMOND_ULTRAMAN_RUIN_FLOWER_DEMON_SPAWN_EGG = registerSpawnEgg("diamond_ultraman_ruin_flower_demon_spawn_egg", ModEntityTypes.DIAMOND_ULTRAMAN_RUIN_FLOWER_DEMON, 0x522C58, 0xE27AA8);
    public static final RegistryObject<Item> DIAMOND_ULTRAMAN_SATAN_DEMON_KING_SPAWN_EGG = registerSpawnEgg("diamond_ultraman_satan_demon_king_spawn_egg", ModEntityTypes.DIAMOND_ULTRAMAN_SATAN_DEMON_KING, 0x1B1820, 0xD4493C);
    public static final RegistryObject<Item> DIAMOND_ULTRAMAN_SATAN_RITUAL_SPAWN_EGG = registerSpawnEgg("diamond_ultraman_satan_ritual_spawn_egg", ModEntityTypes.DIAMOND_ULTRAMAN_SATAN_RITUAL, 0x28212B, 0xC94444);
    public static final RegistryObject<Item> DIAMOND_ULTRAMAN_SECOND_SPAWN_EGG = registerSpawnEgg("diamond_ultraman_second_spawn_egg", ModEntityTypes.DIAMOND_ULTRAMAN_SECOND, 0xC9D4E8, 0x3F78C5);
    public static final RegistryObject<Item> EMBER_GUARDIAN_SPAWN_EGG = registerSpawnEgg("ember_guardian_spawn_egg", ModEntityTypes.EMBER_GUARDIAN, 0x402820, 0xF08A3A);
    public static final RegistryObject<Item> ENDERMAN_SPAWN_EGG = registerSpawnEgg("enderman_spawn_egg", ModEntityTypes.ENDERMAN, 0x161616, 0xB05CFF);
    public static final RegistryObject<Item> FOX_SPAWN_EGG = registerSpawnEgg("fox_spawn_egg", ModEntityTypes.FOX, 0xD9822B, 0xF0E2C5);
    public static final RegistryObject<Item> GHAST_SPAWN_EGG = registerSpawnEgg("ghast_spawn_egg", ModEntityTypes.GHAST, 0xF0EAE2, 0xB84646);
    public static final RegistryObject<Item> GUARDIAN_SPAWN_EGG = registerSpawnEgg("guardian_spawn_egg", ModEntityTypes.GUARDIAN, 0x5E756C, 0xE1D09B);
    public static final RegistryObject<Item> HADES_ZAGI_SPAWN_EGG = registerSpawnEgg("hades_zagi_spawn_egg", ModEntityTypes.HADES_ZAGI, 0x171820, 0xC7354A);
    public static final RegistryObject<Item> JIEDUN_NANO_GOLD_ANCIENT_BRIDGE_FUSION_SPAWN_EGG = registerSpawnEgg("jiedun_nano_gold_ancient_bridge_fusion_spawn_egg", ModEntityTypes.JIEDUN_NANO_GOLD_ANCIENT_BRIDGE_FUSION, 0x242733, 0xD7B24A);
    public static final RegistryObject<Item> LEBIC_DEMON_FORM_SPAWN_EGG = registerSpawnEgg("lebic_demon_form_spawn_egg", ModEntityTypes.LEBIC_DEMON_FORM, 0x29212B, 0x7C6CCF);
    public static final RegistryObject<Item> LERBIS_NEMESIS_THE_SIDEKICK_SPAWN_EGG = registerSpawnEgg("lerbis_nemesis_the_sidekick_spawn_egg", ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK, 0x2C2C35, 0x7DD7D2);
    public static final RegistryObject<Item> MOYINGLONG_SPAWN_EGG = registerSpawnEgg("moyinglong_spawn_egg", ModEntityTypes.MOYINGLONG, 0x1D2028, 0x6E87AA);
    public static final RegistryObject<Item> MODIFIED_BULLIBARD_SPAWN_EGG = registerSpawnEgg("modified_bullibard_spawn_egg", ModEntityTypes.MODIFIED_BULLIBARD, 0x343640, 0xD86C4D);
    public static final RegistryObject<Item> NAKELIANS_SPAWN_EGG = registerSpawnEgg("nakelians_spawn_egg", ModEntityTypes.NAKELIANS, 0x726F67, 0xDDC57B);
    public static final RegistryObject<Item> PHANTOM_SPAWN_EGG = registerSpawnEgg("phantom_spawn_egg", ModEntityTypes.PHANTOM, 0x5B6175, 0xA7B3D8);
    public static final RegistryObject<Item> PIG_SPAWN_EGG = registerSpawnEgg("pig_spawn_egg", ModEntityTypes.PIG, 0xF2A5B6, 0x6F4C4C);
    public static final RegistryObject<Item> PIGLIN_SPAWN_EGG = registerSpawnEgg("piglin_spawn_egg", ModEntityTypes.PIGLIN, 0xC78C69, 0x3F2D2B);
    public static final RegistryObject<Item> PIGLIN_BRUTE_SPAWN_EGG = registerSpawnEgg("piglin_brute_spawn_egg", ModEntityTypes.PIGLIN_BRUTE, 0x6B4B3B, 0xD6B471);
    public static final RegistryObject<Item> RAZOR_DEMAGA_SPAWN_EGG = registerSpawnEgg("razor_demaga_spawn_egg", ModEntityTypes.RAZOR_DEMAGA, 0x4B3645, 0xD4584B);
    public static final RegistryObject<Item> RINGUA_IGONOTA_SPAWN_EGG = registerSpawnEgg("ringua_igonota_spawn_egg", ModEntityTypes.RINGUA_IGONOTA, 0x3B3152, 0xB890FF);
    public static final RegistryObject<Item> RUIN_CHIMERA_SPAWN_EGG = registerSpawnEgg("ruin_chimera_spawn_egg", ModEntityTypes.RUIN_CHIMERA, 0x34252B, 0xD84A34);
    public static final RegistryObject<Item> RUIN_ANTONLA_SPAWN_EGG = registerSpawnEgg("ruin_antonla_spawn_egg", ModEntityTypes.RUIN_ANTONLA, 0xA77D4E, 0x533927);
    public static final RegistryObject<Item> SATAN_HAND_SPAWN_EGG = registerSpawnEgg("satan_hand_spawn_egg", ModEntityTypes.SATAN_HAND, 0x342222, 0xC83C3C);
    public static final RegistryObject<Item> SOUL_OF_MOUNTAINS_SPAWN_EGG = registerSpawnEgg("soul_of_mountains_spawn_egg", ModEntityTypes.SOUL_OF_MOUNTAINS, 0x59636B, 0xE0C36F);
    public static final RegistryObject<Item> SPECIAL_EX_ELEKING_SPAWN_EGG = registerSpawnEgg("special_ex_eleking_spawn_egg", ModEntityTypes.SPECIAL_EX_ELEKING, 0xB7C057, 0x2F2C33);
    public static final RegistryObject<Item> YOUZHUSHOU_SPAWN_EGG = registerSpawnEgg("youzhushou_spawn_egg", ModEntityTypes.YOUZHUSHOU, 0x4B5660, 0x9DC85A);
    public static final RegistryObject<Item> ZOMBIE_SPAWN_EGG = registerSpawnEgg("zombie_spawn_egg", ModEntityTypes.ZOMBIE, 0x536B45, 0x7B5E45);

    public static final List<RegistryObject<Item>> MODEL_ITEMS = List.of(
            SMDRTK_MULTI_FUNCTION_PISTOL,
            SMDRTK_SUIT,
            SMDRTK_ENERGY_SMG,
            SMDRTK_ENERGY_RIFLE,
            COURAGE_CUTTER,
            COURAGE_CUTTER_INCOMPLETE,
            KE_E_BO_JUDGEMENT_BLADE,
            KE_E_BO_CRYSTAL,
            RAZOR_DEMAGA_CUTTER,
            RAZOR_DEMAGA_CRYSTAL,
            GOMORA_LANCE,
            BASIC_CRYSTAL,
            RIDGE_GOMORA_CRYSTAL,
            BULLIBARD_RIPPER_RIGHT,
            BULLIBARD_RIPPER_LEFT,
            BOOSTER_CUTTER_ALPHA,
            BOOSTER_CUTTER_BETA,
            BOOSTER_RIPPER,
            BOOSTER_CRYSTAL_LIBERATOR,
            BLACK_KING_CRYSTAL,
            DIAMOND_CRYSTAL_SMASHER,
            TRUE_DIAMOND_CRYSTAL_SMASHER,
            PROPULSION_CUTTER,
            PROPULSOR_SOUL,
            RIPPER_CRYSTAL,
            MODIFIED_BULLIBARD_CRYSTAL,
            NEBULA_RING_ENERGY_BLADE,
            ZETTON_ABSORBER,
            ZETTON_CRYSTAL,
            HYPER_CRYSTAL,
            RUIN_EX_ELEKING_CRYSTAL,
            BLANK_CRYSTAL,
            STARLIGHT_CRYSTAL,
            KING_JOE_CRYSTAL,
            KING_JOE_SMASHER,
            BLACK_KING_SLAUGHTER,
            CAMORRA,
            DEVOURER_RUIN,
            CHICKEN_SPAWN_EGG,
            GUARDIAN_ELDER_SPAWN_EGG,
            ALLAY_SPAWN_EGG,
            ANIMATED_METEOR_BLAZMET_SPAWN_EGG,
            BATTLE_MECH_SPAWN_EGG,
            BLAZE_SPAWN_EGG,
            AXOLOTL_LUCY_SPAWN_EGG,
            BABARU_ALIEN_SPAWN_EGG,
            BOOSTER_ULTRAMAN_BASIC_SPAWN_EGG,
            BOOSTER_ULTRAMAN_COURAGE_BURNING_SPAWN_EGG,
            BOOSTER_ULTRAMAN_HYPER_SPAWN_EGG,
            BOOSTER_ULTRAMAN_HYPER_CERBERUS_ARMED_SPAWN_EGG,
            BOOSTER_ULTRAMAN_HYPER_YULUAN_ARMED_SPAWN_EGG,
            BOOSTER_ULTRAMAN_STARLIGHT_BURST_SPAWN_EGG,
            CERBERUS_SPAWN_EGG,
            CAT_SPAWN_EGG,
            COW_SPAWN_EGG,
            COSMIC_BULLIBARD_SPAWN_EGG,
            CRYSTALLIZE_BLACK_KING_SPAWN_EGG,
            CRYSTALLIZEBLACKKING_SPAWN_EGG,
            DARK_SOUL_GESPIKET_SPAWN_EGG,
            DIAMOND_ULTRAMAN_BASIC_SPAWN_EGG,
            DIAMOND_ULTRAMAN_RUIN_FLOWER_DEMON_SPAWN_EGG,
            DIAMOND_ULTRAMAN_SATAN_DEMON_KING_SPAWN_EGG,
            DIAMOND_ULTRAMAN_SATAN_RITUAL_SPAWN_EGG,
            DIAMOND_ULTRAMAN_SECOND_SPAWN_EGG,
            EMBER_GUARDIAN_SPAWN_EGG,
            ENDERMAN_SPAWN_EGG,
            FOX_SPAWN_EGG,
            GHAST_SPAWN_EGG,
            GUARDIAN_SPAWN_EGG,
            HADES_ZAGI_SPAWN_EGG,
            JIEDUN_NANO_GOLD_ANCIENT_BRIDGE_FUSION_SPAWN_EGG,
            LEBIC_DEMON_FORM_SPAWN_EGG,
            LERBIS_NEMESIS_THE_SIDEKICK_SPAWN_EGG,
            MOYINGLONG_SPAWN_EGG,
            MODIFIED_BULLIBARD_SPAWN_EGG,
            NAKELIANS_SPAWN_EGG,
            PHANTOM_SPAWN_EGG,
            PIG_SPAWN_EGG,
            PIGLIN_SPAWN_EGG,
            PIGLIN_BRUTE_SPAWN_EGG,
            RAZOR_DEMAGA_SPAWN_EGG,
            RINGUA_IGONOTA_SPAWN_EGG,
            RUIN_CHIMERA_SPAWN_EGG,
            RUIN_ANTONLA_SPAWN_EGG,
            SATAN_HAND_SPAWN_EGG,
            SOUL_OF_MOUNTAINS_SPAWN_EGG,
            SPECIAL_EX_ELEKING_SPAWN_EGG,
            YOUZHUSHOU_SPAWN_EGG,
            ZOMBIE_SPAWN_EGG
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

    private static RegistryObject<Item> registerSwordItem(String name)
    {
        return ITEMS.register(name, () -> new GeckoSwordItem(name, Tiers.DIAMOND, 3, -2.4F, new Item.Properties().rarity(Rarity.UNCOMMON)));
    }

    private static RegistryObject<Item> registerShieldItem(String name)
    {
        return ITEMS.register(name, () -> new GeckoShieldItem(name, new Item.Properties().durability(336).rarity(Rarity.UNCOMMON)));
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
