package io.github.kawaiicakes.chemistrycraft.registry;

import io.github.kawaiicakes.chemistrycraft.block.BloomeryBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.kawaiicakes.chemistrycraft.registry.ChemistryCraftRegistry.BLOCK_REGISTRY;
import static io.github.kawaiicakes.chemistrycraft.registry.ChemistryCraftRegistry.ITEM_REGISTRY;

public class BlockRegistry {
    public static final RegistryObject<Block> BLOOMERY = // TODO: this is only a temporary implementation made while familiarizing myself with making block entities!
            BLOCK_REGISTRY.register("bloomery", () -> new BloomeryBlock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));

    public static <T extends Block> void register(String name, Supplier<T> block) {
        BLOCK_REGISTRY.register(name, block);
        ITEM_REGISTRY.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
