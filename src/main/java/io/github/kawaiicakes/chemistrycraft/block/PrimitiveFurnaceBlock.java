package io.github.kawaiicakes.chemistrycraft.block;

import io.github.kawaiicakes.chemistrycraft.block.entity.PrimitiveFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import static io.github.kawaiicakes.chemistrycraft.registry.BlockEntityRegistry.PRIMITIVE_FURNACE_ENTITY;

public class PrimitiveFurnaceBlock extends BaseEntityBlock {
    protected PrimitiveFurnaceBlock(Properties properties) {
        super(properties);
    }

    /*
        Block entity shit below here!!
     */

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL; //    Makes use of custom block model?
    }

    @Override //    Called when block is removed ig?
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean isMoving) {
        if (blockState.getBlock() != newBlockState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof PrimitiveFurnaceBlockEntity primitiveFurnaceBlockEntity) {
                primitiveFurnaceBlockEntity.drops();
            }
        }
        super.onRemove(blockState, level, blockPos, newBlockState, isMoving);
    }

    @Override //    Called on right click of block
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof PrimitiveFurnaceBlockEntity primitiveFurnaceBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, primitiveFurnaceBlockEntity, blockPos);
            } else {
                throw new IllegalStateException("Missing Container provider for PrimitiveFurnaceBlockEntity!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override //    Called when actual block entity is spawned
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PrimitiveFurnaceBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override //    gets static ticking logic of block entity
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> tBlockEntityType) {
        return createTickerHelper(tBlockEntityType, PRIMITIVE_FURNACE_ENTITY.get(),
                PrimitiveFurnaceBlockEntity::tick);
    }
}
