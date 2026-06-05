package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class PlagueSymbiosisModelRenderer<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M>
{
    private final ResourceLocation texture;

    public PlagueSymbiosisModelRenderer(EntityRendererProvider.Context context, M model, String textureName, float shadowRadius)
    {
        super(context, model, shadowRadius);
        this.texture = new ResourceLocation(TE.MODID, "textures/entity/" + textureName + ".png");
    }

    @Override
    public ResourceLocation getTextureLocation(T entity)
    {
        return texture;
    }
}
