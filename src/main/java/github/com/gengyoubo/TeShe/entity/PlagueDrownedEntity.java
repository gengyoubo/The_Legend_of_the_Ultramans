package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.level.Level;

public class PlagueDrownedEntity extends Drowned
{
    public PlagueDrownedEntity(EntityType<? extends Drowned> entityType, Level level)
    {
        super(entityType, level);
    }
}
