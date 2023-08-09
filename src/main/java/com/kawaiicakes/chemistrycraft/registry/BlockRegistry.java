package com.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class BlockRegistry {
    static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    private static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> block, CreativeModeTab tab)
    {
        final RegistryObject<T> returnBlock = REGISTRY.register(id, block);
        registerBlockItem(id, returnBlock, tab);
        return returnBlock;
    }
    //  Also add spodumene and molybdenite, then come up with more ores
    public static final RegistryObject<Block> HEMATITE = registerBlock(
            "hematite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()),
            ChemistryCraftTabs.ORES);

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String id, RegistryObject<T> block, CreativeModeTab tab)
    {
        return ItemRegistry.REGISTRY.register(id, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus bus)
    {
        REGISTRY.register(bus);
    }
}

