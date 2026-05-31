package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEntityAttributes
{
    private ModEntityAttributes()
    {
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ModEntityTypes.CHICKEN.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .build());
        event.put(ModEntityTypes.GUARDIAN_ELDER.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.18D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .build());
    }
}
