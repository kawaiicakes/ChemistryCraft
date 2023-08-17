package io.github.kawaiicakes.chemistrycraft.api.constants;

/**
 * Avoids magic numbers. Measured in Celsius.
 */
public enum TemperatureConstants {
    LAVA_TEMPERATURE (1000.00F),
    ABSOLUTE_ZERO (-273.15F);

    TemperatureConstants (double temperature) {}
}
