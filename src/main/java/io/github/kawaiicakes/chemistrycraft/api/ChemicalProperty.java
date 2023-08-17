package io.github.kawaiicakes.chemistrycraft.api;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

/**
 * Implementing classes describe some property of a real-life substance and are responsible
 * for translating those properties into logic usable by the game.
 */
@Nullable
public interface ChemicalProperty {
    Item.Properties generateItemProperties();

    BlockBehaviour.Properties generateBlockProperties();

    FluidType.Properties generateFluidProperties();
}
