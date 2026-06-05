package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.level.Level;

public class PlagueHuskEntity extends Husk
{
    public PlagueHuskEntity(EntityType<? extends Husk> entityType, Level level)
    {
        super(entityType, level);
    }
}
