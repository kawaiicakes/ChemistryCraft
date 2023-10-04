package io.github.kawaiicakes.chemistrycraft.api;

import com.smashingmods.chemlib.api.addons.registry.AddonRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;

public class ChemistryCraftAddonRegistry extends AddonRegistry {
    public DeferredRegister<Item> RAW_ORE_ITEMS;
    public DeferredRegister<Item> RAW_ORE_BLOCKS;
    public DeferredRegister<Item> ORE_BLOCKS;
    public DeferredRegister<Item> STORAGE_BLOCKS;

    public ChemistryCraftAddonRegistry(String pModID) throws RuntimeException {
        super(pModID);
    }
}
