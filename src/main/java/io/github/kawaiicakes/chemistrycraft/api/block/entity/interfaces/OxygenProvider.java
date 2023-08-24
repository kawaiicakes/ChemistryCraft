package io.github.kawaiicakes.chemistrycraft.api.block.entity.interfaces;

import io.github.kawaiicakes.chemistrycraft.api.capabilities.IOxygenHandler;
import net.minecraftforge.common.util.LazyOptional;

public interface OxygenProvider {
    IOxygenHandler getOxygenHandler();
    LazyOptional<IOxygenHandler> getLazyOxygenHandler();
    void setLazyOxygenHandler(LazyOptional<IOxygenHandler> handler);
    void setAmount(float mols);
}
