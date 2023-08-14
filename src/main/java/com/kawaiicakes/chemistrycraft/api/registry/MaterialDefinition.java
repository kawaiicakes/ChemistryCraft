package com.kawaiicakes.chemistrycraft.api.registry;

import com.kawaiicakes.chemistrycraft.api.Constants;
import net.minecraft.world.item.Item;

import java.util.Collection;

/**
 * This class is used to define new materials and their properties, who are automatically registered
 * as the specified objects with their corresponding properties. These properties allow the objects
 * they are assigned to behave as intended with the mod. This offers a great deal of convenience.
 * <br><br>
 * Its purpose is to provide a main entry point for registering new items and such with the game.
 * The intent behind that is to allow for registering stuff either from code or from JSON, or any
 * other way which can interface with this method.
 * <br><br>
 * Addon makers may use it by instantiating a new instance of the class. Arguments may be passed
 * as null to use the default values defined in this class. It can then be stored in a field and
 * used with <code>#builder</code> to act as 'presets' if so desired. See <code>MaterialDefinitionRegistry</code>.
 * <br><br>
 * Even more specific behaviours may be implemented by subclassing it.
 */
public class MaterialDefinition {
    public String name;
    public String constituents;
    public boolean allotrope;
    public String description;
    public float meltingPoint;
    public float boilingPoint;
    public float radiation;
    public byte hardness;
    public String tenacity;
    public String crystalSystem;
    public Collection<String> strata;
    public boolean dropsXP;
    public boolean toxic;

    public MaterialDefinition() {
    }

    public Item.Properties buildItemProperties() {
        final Item.Properties properties = new Item.Properties();
        //  TODO: add more
        if (meltingPoint > Constants.LAVA_TEMPERATURE) properties.fireResistant();

        return properties;
    }

    //public BlockBehaviour.Properties buildBlockProperties(String stratum) {
        //  TODO: properly implement stratum check
        //final Material material = new Material.Builder().build();
        //final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(material).sound();


    //}
}
