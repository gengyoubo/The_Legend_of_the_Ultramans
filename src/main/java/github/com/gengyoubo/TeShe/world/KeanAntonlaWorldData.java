package github.com.gengyoubo.TeShe.world;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public final class KeanAntonlaWorldData extends SavedData
{
    private static final String DATA_NAME = TE.MODID + "_kean_antonla";
    private static final String WORLDGEN_ENABLED = "WorldgenEnabled";
    private boolean worldgenEnabled;

    public static KeanAntonlaWorldData get(ServerLevel level)
    {
        ServerLevel overworld = level.getServer().overworld();
        return overworld.getDataStorage().computeIfAbsent(KeanAntonlaWorldData::load, KeanAntonlaWorldData::new, DATA_NAME);
    }

    public static KeanAntonlaWorldData load(CompoundTag tag)
    {
        KeanAntonlaWorldData data = new KeanAntonlaWorldData();
        data.worldgenEnabled = tag.getBoolean(WORLDGEN_ENABLED);
        return data;
    }

    public boolean isWorldgenEnabled()
    {
        return worldgenEnabled;
    }

    public void enableWorldgen()
    {
        if (!worldgenEnabled) {
            worldgenEnabled = true;
            setDirty();
        }
    }

    @Override
    public CompoundTag save(CompoundTag tag)
    {
        tag.putBoolean(WORLDGEN_ENABLED, worldgenEnabled);
        return tag;
    }
}
