package io.github.kawaiicakes.chemistrycraft.api;

/**
 * Implementing classes promise to provide the properties of some chemical substance. This may be passed to TODO
 * to automatically register the objects associated with the implementing class.
 *<br> <br>
 * You may also write a class implementing CustomProperties to easily add new properties to existing objects.
 */
public interface ChemicalProperties {
    String getName();
    String getDescription();
    String getParentName();
    float getBoilingPoint();
    float getMeltingPoint();

}
