package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

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
        registerGenericAttributes(event, ModEntityTypes.BATTLE_MECH);
        registerGenericAttributes(event, ModEntityTypes.BLAZE);
        registerGenericAttributes(event, ModEntityTypes.BOOSTER_ULTRAMAN_BASIC);
        registerGenericAttributes(event, ModEntityTypes.BOOSTER_ULTRAMAN_COURAGE_BURNING);
        registerGenericAttributes(event, ModEntityTypes.BOOSTER_ULTRAMAN_STARLIGHT_BURST);
        registerGenericAttributes(event, ModEntityTypes.CERBERUS);
        registerGenericAttributes(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING);
        registerGenericAttributes(event, ModEntityTypes.CRYSTALLIZEBLACKKING);
        registerGenericAttributes(event, ModEntityTypes.ENDERMAN);
        registerGenericAttributes(event, ModEntityTypes.GHAST);
        registerGenericAttributes(event, ModEntityTypes.HADES_ZAGI);
        registerGenericAttributes(event, ModEntityTypes.JIEDUN_NANO_GOLD_ANCIENT_BRIDGE_FUSION);
        registerGenericAttributes(event, ModEntityTypes.LEBIC_DEMON_FORM);
        registerGenericAttributes(event, ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK);
        registerGenericAttributes(event, ModEntityTypes.MOYINGLONG);
        registerGenericAttributes(event, ModEntityTypes.NAKELIANS);
        registerGenericAttributes(event, ModEntityTypes.RINGUA_IGONOTA);
        registerGenericAttributes(event, ModEntityTypes.YOUZHUSHOU);
        registerGenericAttributes(event, ModEntityTypes.ZOMBIE);
    }

    private static void registerGenericAttributes(
            EntityAttributeCreationEvent event,
            RegistryObject<? extends EntityType<? extends Mob>> entityType
    )
    {
        event.put(entityType.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.22D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .build());
    }
}
