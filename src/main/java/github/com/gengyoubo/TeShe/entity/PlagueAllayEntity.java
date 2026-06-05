package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.level.Level;

public class PlagueAllayEntity extends Allay
{
    public PlagueAllayEntity(EntityType<? extends Allay> entityType, Level level)
    {
        super(entityType, level);
    }
}
