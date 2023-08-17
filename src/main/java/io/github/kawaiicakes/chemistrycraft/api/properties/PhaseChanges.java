package io.github.kawaiicakes.chemistrycraft.api.properties;

import io.github.kawaiicakes.chemistrycraft.api.ChemicalProperty;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;

//  TODO: gas implementation... oh boy...
public class PhaseChanges implements ChemicalProperty {
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
