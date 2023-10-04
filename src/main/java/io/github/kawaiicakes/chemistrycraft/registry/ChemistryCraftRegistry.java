package io.github.kawaiicakes.chemistrycraft.registry;

import net.minecraftforge.eventbus.api.IEventBus;

import static io.github.kawaiicakes.chemistrycraft.registry.BlockEntityRegistry.BLOCK_ENTITY_REGISTRY;
import static io.github.kawaiicakes.chemistrycraft.registry.BlockRegistry.BLOCK_REGISTRY;
import static io.github.kawaiicakes.chemistrycraft.registry.ItemRegistry.ITEM_REGISTRY;
import static io.github.kawaiicakes.chemistrycraft.registry.MenuRegistry.MENU_REGISTRY;
import static io.github.kawaiicakes.chemistrycraft.registry.RecipeRegistry.RECIPE_REGISTRY;

public class ChemistryCraftRegistry {
    public static void register(IEventBus bus) {
        BLOCK_ENTITY_REGISTRY.register(bus);
        BLOCK_REGISTRY.register(bus);
        ITEM_REGISTRY.register(bus);
        MENU_REGISTRY.register(bus);
        RECIPE_REGISTRY.register(bus);
    }
}
