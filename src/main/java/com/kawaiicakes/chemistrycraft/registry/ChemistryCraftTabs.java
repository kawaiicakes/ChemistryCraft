package com.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class ChemistryCraftTabs {
    public static final CreativeModeTab ORES = new CreativeModeTab("chemistrycraft_ores") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return Blocks.DIAMOND_BLOCK.asItem().getDefaultInstance();
        }
    };
    public static final CreativeModeTab BUILDING_BLOCKS = new CreativeModeTab("chemistrycraft_blocks") {
        @Override
        public @NotNull ItemStack makeIcon() {return Items.AMETHYST_BLOCK.getDefaultInstance();}
    };
    public static final CreativeModeTab MINERALS = new CreativeModeTab("chemistrycraft_minerals") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return Items.DIAMOND.getDefaultInstance();
        }
    };
}
