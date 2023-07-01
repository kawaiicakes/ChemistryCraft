package com.kawaiicakes.chemistrycraft.registries;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;
import static com.kawaiicakes.chemistrycraft.registries.BlockRegistry.EXAMPLE_BLOCK;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block",
            () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().tab(ItemRegistry.ITEM_TAB)));
    public static final CreativeModeTab ITEM_TAB = new CreativeModeTab("chemistrycraft_items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.EXAMPLE_BLOCK_ITEM.get());
        }
    };

}

/*
forge scans the entire classpath for annotations in order to detect your classes and methods
though the event annotation is deprecated, should use IBus#addListener or #addGenericListener
the annotation one causes too many problems
 */
