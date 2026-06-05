package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.level.Level;

public class GuardianElderEntity extends ElderGuardian
{
    public GuardianElderEntity(EntityType<? extends ElderGuardian> entityType, Level level)
    {
        super(entityType, level);
    }
}
