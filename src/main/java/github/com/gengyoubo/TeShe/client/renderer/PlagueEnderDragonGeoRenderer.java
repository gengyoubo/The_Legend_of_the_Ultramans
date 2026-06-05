package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.client.model.PlagueEnderDragonGeoModel;
import github.com.gengyoubo.TeShe.entity.PlagueEnderDragonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PlagueEnderDragonGeoRenderer extends GeoEntityRenderer<PlagueEnderDragonEntity>
{
    public PlagueEnderDragonGeoRenderer(EntityRendererProvider.Context context)
    {
        super(context, new PlagueEnderDragonGeoModel());
        this.shadowRadius = 1.2F;
        TE.LOGGER.info("Using Gecko renderer for teshe:moyinglong with geo/entity/moyinglong.geo.json");
    }
}
