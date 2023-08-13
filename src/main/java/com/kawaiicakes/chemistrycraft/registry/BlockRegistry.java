package com.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonArray;
import com.kawaiicakes.chemistrycraft.common.blocks.OreBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;
import static com.kawaiicakes.chemistrycraft.registry.ItemRegistry.BLOCK_ITEM_REGISTRY;
import static net.minecraftforge.registries.ForgeRegistries.BLOCKS;

public class BlockRegistry {
    public static final DeferredRegister<Block> ORES_REGISTRY = DeferredRegister.create(BLOCKS, MOD_ID);
    public static final DeferredRegister<Block> RAW_BLOCK_REGISTRY = DeferredRegister.create(BLOCKS, MOD_ID);

    public static final BlockBehaviour.Properties STONE_ORE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE); //    TODO: record class to hold this and the block type? then a static function to pass them into a supplier appropriately?
    public static final BlockBehaviour.Properties DEEPSLATE_ORE_PROPERTIES = BlockBehaviour.Properties.of(new Material.Builder(MaterialColor.DEEPSLATE).build()).sound(SoundType.DEEPSLATE);

    private static void generateOres(JsonArray strata) {
        strata.forEach(stratum -> {
            final String oreStrata = stratum.getAsString();
        });
    }

    static <T extends Block> void registerOre(String name, Supplier<T> block) {
        RegistryObject<Block> blockObj = ORES_REGISTRY.register(name, block); //    TODO: How bout a property builder class so I don't have to rewrite and keep track of a million things?
        BLOCK_ITEM_REGISTRY.register(name, () -> new BlockItem(blockObj.get(), new Item.Properties().tab(ChemistryCraftTabs.ORES)));
    }

    public static void register(IEventBus bus)
    {
        ORES_REGISTRY.register(bus);
    }
}

