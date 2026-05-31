package github.com.gengyoubo.TeShe.item;

import github.com.gengyoubo.TeShe.client.renderer.GenericGeoItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class GeckoModelItem extends Item implements GeoItem
{
    private final String geckoModelName;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GeckoModelItem(String geckoModelName, Properties properties)
    {
        super(properties);
        this.geckoModelName = geckoModelName;
    }

    public String getGeckoModelName()
    {
        return geckoModelName;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer)
    {
        consumer.accept(new IClientItemExtensions()
        {
            private BlockEntityWithoutLevelRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer()
            {
                if (renderer == null) {
                    renderer = new GenericGeoItemRenderer<>(GeckoModelItem.this);
                }
                return renderer;
            }
        });
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
