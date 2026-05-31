package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.item.GeckoModelItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GenericGeoItemRenderer<T extends GeckoModelItem> extends GeoItemRenderer<T>
{
    public GenericGeoItemRenderer(T item)
    {
        super(new DefaultedItemGeoModel<T>(new ResourceLocation(TE.MODID, item.getGeckoModelName()))
                .withAltAnimations(new ResourceLocation(TE.MODID, "empty")));
    }
}
