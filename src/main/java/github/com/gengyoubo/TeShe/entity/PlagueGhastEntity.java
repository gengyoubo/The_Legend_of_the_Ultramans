package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.Level;

public class PlagueGhastEntity extends Ghast
{
    public PlagueGhastEntity(EntityType<? extends Ghast> entityType, Level level)
    {
        super(entityType, level);
    }
}
