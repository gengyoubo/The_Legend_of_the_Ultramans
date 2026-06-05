package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;

public class PlaguePhantomEntity extends Phantom
{
    public PlaguePhantomEntity(EntityType<? extends Phantom> entityType, Level level)
    {
        super(entityType, level);
    }
}
