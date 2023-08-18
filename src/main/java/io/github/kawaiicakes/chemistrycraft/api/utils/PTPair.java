package io.github.kawaiicakes.chemistrycraft.api.utils;

/**
 * This class simply holds two float values each representing temperature and pressure.
 * Can represent coordinates on a phase diagram.
 * This is used when defining the critical or triple points.
 * <br><br>
 * Values must be measured in Celsius and kPa.
 */
public record PTPair(float temperature, float pressure) {
    public boolean isAbsolutelyGreater(PTPair ptPair) {
        return (this.temperature > ptPair.temperature && this.pressure > ptPair.pressure);
    }
}
