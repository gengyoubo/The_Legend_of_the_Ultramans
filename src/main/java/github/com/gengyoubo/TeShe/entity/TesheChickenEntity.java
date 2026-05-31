package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class TesheChickenEntity extends TesheGeoMob
{
    public TesheChickenEntity(EntityType<? extends PathfinderMob> entityType, Level level)
    {
        super(entityType, level, "chicken");
    }
}
