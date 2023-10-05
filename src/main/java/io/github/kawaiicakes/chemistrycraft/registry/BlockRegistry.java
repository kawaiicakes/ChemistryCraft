package io.github.kawaiicakes.chemistrycraft.registry;

import io.github.kawaiicakes.chemistrycraft.registry.block.BloomeryBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> BLOOMERY = // TODO: this is only a temporary implementation made while familiarizing myself with making block entities!
            BLOCK_REGISTRY.register("bloomery", () -> new BloomeryBlock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));

    public static <T extends Block> void register(String name, Supplier<T> block) {
        BLOCK_REGISTRY.register(name, block);
        ItemRegistry.ITEM_REGISTRY.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
