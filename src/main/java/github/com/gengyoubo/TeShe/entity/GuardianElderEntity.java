package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class GuardianElderEntity extends TesheGeoMob
{
    public GuardianElderEntity(EntityType<? extends PathfinderMob> entityType, Level level)
    {
        super(entityType, level, "guardian_elder");
    }
}
