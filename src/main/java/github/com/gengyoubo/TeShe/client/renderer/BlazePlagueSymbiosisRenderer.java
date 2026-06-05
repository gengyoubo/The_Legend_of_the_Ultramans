package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import github.com.gengyoubo.TeShe.model.BlazePlagueSymbiosis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Blaze;

public class BlazePlagueSymbiosisRenderer<T extends Blaze> extends MobRenderer<T, BlazePlagueSymbiosis<T>>
{
    private final ResourceLocation texture;

    public BlazePlagueSymbiosisRenderer(EntityRendererProvider.Context context, String textureName)
    {
        super(context, new BlazePlagueSymbiosis<>(context.bakeLayer(BlazePlagueSymbiosis.LAYER_LOCATION)), 0.5F);
        this.texture = new ResourceLocation(TE.MODID, "textures/entity/" + textureName + ".png");
    }

    @Override
    public ResourceLocation getTextureLocation(T entity)
    {
        return texture;
    }
}

