package com.kawaiicakes.chemistrycraft.api.chemlib;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * ChemLib's implementation to grab generated registry objects relies on the RegisterEvent being
 * called for its registries. This poses a problem since I need to access them as ChemistryCraft's
 * registers are registered.
 * This is a static utility class that works in a manner reminiscent to <code>ForgeRegistries</code>.
 */
public class ChemlibRegistries {
    /**
     * Static method returning the ChemLib <code>RegistryObject</code> associated with
     * the given name.
     * @param name  The JSON-assigned name of the RegistryObject.
     * @return      The <code>RegistryObject</code>.
     * @param <T>   type extending <code>ItemLike</code>, intended to be used with ChemLib classes
     *           extending <code>ItemLike</code>.
     */
    /* I FUCKING LOVE COMMENTED OUT CODE FIXME
    public static <T extends ItemLike> RegistryObject<T> getByName(String name) {
        RegistryObject<T> registryObject = RegistryObject.create(new ResourceLocation("chemlib:" + name), ForgeRegistries.ITEMS)
    } */
}
