package com.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class ItemRegistry {
    ItemRegistry(){}

    public static final DeferredRegister<Item> GENERIC_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Item> RAW_MINERAL_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static void register(IEventBus bus)
    {
        GENERIC_REGISTRY.register(bus);
        RAW_MINERAL_REGISTRY.register(bus);
        BLOCK_ITEM_REGISTRY.register(bus);
    }
}
