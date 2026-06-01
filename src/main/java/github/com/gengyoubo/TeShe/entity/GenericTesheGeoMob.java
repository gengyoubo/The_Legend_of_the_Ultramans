package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class GenericTesheGeoMob extends TesheGeoMob
{
    public GenericTesheGeoMob(EntityType<? extends PathfinderMob> entityType, Level level, String geckoModelName)
    {
        super(entityType, level, geckoModelName);
    }
}
