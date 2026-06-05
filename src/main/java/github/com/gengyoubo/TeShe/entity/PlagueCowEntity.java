package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;

public class PlagueCowEntity extends Cow
{
    public PlagueCowEntity(EntityType<? extends Cow> entityType, Level level)
    {
        super(entityType, level);
    }
}
