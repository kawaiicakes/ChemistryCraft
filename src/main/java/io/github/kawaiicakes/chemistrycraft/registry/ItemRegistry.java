package io.github.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;
import static io.github.kawaiicakes.chemistrycraft.registry.BlockRegistry.BLOOMERY;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final RegistryObject<Item> BLOOMERY_ITEM = ITEM_REGISTRY.register("bloomery", () -> new BlockItem(BLOOMERY.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static void register(IEventBus bus) {
        ITEM_REGISTRY.register(bus);
    }
}
