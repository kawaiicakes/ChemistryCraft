package io.github.kawaiicakes.chemistrycraft.api.block.entity.interfaces;

import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * Implementing classes agree to store and handle energy.
 */
public interface EnergyHandlerProvider {
    IEnergyStorage getEnergyStorage();

    /**
     * Returns the energy consumed by this per tick.
     * @return The energy as an <code>int</code> consumed by the implementing class per tick.
     */
    int getEnergyConsumptionPerTick();
    LazyOptional<IEnergyStorage> getLazyEnergyHandler();
    void setLazyEnergyHandler(LazyOptional<IEnergyStorage> energyHandler);
    void setEnergyLevel(int energy);
}
