package io.github.kawaiicakes.chemistrycraft.init.registry;

import io.github.kawaiicakes.chemistrycraft.screen.BloomeryBlockMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> MENU_REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
    public static final RegistryObject<MenuType<BloomeryBlockMenu>> BLOOMERY_MENU =
            registerMenuType(BloomeryBlockMenu::new, "bloomery_menu");

    // helper method
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENU_REGISTRY.register(name, () -> IForgeMenuType.create(factory));
    }
}
