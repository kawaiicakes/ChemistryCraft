package io.github.kawaiicakes.chemistrycraft.registry;

import io.github.kawaiicakes.chemistrycraft.screen.PrimitiveFurnaceBlockMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> MENU_REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
    public static final RegistryObject<MenuType<PrimitiveFurnaceBlockMenu>> FURNACE_MENU =
            registerMenuType(PrimitiveFurnaceBlockMenu::new, "primitive_furnace_menu");

    // helper method
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENU_REGISTRY.register(name, () -> IForgeMenuType.create(factory));
    }
    public static void register(IEventBus bus) {
        MENU_REGISTRY.register(bus);
    }
}
