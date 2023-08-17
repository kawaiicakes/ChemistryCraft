package io.github.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    static {
        ITEM_REGISTRY.register("ballsmungus", () -> new Item(new Item.Properties()));
    }
}
