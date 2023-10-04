package io.github.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.registry.BlockRegistry.BLOOMERY;
import static io.github.kawaiicakes.chemistrycraft.registry.ChemistryCraftRegistry.ITEM_REGISTRY;

public class ItemRegistry {
    public static final RegistryObject<Item> BLOOMERY_ITEM = ITEM_REGISTRY.register("bloomery", () -> new BlockItem(BLOOMERY.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
}
