package github.com.gengyoubo.TeShe;

import github.com.gengyoubo.TeShe.registry.ModCreativeTabs;
import github.com.gengyoubo.TeShe.registry.ModEntityTypes;
import github.com.gengyoubo.TeShe.registry.ModItems;
import github.com.gengyoubo.TeShe.registry.ModMobEffects;
import github.com.gengyoubo.TeShe.registry.ModParticleTypes;
import github.com.gengyoubo.TeShe.network.ModNetwork;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

@Mod(TE.MODID)
public class TE
{
    public static final String MODID = "teshe";

    public TE(FMLJavaModLoadingContext context)
    {
        GeckoLib.initialize();
        IEventBus bus = context.getModEventBus();
        ModEntityTypes.register(bus);
        ModItems.register(bus);
        ModMobEffects.register(bus);
        ModParticleTypes.register(bus);
        ModCreativeTabs.register(bus);
        ModNetwork.register();
    }
}
