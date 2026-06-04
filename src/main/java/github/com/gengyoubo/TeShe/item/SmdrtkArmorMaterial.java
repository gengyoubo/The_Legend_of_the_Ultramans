package github.com.gengyoubo.TeShe.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class SmdrtkArmorMaterial implements ArmorMaterial
{
    public static final SmdrtkArmorMaterial HELMET = new SmdrtkArmorMaterial(1000, 9, 0.2F);
    public static final SmdrtkArmorMaterial CHESTPLATE = new SmdrtkArmorMaterial(4000, 10, 0.4F);
    public static final SmdrtkArmorMaterial LEGGINGS = new SmdrtkArmorMaterial(2000, 9, 0.3F);
    public static final SmdrtkArmorMaterial BOOTS = new SmdrtkArmorMaterial(1000, 9, 0.1F);

    private final int durability;
    private final int defense;
    private final float toughness;

    private SmdrtkArmorMaterial(int durability, int defense, float toughness)
    {
        this.durability = durability;
        this.defense = defense;
        this.toughness = toughness;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type)
    {
        return durability;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type)
    {
        return defense;
    }

    @Override
    public int getEnchantmentValue()
    {
        return 15;
    }

    @Override
    public SoundEvent getEquipSound()
    {
        return SoundEvents.ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.of(Items.NETHERITE_INGOT);
    }

    @Override
    public String getName()
    {
        return "teshe:smdrtk";
    }

    @Override
    public float getToughness()
    {
        return toughness;
    }

    @Override
    public float getKnockbackResistance()
    {
        return 0.0F;
    }
}
