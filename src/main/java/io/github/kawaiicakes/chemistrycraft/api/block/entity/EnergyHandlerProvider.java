package io.github.kawaiicakes.chemistrycraft.api.block.entity;

import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * Implementing classes agree to store and handle energy.
 */
public interface EnergyHandlerProvider {
    IEnergyStorage getEnergyStorage();
    int getEnergyConsumptionPerTick();
    LazyOptional<IEnergyStorage> getLazyEnergyHandler();
    void setLazyEnergyHandler();
    void setEnergyLevel(int energy);
}
