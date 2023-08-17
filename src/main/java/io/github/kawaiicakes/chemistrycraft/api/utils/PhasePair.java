package io.github.kawaiicakes.chemistrycraft.api.utils;

/**
 * This class simply holds two float values each representing temperature and pressure.
 * This is used when defining the critical or triple points.
 * <br><br>
 * Values must be measured in Celsius and kPa.
 */
public record PhasePair(float temperature, float pressure) {
    public boolean isAbsolutelyGreater(PhasePair phasePair) {
        return (this.temperature > phasePair.temperature && this.pressure > phasePair.pressure);
    }
}
