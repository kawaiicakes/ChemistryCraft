package io.github.kawaiicakes.chemistrycraft.api.capabilities;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

/**
 * This capability is the unit of interaction for pressure inventories. Assume floats are measured in kPa.
 */
@AutoRegisterCapability
public interface IPressureHandler {
    void increasePressure(float kPa);
    void decreasePressure(float kPa);
    float getCurrentPressure();
    float getMaxPressure();
}
