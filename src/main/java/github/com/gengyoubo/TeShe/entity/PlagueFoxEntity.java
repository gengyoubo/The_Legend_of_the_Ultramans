package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.Level;

public class PlagueFoxEntity extends Fox
{
    public PlagueFoxEntity(EntityType<? extends Fox> entityType, Level level)
    {
        super(entityType, level);
    }
}
