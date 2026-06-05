package github.com.gengyoubo.TeShe.client.renderer;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.AxolotlRenderer;
import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.client.renderer.entity.GuardianRenderer;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Phantom;

public final class PlagueVanillaRenderers
{
    private PlagueVanillaRenderers()
    {
    }

    private static ResourceLocation texture(String name)
    {
        return new ResourceLocation(TE.MODID, "textures/entity/" + name + ".png");
    }

    public static class AxolotlMob extends AxolotlRenderer
    {
        private static final ResourceLocation TEXTURE = texture("axolotl_lucy");

        public AxolotlMob(EntityRendererProvider.Context context)
        {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(Axolotl entity)
        {
            return TEXTURE;
        }
    }

    public static class CatMob extends CatRenderer
    {
        private static final ResourceLocation TEXTURE = texture("cat");

        public CatMob(EntityRendererProvider.Context context)
        {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(Cat entity)
        {
            return TEXTURE;
        }
    }

    public static class CowMob extends CowRenderer
    {
        private static final ResourceLocation TEXTURE = texture("cow");

        public CowMob(EntityRendererProvider.Context context)
        {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(Cow entity)
        {
            return TEXTURE;
        }
    }

    public static class FoxMob extends FoxRenderer
    {
        private static final ResourceLocation TEXTURE = texture("fox");

        public FoxMob(EntityRendererProvider.Context context)
        {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(Fox entity)
        {
            return TEXTURE;
        }
    }

    public static class GhastMob extends GhastRenderer
    {
        private static final ResourceLocation TEXTURE = texture("ghast");
        private static final ResourceLocation SHOOTING_TEXTURE = texture("ghast_shooting");

        public GhastMob(EntityRendererProvider.Context context)
        {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(Ghast entity)
        {
            return entity.isCharging() ? SHOOTING_TEXTURE : TEXTURE;
        }
    }

    public static class GuardianMob extends GuardianRenderer
    {
        private static final ResourceLocation TEXTURE = texture("guardian");

        public GuardianMob(EntityRendererProvider.Context context)
        {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(Guardian entity)
        {
            return TEXTURE;
        }
    }

    public static class PhantomMob extends PhantomRenderer
    {
        private static final ResourceLocation TEXTURE = texture("phantom");

        public PhantomMob(EntityRendererProvider.Context context)
        {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(Phantom entity)
        {
            return TEXTURE;
        }
    }

    public static class PigMob extends PigRenderer
    {
        private static final ResourceLocation TEXTURE = texture("pig");

        public PigMob(EntityRendererProvider.Context context)
        {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(Pig entity)
        {
            return TEXTURE;
        }
    }

    public static class PiglinMob extends PiglinRenderer
    {
        private static final ResourceLocation TEXTURE = texture("piglin");

        public PiglinMob(EntityRendererProvider.Context context)
        {
            super(context, ModelLayers.PIGLIN, ModelLayers.PIGLIN_INNER_ARMOR, ModelLayers.PIGLIN_OUTER_ARMOR, false);
        }

        @Override
        public ResourceLocation getTextureLocation(Mob entity)
        {
            return TEXTURE;
        }
    }

    public static class PiglinBruteMob extends PiglinRenderer
    {
        private static final ResourceLocation TEXTURE = texture("piglin_brute");

        public PiglinBruteMob(EntityRendererProvider.Context context)
        {
            super(context, ModelLayers.PIGLIN_BRUTE, ModelLayers.PIGLIN_BRUTE_INNER_ARMOR, ModelLayers.PIGLIN_BRUTE_OUTER_ARMOR, false);
        }

        @Override
        public ResourceLocation getTextureLocation(Mob entity)
        {
            return TEXTURE;
        }
    }
}
