package io.github.kawaiicakes.chemistrycraft.api.utils;

// https://github.com/jtablesaw/tablesaw
// https://poi.apache.org/

/*
 * algorithm: for each segment (a,b) in the curve, cross product the red vector (b-a) with
 * the blue one (x-a) and add 1 to a total if it is positive (or negative depending on what
 * way around your curve is) and if that total is zero then you are below the curve, otherwise
 * you are above it
 */

// Old implementation ^ probably going to derive phases based on free energy of system now

/**
 * Static utility methods for changing a phase diagram into usable code by the API.
 *
 */
public class PhaseDiagram {

}
