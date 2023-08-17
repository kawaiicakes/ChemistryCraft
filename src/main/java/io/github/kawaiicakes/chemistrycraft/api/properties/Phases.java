package io.github.kawaiicakes.chemistrycraft.api.properties;

import io.github.kawaiicakes.chemistrycraft.api.ChemicalProperty;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;

//  TODO: gas implementation... oh boy...

/**
 * Describes the phase behaviours of a substance. New instances may be instantiated via <code>PhaseDiagram</code>
 * or by passing primitives to the constructor when information is lacking on a substance.
 */
public class Phases implements ChemicalProperty {
    /**
     * Assume pressure at SATP.
     */
    private float boilingPoint;
    /**
     * Assume pressure at SATP.
     */
    private float meltingPoint;

    private float criticalPoint;
    private float triplePoint;


    @Override
    public Item.Properties generateItemProperties() {
        return null;
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
