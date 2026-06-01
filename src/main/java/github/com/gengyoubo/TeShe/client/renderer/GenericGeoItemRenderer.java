package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.item.GeckoModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GenericGeoItemRenderer<T extends Item & GeoItem & GeckoModelProvider> extends GeoItemRenderer<T>
{
    public GenericGeoItemRenderer(T item)
    {
        super(new DefaultedItemGeoModel<T>(new ResourceLocation(TE.MODID, item.getGeckoModelName()))
                .withAltAnimations(new ResourceLocation(TE.MODID, "empty")));
    }
}
