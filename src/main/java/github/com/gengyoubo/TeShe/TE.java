package github.com.gengyoubo.TeShe;

import github.com.gengyoubo.TeShe.registry.ModCreativeTabs;
import github.com.gengyoubo.TeShe.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TE.MODID)
public class TE
{
    public static final String MODID = "teshe";

    public TE(FMLJavaModLoadingContext context)
    {
        IEventBus bus = context.getModEventBus();
        ModItems.register(bus);
        ModCreativeTabs.register(bus);
    }
}
