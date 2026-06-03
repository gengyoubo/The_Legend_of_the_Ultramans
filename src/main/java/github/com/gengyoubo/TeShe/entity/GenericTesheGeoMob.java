package github.com.gengyoubo.TeShe.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class GenericTesheGeoMob extends TesheGeoMob
{
    private final boolean hasBossBar;
    private final ServerBossEvent bossInfo;
    private final String bossTranslationKey;

    public GenericTesheGeoMob(EntityType<? extends PathfinderMob> entityType, Level level, String geckoModelName)
    {
        this(entityType, level, geckoModelName, "empty", null, null);
    }

    public GenericTesheGeoMob(EntityType<? extends PathfinderMob> entityType, Level level, String geckoModelName, String geckoAnimationsName, String defaultAnimationName)
    {
        this(entityType, level, geckoModelName, geckoAnimationsName, defaultAnimationName, null);
    }

    public GenericTesheGeoMob(EntityType<? extends PathfinderMob> entityType, Level level, String geckoModelName, String bossTranslationKey)
    {
        this(entityType, level, geckoModelName, "empty", null, bossTranslationKey);
    }

    public GenericTesheGeoMob(
            EntityType<? extends PathfinderMob> entityType,
            Level level,
            String geckoModelName,
            String geckoAnimationsName,
            String defaultAnimationName,
            String bossTranslationKey
    )
    {
        super(entityType, level, geckoModelName, geckoAnimationsName, defaultAnimationName);
        this.hasBossBar = bossTranslationKey != null;
        this.bossTranslationKey = bossTranslationKey;
        this.bossInfo = hasBossBar ? createBossInfo(bossTranslationKey) : null;
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        if (!level().isClientSide() && hasBossBar) {
            bossInfo.setName(getDisplayName());
            bossInfo.setProgress(getHealth() / getMaxHealth());
        }
    }

    @Override
    public Component getDisplayName()
    {
        if (hasBossBar) {
            return Component.translatable(bossTranslationKey);
        }

        return super.getDisplayName();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player)
    {
        super.startSeenByPlayer(player);
        if (hasBossBar) {
            bossInfo.addPlayer(player);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player)
    {
        super.stopSeenByPlayer(player);
        if (hasBossBar) {
            bossInfo.removePlayer(player);
        }
    }

    @Override
    public void remove(RemovalReason reason)
    {
        if (hasBossBar) {
            bossInfo.removeAllPlayers();
        }
        super.remove(reason);
    }

    private ServerBossEvent createBossInfo(String translationKey)
    {
        return new ServerBossEvent(
                Component.translatable(translationKey),
                BossEvent.BossBarColor.RED,
                BossEvent.BossBarOverlay.PROGRESS
        );
    }
}
