package github.com.gengyoubo.TeShe.item;

import github.com.gengyoubo.TeShe.entity.CosmicBullibardEntity;
import github.com.gengyoubo.TeShe.registry.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeSpawnEggItem;

public class CosmicBullibardBossSpawnEggItem extends ForgeSpawnEggItem
{
    public CosmicBullibardBossSpawnEggItem(Properties properties)
    {
        super(ModEntityTypes.COSMIC_BULLIBARD, 0x242B42, 0xA8D8FF, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.PASS;
        }

        BlockPos spawnPos = getSpawnPos(level, context.getClickedPos(), context.getClickedFace());
        CosmicBullibardEntity entity = ModEntityTypes.COSMIC_BULLIBARD.get().create(serverLevel);
        if (entity == null) {
            return InteractionResult.FAIL;
        }

        entity.setSpecialIndividual(CosmicBullibardEntity.BOSS_VARIANT);
        entity.moveTo(
                spawnPos.getX() + 0.5D,
                spawnPos.getY(),
                spawnPos.getZ() + 0.5D,
                context.getRotation(),
                0.0F
        );
        entity.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(spawnPos), MobSpawnType.SPAWN_EGG, null, null);
        entity.setSpecialIndividual(CosmicBullibardEntity.BOSS_VARIANT);
        entity.setHealth(entity.getMaxHealth());
        serverLevel.addFreshEntity(entity);

        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        if (player == null || !player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.CONSUME;
    }

    private static BlockPos getSpawnPos(Level level, BlockPos clickedPos, Direction clickedFace)
    {
        BlockState clickedState = level.getBlockState(clickedPos);
        if (clickedState.getCollisionShape(level, clickedPos).isEmpty()) {
            return clickedPos;
        }

        return clickedPos.relative(clickedFace);
    }
}
