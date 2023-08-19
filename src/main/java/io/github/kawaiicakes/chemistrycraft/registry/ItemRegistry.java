package io.github.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final RegistryObject<Item> BALLSMUNGUS = ITEM_REGISTRY.register("ballsmungus", () -> new Item(new Item.Properties())); // TODO: come on. lol
    public static void register(IEventBus bus) {
        ITEM_REGISTRY.register(bus);
    }
}
