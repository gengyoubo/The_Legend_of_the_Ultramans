package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModParticleTypes
{
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TE.MODID);

    public static final RegistryObject<SimpleParticleType> SPARK = PARTICLE_TYPES.register("spark", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SPARKBLUE = PARTICLE_TYPES.register("sparkblue", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> RED_SPARK = PARTICLE_TYPES.register("red_spark", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SPARK_TO_SPARKBLUE = PARTICLE_TYPES.register("spark_to_sparkblue", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SPARKBLUE_TO_SPARK = PARTICLE_TYPES.register("sparkblue_to_spark", () -> new SimpleParticleType(false));

    private ModParticleTypes()
    {
    }

    public static void register(IEventBus bus)
    {
        PARTICLE_TYPES.register(bus);
    }
}
