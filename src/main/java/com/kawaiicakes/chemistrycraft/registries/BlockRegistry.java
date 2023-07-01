package com.kawaiicakes.chemistrycraft.registries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
}
