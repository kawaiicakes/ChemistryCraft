package io.github.kawaiicakes.chemistrycraft.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static <T extends Block> void registerBlock(String name, Supplier<T> block) {
        BLOCK_REGISTRY.register(name, block);
        ItemRegistry.ITEM_REGISTRY.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    static {
        Supplier<? extends Block> sup = () -> new Block(BlockBehaviour.Properties.of(Material.STONE));
        BLOCK_REGISTRY.register("ballspoop", sup);
    }
}
