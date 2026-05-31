package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.item.GeckoModelItem;
import github.com.gengyoubo.TeShe.item.SmdrtkMultiFunctionPistolItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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
    public static final RegistryObject<Item> COURAGE_CUTTER = registerModelItem("courage_cutter");
    public static final RegistryObject<Item> COURAGE_CUTTER_INCOMPLETE = registerModelItem("courage_cutter_incomplete");
    public static final RegistryObject<Item> KE_E_BO_JUDGEMENT_BLADE = registerModelItem("ke_e_bo_judgement_blade");
    public static final RegistryObject<Item> KE_E_BO_CRYSTAL = registerModelItem("ke_e_bo_crystal");
    public static final RegistryObject<Item> RAZOR_DEMAGA_CUTTER = registerModelItem("razor_demaga_cutter");
    public static final RegistryObject<Item> RAZOR_DEMAGA_CRYSTAL = registerModelItem("razor_demaga_crystal");
    public static final RegistryObject<Item> GOMORA_LANCE = registerModelItem("gomora_lance");
    public static final RegistryObject<Item> BASIC_CRYSTAL = registerModelItem("basic_crystal");
    public static final RegistryObject<Item> RIDGE_GOMORA_CRYSTAL = registerModelItem("ridge_gomora_crystal");
    public static final RegistryObject<Item> BULLIBARD_RIPPER_RIGHT = registerModelItem("bullibard_ripper_right");
    public static final RegistryObject<Item> BULLIBARD_RIPPER_LEFT = registerModelItem("bullibard_ripper_left");
    public static final RegistryObject<Item> BOOSTER_CUTTER_ALPHA = registerModelItem("booster_cutter_alpha");
    public static final RegistryObject<Item> BOOSTER_CUTTER_BETA = registerModelItem("booster_cutter_beta");
    public static final RegistryObject<Item> BOOSTER_RIPPER = registerModelItem("booster_ripper");
    public static final RegistryObject<Item> BOOSTER_CRYSTAL_LIBERATOR = registerModelItem("booster_crystal_liberator");
    public static final RegistryObject<Item> BLACK_KING_CRYSTAL = registerModelItem("black_king_crystal");
    public static final RegistryObject<Item> DIAMOND_CRYSTAL_SMASHER = registerModelItem("diamond_crystal_smasher");
    public static final RegistryObject<Item> TRUE_DIAMOND_CRYSTAL_SMASHER = registerModelItem("true_diamond_crystal_smasher");
    public static final RegistryObject<Item> PROPULSION_CUTTER = registerModelItem("propulsion_cutter");
    public static final RegistryObject<Item> PROPULSOR_SOUL = registerModelItem("propulsor_soul");
    public static final RegistryObject<Item> SATAN_HAND = registerModelItem("satan_hand");
    public static final RegistryObject<Item> RIPPER_CRYSTAL = registerModelItem("ripper_crystal");
    public static final RegistryObject<Item> MODIFIED_BULLIBARD_CRYSTAL = registerModelItem("modified_bullibard_crystal");
    public static final RegistryObject<Item> NEBULA_RING_ENERGY_BLADE = registerModelItem("nebula_ring_energy_blade");
    public static final RegistryObject<Item> ZETTON_ABSORBER = registerModelItem("zetton_absorber");
    public static final RegistryObject<Item> ZETTON_CRYSTAL = registerModelItem("zetton_crystal");
    public static final RegistryObject<Item> HYPER_CRYSTAL = registerModelItem("hyper_crystal");
    public static final RegistryObject<Item> RUIN_EX_ELEKING_CRYSTAL = registerModelItem("ruin_ex_eleking_crystal");
    public static final RegistryObject<Item> BLANK_CRYSTAL = registerModelItem("blank_crystal");
    public static final RegistryObject<Item> STARLIGHT_CRYSTAL = registerModelItem("starlight_crystal");
    public static final RegistryObject<Item> KING_JOE_CRYSTAL = registerModelItem("king_joe_crystal");
    public static final RegistryObject<Item> KING_JOE_SMASHER = registerModelItem("king_joe_smasher");
    public static final RegistryObject<Item> CHICKEN_SPAWN_EGG = ITEMS.register(
            "chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CHICKEN, 0xFFF0B5, 0xD84A34, new Item.Properties())
    );
    public static final RegistryObject<Item> GUARDIAN_ELDER_SPAWN_EGG = ITEMS.register(
            "guardian_elder_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.GUARDIAN_ELDER, 0x5E756C, 0xE1D09B, new Item.Properties())
    );

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
            SATAN_HAND,
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
            CHICKEN_SPAWN_EGG,
            GUARDIAN_ELDER_SPAWN_EGG
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
}
