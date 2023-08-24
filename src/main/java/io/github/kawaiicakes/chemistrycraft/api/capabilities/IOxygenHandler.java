package io.github.kawaiicakes.chemistrycraft.api.capabilities;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

/**
 * This capability is the unit of interaction for inventories consuming oxygen. Amounts are measured in mols
 */
@AutoRegisterCapability
public interface IOxygenHandler {
    void addAmount(float mols);
    void decreaseAmount(float mols);
    float getCurrentAmount();
}