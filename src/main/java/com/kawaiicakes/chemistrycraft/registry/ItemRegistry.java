package com.kawaiicakes.chemistrycraft.registry;

import com.kawaiicakes.chemistrycraft.api.registry.MaterialDefinition;
import com.kawaiicakes.chemistrycraft.common.items.AbstractMineralItem;
import com.kawaiicakes.chemistrycraft.common.items.AllotropeMineralItem;
import com.kawaiicakes.chemistrycraft.common.items.NonAllotropeMineralItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.AbstractCollection;
import java.util.function.Supplier;

import static com.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class ItemRegistry {
    ItemRegistry(){}

    public static final DeferredRegister<Item> CHEMISTRYCRAFT_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static <T extends AbstractMineralItem> void registerMineral(MaterialDefinition materialDefinition) {

    }

    public static void register(IEventBus bus) {
        CHEMISTRYCRAFT_ITEMS.register(bus);
    }
}
