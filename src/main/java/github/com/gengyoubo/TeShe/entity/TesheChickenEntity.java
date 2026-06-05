package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;

public class TesheChickenEntity extends Chicken
{
    public TesheChickenEntity(EntityType<? extends Chicken> entityType, Level level)
    {
        super(entityType, level);
    }
}
