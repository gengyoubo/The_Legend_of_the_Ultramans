package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModMobEffects
{
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TE.MODID);

    public static final RegistryObject<MobEffect> LIGHT_SEED = MOB_EFFECTS.register(
            "light_seed",
            () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 0xF9F5C8)
    );
    public static final RegistryObject<MobEffect> PLUNDER = MOB_EFFECTS.register(
            "plunder",
            () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 0xD9B15F)
    );

    private ModMobEffects()
    {
    }

    public static void register(IEventBus bus)
    {
        MOB_EFFECTS.register(bus);
    }

    private static final class SimpleMobEffect extends MobEffect
    {
        private SimpleMobEffect(MobEffectCategory category, int color)
        {
            super(category, color);
        }
    }
}
