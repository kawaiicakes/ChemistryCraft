package io.github.kawaiicakes.chemistrycraft.api.properties;

import io.github.kawaiicakes.chemistrycraft.api.Constants;
import io.github.kawaiicakes.chemistrycraft.api.utils.PhasePair;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;

//  TODO: gas implementation... oh boy...

/**
 * Describes the thermodynamic behaviours of a substance. New instances may be instantiated with a <code>PhaseDiagram</code>
 * or by passing primitives to the constructor when information is lacking on a substance.
 */
public class ThermodynamicProperties implements ChemicalProperty {
    /**
     * Assume pressure at SATP.
     */
    private float boilingPoint;
    /**
     * Assume pressure at SATP.
     */
    private float meltingPoint;

    private PhasePair criticalPoint;
    private PhasePair triplePoint;

    private float specificHeatCapacity;
    private float thermalConductivity; //   TODO: determine need for unit types

    @Override
    public Item.Properties generateItemProperties() {
        Item.Properties properties = new Item.Properties();
        if (this.meltingPoint > Constants.LAVA_TEMPERATURE) properties.fireResistant();
        return properties;
    }

    @Override
    public BlockBehaviour.Properties generateBlockProperties() {
        return null;
    }

    @Override
    public FluidType.Properties generateFluidProperties() {
        return null;
    }
}
