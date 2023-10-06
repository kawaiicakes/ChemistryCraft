package io.github.kawaiicakes.chemistrycraft;

import com.mojang.logging.LogUtils;
import io.github.kawaiicakes.chemistrycraft.network.ChemistryPackets;
import io.github.kawaiicakes.chemistrycraft.registry.*;
import io.github.kawaiicakes.chemistrycraft.screen.BloomeryBlockScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Style;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static io.github.kawaiicakes.chemistrycraft.registry.MenuRegistry.BLOOMERY_MENU;

// TODO: tooltips can be modified through events (RenderTooltipEvent.GatherComponents)
// TODO: research materials science https://www.quora.com/How-do-you-increase-the-tensile-strength-of-a-sword-and-or-blade , https://www.google.com/search?q=what+kind+of+stress+is+applied+to+a+sword+blade&sca_esv=557314902&rlz=1C1VDKB_enCA1069CA1069&sxsrf=AB5stBjIWTS4cZCoqDP-Y_Kr20CrMhqn4w%3A1692152470603&ei=ljLcZKK4JMuO0PEP9tu1oAg&ved=0ahUKEwii26mXj-CAAxVLBzQIHfZtDYQQ4dUDCBA&uact=5&oq=what+kind+of+stress+is+applied+to+a+sword+blade&gs_lp=Egxnd3Mtd2l6LXNlcnAiL3doYXQga2luZCBvZiBzdHJlc3MgaXMgYXBwbGllZCB0byBhIHN3b3JkIGJsYWRlMgcQIRigARgKSLw0UABYgzRwAngBkAEBmAGLAaAB4ySqAQUzNy4xM7gBA8gBAPgBAcICBxAjGIoFGCfCAgQQIxgnwgIHEAAYigUYQ8ICDRAAGIoFGLEDGIMBGEPCAggQABiABBixA8ICCBAAGIoFGLEDwgIFEAAYgATCAgYQABgWGB7CAggQABgWGB4YD8ICBxAAGA0YgATCAgoQABiABBgUGIcCwgIIEAAYFhgeGArCAggQABiKBRiGA8ICBRAhGKAB4gMEGAAgQYgGAQ&sclient=gws-wiz-serp
// TODO: look into PortalShape or the pathfinding method for making multiblock structures
// TODO: javadoc everything when implementation becomes more concrete
// TODO: Annotate interfaces and abstract classes

 /*
    Current design paradigm focuses around initial intense generation of properties once,
    then performant use of initialized fields rather than continually running intense calculations
    to simulate desired behaviours.

    For example, a custom item may have a certain final field of some Enthalpy, which is
    calculated and initialized once prior to object registration. This immutable property of the
    item is then accessed when necessary, using principles of encapsulation, by higher-levels of
    the API for calculation of a promised behaviour; then returned to lower-levels of the API for
    implementation of promised behaviour.

    As much as possible, ticking entities are to be avoided and especially intense calculations
    occurring on the server or client should be forced to occur over several ticks or even over
    several seconds. This can be deliberately "disguised" as gradual chemical/physical transformations
    of state.

    Calculation of promised behaviours will often occur in static utility methods. Bearing all
    this in mind, it may be generalized that more fundamental aspects of chemical/physical
    behaviour are likely to be represented as instances of higher-level API classes, and that
    values which may be trivially derived are lower and may be calculated using static methods.

    As to style:
    The api package has the highest-level user-facing classes with respect to interacting with
    this mod. Any subpackages represent a step lower. The same principle holds true regardless
    of depth of nested packages.
    Anything outside the api package is, in some way, interacting with the code of Minecraft/Forge.
    How it relates to Minecraft's code is described by the package name. The principle of nested
    packages being of lower implementation also holds true here. Utility classes should go into
    the utils package not inside the api package. Anything in that package should primarily
    provide utility for Minecraft/Forge code.

    Where possible, classes should keep as many of their fields private final as they can and
    utilize encapsulation and static utility methods to dynamically represent a broad range
    of properties that do not necessarily need to be stored in memory.
 */

@Mod(ChemistryCraft.MOD_ID)
public class ChemistryCraft
{
    public static final String MOD_ID = "chemistrycraft";
    public static final String MOD_DISPLAY_ID = "ChemistryCraft";
    public static final Style MOD_ID_TEXT_STYLE = Style.EMPTY.withFont(Style.DEFAULT_FONT).withItalic(true).withColor(ChatFormatting.LIGHT_PURPLE);
    public static final Logger LOGGER = LogUtils.getLogger();

    public ChemistryCraft()
    {
        MinecraftForge.EVENT_BUS.register(this);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockEntityRegistry.BLOCK_ENTITY_REGISTRY.register(modEventBus);
        BlockRegistry.BLOCK_REGISTRY.register(modEventBus);
        ItemRegistry.REGISTRY_MISC_ITEMS.register(modEventBus);
        MenuRegistry.MENU_REGISTRY.register(modEventBus);
        RecipeRegistry.RECIPE_REGISTRY.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ChemistryPackets.register();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(BLOOMERY_MENU.get(), BloomeryBlockScreen::new);
        }
    }
}
