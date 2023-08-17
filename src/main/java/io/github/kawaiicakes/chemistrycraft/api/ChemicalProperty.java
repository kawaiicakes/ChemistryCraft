package io.github.kawaiicakes.chemistrycraft.api;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

@Nullable
public interface ChemicalProperty {
    Item.Properties generateItemProperties();

    BlockBehaviour.Properties generateBlockProperties();

    FluidType.Properties generateFluidProperties();
}
