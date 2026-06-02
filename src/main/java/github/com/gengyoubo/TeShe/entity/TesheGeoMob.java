package github.com.gengyoubo.TeShe.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class TesheGeoMob extends PathfinderMob implements GeoEntity
{
    private final String geckoModelName;
    private final String geckoAnimationsName;
    private final RawAnimation defaultAnimation;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected TesheGeoMob(EntityType<? extends PathfinderMob> entityType, Level level, String geckoModelName)
    {
        this(entityType, level, geckoModelName, "empty", null);
    }

    protected TesheGeoMob(EntityType<? extends PathfinderMob> entityType, Level level, String geckoModelName, String geckoAnimationsName, String defaultAnimationName)
    {
        super(entityType, level);
        this.geckoModelName = geckoModelName;
        this.geckoAnimationsName = geckoAnimationsName;
        this.defaultAnimation = defaultAnimationName == null ? null : RawAnimation.begin().thenLoop(defaultAnimationName);
    }

    public String getGeckoModelName()
    {
        return geckoModelName;
    }

    public String getGeckoAnimationsName()
    {
        return geckoAnimationsName;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        if (defaultAnimation != null) {
            controllers.add(new AnimationController<>(this, "default_controller", 0, state -> state.setAndContinue(defaultAnimation)));
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }
}
