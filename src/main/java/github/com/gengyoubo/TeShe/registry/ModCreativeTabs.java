package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thip.init.THIPModItems;

import static net.thip.init.THIPModTabs.MATERIAL;
import static net.thip.init.THIPModTabs.REGISTRY;

public final class ModCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TE.MODID);

    public static final RegistryObject<CreativeModeTab> TESHE_ITEMS = CREATIVE_MODE_TABS.register(
            "teshe_items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.teshe.items"))
                    .icon(() -> ModItems.SMDRTK_MULTI_FUNCTION_PISTOL.get().getDefaultInstance())
                    .displayItems((parameters, output) -> ModItems.MODEL_ITEMS.forEach(item -> {
                        output.accept(item.get());
                    }))
                    .build()
    );
    private ModCreativeTabs()
    {
    }

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);

    }
}
