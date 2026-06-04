package github.com.gengyoubo.TeShe.world;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.thip.init.THIPModItems;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = TE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBrewingRecipes
{
    private ModBrewingRecipes()
    {
    }

    @SubscribeEvent
    public static void registerBrewingRecipes(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> BrewingRecipeRegistry.addRecipe(new MutagenicInducerBrewingRecipe()));
    }

    private static final class MutagenicInducerBrewingRecipe implements IBrewingRecipe
    {
        @Override
        public boolean isInput(ItemStack input)
        {
            return input.is(Items.POTION) && PotionUtils.getPotion(input) == Potions.POISON;
        }

        @Override
        public boolean isIngredient(ItemStack ingredient)
        {
            return ingredient.is(THIPModItems.ANTLAR_MAMDIDLE_PINCERS.get());
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient)
        {
            if (!isInput(input) || !isIngredient(ingredient)) {
                return ItemStack.EMPTY;
            }

            return new ItemStack(ModItems.MUTAGENIC_INDUCER.get());
        }
    }
}
