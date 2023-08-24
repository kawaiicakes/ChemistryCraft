package io.github.kawaiicakes.chemistrycraft.api.capabilities;

/**
 * This capability is the unit of interaction for pressure inventories. Assume floats are measured in kPa.
 */
public interface IPressureHandler {
    void increasePressure(float kPa);
    void decreasePressure(float kPa);
    float getCurrentPressure();
    float getMaxPressure();
}
