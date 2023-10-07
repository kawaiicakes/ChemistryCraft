package io.github.kawaiicakes.chemistrycraft.init.registry.block.entity;

import io.github.kawaiicakes.chemistrycraft.api.block.entity.ItemProvider;
import io.github.kawaiicakes.chemistrycraft.api.block.entity.OxygenProvider;
import io.github.kawaiicakes.chemistrycraft.api.block.entity.TemperatureProvider;
import io.github.kawaiicakes.chemistrycraft.api.capabilities.IOxygenHandler;
import io.github.kawaiicakes.chemistrycraft.api.capabilities.ITemperatureHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSmeltingBlockEntity extends BlockEntity implements MenuProvider, ItemProvider, TemperatureProvider, OxygenProvider {
    private final ItemStackHandler itemHandler;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public AbstractSmeltingBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, ItemStackHandler itemHandler) {
        super(pType, pPos, pBlockState);
        this.itemHandler = itemHandler;
    }

    @Override
    public IItemHandler getItemHandler() {
        return this.itemHandler;
    }

    @Override
    public LazyOptional<IItemHandler> getLazyItemHandler() {
        return this.lazyItemHandler;
    }

    @Override
    public void setLazyItemHandler(LazyOptional<?> itemHandler) {

    }

    @Override
    public IOxygenHandler getOxygenHandler() {
        return null;
    }

    @Override
    public LazyOptional<IOxygenHandler> getLazyOxygenHandler() {
        return null;
    }

    @Override
    public void setLazyOxygenHandler(LazyOptional<IOxygenHandler> handler) {

    }

    @Override
    public void setAmount(float mols) {

    }

    @Override
    public ITemperatureHandler getTemperatureHandler() {
        return null;
    }

    @Override
    public LazyOptional<ITemperatureHandler> getLazyTemperatureHandler() {
        return null;
    }

    @Override
    public void setLazyTemperatureHandler(LazyOptional<ITemperatureHandler> handler) {

    }

    @Override
    public void setTemperature(float kelvin) {

    }

    @Override
    public abstract Component getDisplayName();

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return null;
    }
}
