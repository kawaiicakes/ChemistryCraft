package io.github.kawaiicakes.chemistrycraft.api.block.entity.interfaces;

import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

/**
 * Implementing classes agree to store and handle items.
 */
public interface ItemProvider {
    IItemHandler getItemHandler();
    LazyOptional<IItemHandler> getLazyItemHandler();
    void setLazyItemHandler(LazyOptional<?> itemHandler);
}
