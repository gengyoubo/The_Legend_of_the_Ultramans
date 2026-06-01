package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.client.model.GenericGeoArmorModel;
import github.com.gengyoubo.TeShe.item.GeckoModelProvider;
import net.minecraft.world.item.ArmorItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GenericGeoArmorRenderer<T extends ArmorItem & GeoItem & GeckoModelProvider> extends GeoArmorRenderer<T>
{
    public GenericGeoArmorRenderer()
    {
        super(new GenericGeoArmorModel<>());
    }
}
