package io.github.kawaiicakes.chemistrycraft.registry;

import io.github.kawaiicakes.chemistrycraft.screen.BloomeryBlockMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.registry.ChemistryCraftRegistry.MENU_REGISTRY;

public class MenuRegistry {
    public static final RegistryObject<MenuType<BloomeryBlockMenu>> BLOOMERY_MENU =
            registerMenuType(BloomeryBlockMenu::new, "bloomery_menu");

    // helper method
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENU_REGISTRY.register(name, () -> IForgeMenuType.create(factory));
    }
}
