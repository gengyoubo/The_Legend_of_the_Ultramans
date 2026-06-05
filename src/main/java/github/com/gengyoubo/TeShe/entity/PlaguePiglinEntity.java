package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.level.Level;

public class PlaguePiglinEntity extends Piglin
{
    public PlaguePiglinEntity(EntityType<? extends Piglin> entityType, Level level)
    {
        super(entityType, level);
    }
}
