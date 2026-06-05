package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.level.Level;

public class PlagueGuardianEntity extends Guardian
{
    public PlagueGuardianEntity(EntityType<? extends Guardian> entityType, Level level)
    {
        super(entityType, level);
    }
}
