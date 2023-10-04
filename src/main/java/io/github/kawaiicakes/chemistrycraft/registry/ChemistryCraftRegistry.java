package io.github.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class ChemistryCraftRegistry {
    static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTRY;
    static final DeferredRegister<Block> BLOCK_REGISTRY;
    static final DeferredRegister<Item> ITEM_REGISTRY;
    static final DeferredRegister<MenuType<?>> MENU_REGISTRY;
    static final DeferredRegister<RecipeSerializer<?>> RECIPE_REGISTRY;

    public static void register(IEventBus bus) {
        BLOCK_ENTITY_REGISTRY.register(bus);
        BLOCK_REGISTRY.register(bus);
        ITEM_REGISTRY.register(bus);
        MENU_REGISTRY.register(bus);
        RECIPE_REGISTRY.register(bus);
    }

    static {
        BLOCK_ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);
        BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
        ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
        MENU_REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
        RECIPE_REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);
    }
}
