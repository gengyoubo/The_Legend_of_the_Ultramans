package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.CosmicBullibardEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
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
        event.put(ModEntityTypes.ALLAY.get(), Allay.createAttributes().build());
        registerGenericAttributes(event, ModEntityTypes.ANIMATED_METEOR_BLAZMET);
        registerGenericAttributes(event, ModEntityTypes.AXOLOTL_LUCY);
        registerGenericAttributes(event, ModEntityTypes.BATTLE_MECH);
        event.put(ModEntityTypes.BLAZE.get(), Blaze.createAttributes().build());
        registerGenericAttributes(event, ModEntityTypes.CERBERUS);
        event.put(ModEntityTypes.CAT.get(), Cat.createAttributes().build());
        event.put(ModEntityTypes.COW.get(), Cow.createAttributes().build());
        event.put(ModEntityTypes.COSMIC_BULLIBARD.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, CosmicBullibardEntity.NORMAL_MAX_HEALTH)
                .add(Attributes.ARMOR, CosmicBullibardEntity.NORMAL_ARMOR)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.ATTACK_DAMAGE, CosmicBullibardEntity.NORMAL_ATTACK_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .build());
        registerGenericAttributes(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING);
        registerGenericAttributes(event, ModEntityTypes.CRYSTALLIZE_BLACK_KING_BOSS);
        registerGenericAttributes(event, ModEntityTypes.CRYSTALLIZEBLACKKING);
        registerGenericAttributes(event, ModEntityTypes.CRYSTALLIZEBLACKKING_BOSS);
        registerGenericAttributes(event, ModEntityTypes.DARK_SOUL_GESPIKET);
        registerGenericAttributes(event, ModEntityTypes.DARK_SOUL_GESPIKET_BOSS);
        registerGenericAttributes(event, ModEntityTypes.EMBER_GUARDIAN);
        event.put(ModEntityTypes.ENDERMAN.get(), EnderMan.createAttributes().build());
        event.put(ModEntityTypes.FOX.get(), Fox.createAttributes().build());
        registerGenericAttributes(event, ModEntityTypes.GHAST);
        event.put(ModEntityTypes.GUARDIAN.get(), Guardian.createAttributes().build());
        registerGenericAttributes(event, ModEntityTypes.HADES_ZAGI);
        registerGenericAttributes(event, ModEntityTypes.LEBIC_DEMON_FORM);
        registerGenericAttributes(event, ModEntityTypes.LEBIC_DEMON_FORM_BOSS);
        registerGenericAttributes(event, ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK);
        registerGenericAttributes(event, ModEntityTypes.MOYINGLONG);
        registerGenericAttributes(event, ModEntityTypes.MODIFIED_BULLIBARD);
        registerGenericAttributes(event, ModEntityTypes.MODIFIED_BULLIBARD_BOSS);
        event.put(ModEntityTypes.PHANTOM.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .build());
        event.put(ModEntityTypes.PIG.get(), Pig.createAttributes().build());
        event.put(ModEntityTypes.PIGLIN.get(), Piglin.createAttributes().build());
        event.put(ModEntityTypes.PIGLIN_BRUTE.get(), PiglinBrute.createAttributes().build());
        registerGenericAttributes(event, ModEntityTypes.ANIMATED_METEOR_BLAZMET_BOSS);
        registerGenericAttributes(event, ModEntityTypes.RAZOR_DEMAGA);
        registerGenericAttributes(event, ModEntityTypes.RAZOR_DEMAGA_BOSS);
        registerGenericAttributes(event, ModEntityTypes.RINGUA_IGONOTA);
        registerGenericAttributes(event, ModEntityTypes.RINGUA_IGONOTA_BOSS);
        registerGenericAttributes(event, ModEntityTypes.RUIN_CHIMERA);
        registerGenericAttributes(event, ModEntityTypes.RUIN_ANTONLA);
        registerGenericAttributes(event, ModEntityTypes.RUIN_ANTONLA_BOSS);
        registerGenericAttributes(event, ModEntityTypes.SATAN_HAND);
        registerGenericAttributes(event, ModEntityTypes.SOUL_OF_MOUNTAINS);
        registerGenericAttributes(event, ModEntityTypes.SPECIAL_EX_ELEKING);
        registerGenericAttributes(event, ModEntityTypes.YOUZHUSHOU);
        event.put(ModEntityTypes.ZOMBIE.get(), Zombie.createAttributes().build());
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
