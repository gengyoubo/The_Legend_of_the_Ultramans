package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.level.Level;

public class PlagueHoglinEntity extends Hoglin
{
    public PlagueHoglinEntity(EntityType<? extends Hoglin> entityType, Level level)
    {
        super(entityType, level);
    }
}
