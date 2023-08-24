package io.github.kawaiicakes.chemistrycraft.api.block.entity;

import io.github.kawaiicakes.chemistrycraft.api.capabilities.ITemperatureHandler;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Implementing classes agree to retain and handle temperature.
 */
public interface TemperatureProvider {
    ITemperatureHandler getTemperatureHandler();
    LazyOptional<ITemperatureHandler> getLazyTemperatureHandler();
    void setLazyTemperatureHandler(LazyOptional<ITemperatureHandler> handler);
    void setTemperature(float kelvin);
}
