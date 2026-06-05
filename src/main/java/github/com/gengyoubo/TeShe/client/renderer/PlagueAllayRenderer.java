package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.entity.PlagueAllayEntity;
import github.com.gengyoubo.TeShe.model.AllayPlagueSymbiosis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class PlagueAllayRenderer extends MobRenderer<PlagueAllayEntity, AllayPlagueSymbiosis<PlagueAllayEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(TE.MODID, "textures/entity/allay.png");

    public PlagueAllayRenderer(EntityRendererProvider.Context context)
    {
        super(context, new AllayPlagueSymbiosis<>(context.bakeLayer(AllayPlagueSymbiosis.LAYER_LOCATION)), 0.4F);
        addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(PlagueAllayEntity entity)
    {
        return TEXTURE;
    }

    @Override
    protected int getBlockLightLevel(PlagueAllayEntity entity, BlockPos pos)
    {
        return 15;
    }
}

