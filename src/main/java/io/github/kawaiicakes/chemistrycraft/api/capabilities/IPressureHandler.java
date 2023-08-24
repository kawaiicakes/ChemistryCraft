package io.github.kawaiicakes.chemistrycraft.api.capabilities;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

/**
 * This capability is the unit of interaction for pressure inventories. Assume floats are measured in kPa.
 */
@AutoRegisterCapability
public interface IPressureHandler {
    /**
     * Return the container volume.
     * @return The float representing container volume in meters cubed.
     */
    float getContainerVolume();
    void increasePressure(float kPa);
    void decreasePressure(float kPa);
    float getCurrentPressure();
    float getMaxPressure();
}
