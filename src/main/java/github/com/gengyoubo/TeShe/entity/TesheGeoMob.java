package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class TesheGeoMob extends PathfinderMob implements GeoEntity
{
    private final String geckoModelName;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected TesheGeoMob(EntityType<? extends PathfinderMob> entityType, Level level, String geckoModelName)
    {
        super(entityType, level);
        this.geckoModelName = geckoModelName;
    }

    public String getGeckoModelName()
    {
        return geckoModelName;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }
}
