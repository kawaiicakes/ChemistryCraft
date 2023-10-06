package io.github.kawaiicakes.chemistrycraft.registry;

import com.smashingmods.chemlib.common.items.ChemicalBlockItem;
import io.github.kawaiicakes.chemistrycraft.api.Mineral;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;
import static io.github.kawaiicakes.chemistrycraft.registry.BlockRegistry.BLOOMERY;

public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTRY_MISC_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Item> REGISTRY_RAW_ORE = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Item> REGISTRY_ORE_BLOCK_ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> BLOOMERY_ITEM = REGISTRY_MISC_ITEMS.register("bloomery", () -> new BlockItem(BLOOMERY.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static <T extends Item & Mineral> void registerOre(T mineral) {
        REGISTRY_RAW_ORE.register(mineral.getChemicalName(), () -> mineral);
    }

    public static <T extends Item & Mineral> void registerOreBlockItem(T mineral, Item.Properties properties) {
        // REGISTRY_ORE_BLOCK_ITEM.register(mineral.getChemicalName() + "_ore", new ChemicalBlockItem(mineral, properties));
    }

    public static void register(IEventBus bus) {
        REGISTRY_MISC_ITEMS.register(bus);
        REGISTRY_RAW_ORE.register(bus);
    }
}
