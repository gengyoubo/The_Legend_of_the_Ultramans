package github.com.gengyoubo.TeShe.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SparkParticle extends SimpleAnimatedParticle
{
    private static final int LAST_FRAME_AGE = 6;
    private static final int BEAM_LAST_FRAME_AGE = 7;

    protected SparkParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites)
    {
        this(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites, LAST_FRAME_AGE);
    }

    protected SparkParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites, int lastFrameAge)
    {
        super(level, x, y, z, sprites, 0.0F);
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.lifetime = lastFrameAge;
        this.quadSize = 0.35F;
        this.hasPhysics = false;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick()
    {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites)
        {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            return new SparkParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
        }
    }

    public static class BeamProvider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public BeamProvider(SpriteSet sprites)
        {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            return new SparkParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites, BEAM_LAST_FRAME_AGE);
        }
    }
}
