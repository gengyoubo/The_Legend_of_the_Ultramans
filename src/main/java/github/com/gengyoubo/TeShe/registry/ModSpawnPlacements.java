package github.com.gengyoubo.TeShe.registry;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.KeanAntonlaEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModSpawnPlacements
{
    private ModSpawnPlacements()
    {
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event)
    {
        event.register(ModEntityTypes.COSMIC_BULLIBARD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntityTypes.CRYSTALLIZE_BLACK_KING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntityTypes.LERBIS_NEMESIS_THE_SIDEKICK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntityTypes.LERBIS_NEMESIS_THE_LEADER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntityTypes.KEAN_ANTONLA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, KeanAntonlaEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
