package io.github.kawaiicakes.chemistrycraft.init.registry;

import com.smashingmods.chemlib.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ChemistryCraftTabs {
    public static final CreativeModeTab ORES = new CreativeModeTab("chemistrycraft_ores") {
        @Override
        public ItemStack makeIcon() {
            return ItemRegistry.getRegistryObject(ItemRegistry.REGISTRY_MISC_ITEMS, "periodic_table").get()
                    .getDefaultInstance();
        }
    };
    public static final CreativeModeTab MINERALS = new CreativeModeTab("chemistrycraft_minerals") {
        @Override
        public ItemStack makeIcon() {
            return ItemRegistry.getRegistryObject(ItemRegistry.REGISTRY_MISC_ITEMS, "periodic_table").get()
                    .getDefaultInstance();
        }
    };
    public static final CreativeModeTab BLOCKS = new CreativeModeTab("chemistrycraft_blocks") {
        @Override
        public ItemStack makeIcon() {
            return ItemRegistry.getRegistryObject(ItemRegistry.REGISTRY_MISC_ITEMS, "periodic_table").get()
                    .getDefaultInstance();
        }
    };
}
