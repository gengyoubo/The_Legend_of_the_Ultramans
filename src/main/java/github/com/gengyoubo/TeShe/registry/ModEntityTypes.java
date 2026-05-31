package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.GuardianElderEntity;
import github.com.gengyoubo.TeShe.entity.TesheChickenEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntityTypes
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TE.MODID);

    public static final RegistryObject<EntityType<TesheChickenEntity>> CHICKEN = ENTITY_TYPES.register(
            "chicken",
            () -> EntityType.Builder.of(TesheChickenEntity::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .clientTrackingRange(8)
                    .build(TE.MODID + ":chicken")
    );

    public static final RegistryObject<EntityType<GuardianElderEntity>> GUARDIAN_ELDER = ENTITY_TYPES.register(
            "guardian_elder",
            () -> EntityType.Builder.of(GuardianElderEntity::new, MobCategory.MONSTER)
                    .sized(2.0F, 1.4F)
                    .clientTrackingRange(10)
                    .build(TE.MODID + ":guardian_elder")
    );

    private ModEntityTypes()
    {
    }

    public static void register(IEventBus bus)
    {
        ENTITY_TYPES.register(bus);
    }
}
