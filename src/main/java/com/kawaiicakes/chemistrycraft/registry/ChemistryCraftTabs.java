package com.kawaiicakes.chemistrycraft.registry;

import com.kawaiicakes.chemistrycraft.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class ChemistryCraftTabs {
    public static final CreativeModeTab ORES =  new CreativeModeTab("chemistrycraft_ores") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return Blocks.DIAMOND_BLOCK.asItem().getDefaultInstance();
        }
    };
}
