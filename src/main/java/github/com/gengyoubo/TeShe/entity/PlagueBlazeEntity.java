package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.level.Level;

public class PlagueBlazeEntity extends Blaze
{
    public PlagueBlazeEntity(EntityType<? extends Blaze> entityType, Level level)
    {
        super(entityType, level);
    }
}
