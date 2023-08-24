package io.github.kawaiicakes.chemistrycraft.api.capabilities;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

/**
 * This capability is the unit of interaction for temperature inventories. Assume floats are measured in Kelvin as
 * opposed to Joules; calculation of change in temperature due to heat exchange should be done elsewhere.
 */
@AutoRegisterCapability
public interface ITemperatureHandler {
    void increaseTemperature(float kelvin);

    /**
     * Overriding classes should account for ambient temperature loss to the surroundings.
     * @param decrement the float by which to decrease the temperature.
     */
    void decreaseTemperature(float kelvin);
    float getCurrentTemperature();
    float getMaxTemperature();
}