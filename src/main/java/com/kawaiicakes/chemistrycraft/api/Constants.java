package com.kawaiicakes.chemistrycraft.api;

/**
 * This class contains static constants measured in Celsius, but otherwise uses
 * SI units where applicable. Primitive data types are chosen to be the smallest
 * possible needed to contain the value to save a negligible amount of memory.
 * Cast where appropriate.
 */
public class Constants {
    public static final short LAVA_TEMPERATURE = 1_000;
    public static final float ABSOLUTE_ZERO = -273.15F;
}
