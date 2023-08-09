package com.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class ItemRegistry {

    static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> YUMMY_LEAD = REGISTRY.register("yummy_lead", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).build()).tab(ChemistryCraftTabs.ORES)));
    public static void register(IEventBus bus)
    {
        REGISTRY.register(bus);
    }
}

/*
forge scans the entire classpath for annotations in order to detect your classes and methods
though the event annotation is deprecated, should use IBus#addListener or #addGenericListener
the annotation one causes too many problems
 */
