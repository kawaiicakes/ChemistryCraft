package io.github.kawaiicakes.chemistrycraft.api.constants;

/**
 * Avoids magic numbers. Measured in Celsius.
 */
public enum TemperatureConstants { //   TODO: is an enum class really necessary? can I use a normal static class instead?
    LAVA_TEMPERATURE (1000.00F),
    FREEZING_POINT_WATER (0.00F),
    BOILING_POINT_WATER (100.00F),
    ABSOLUTE_ZERO (-273.15F);

    TemperatureConstants (float temperature) {}
}
