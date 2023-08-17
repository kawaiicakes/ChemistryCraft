package io.github.kawaiicakes.chemistrycraft.api.properties;

/**
 * Implementing classes promise to provide the properties of some chemical substance. This may be passed to PropertyRegistration
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
