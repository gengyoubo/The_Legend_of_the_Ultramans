package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;

public class PlaguePigEntity extends Pig
{
    public PlaguePigEntity(EntityType<? extends Pig> entityType, Level level)
    {
        super(entityType, level);
    }
}
