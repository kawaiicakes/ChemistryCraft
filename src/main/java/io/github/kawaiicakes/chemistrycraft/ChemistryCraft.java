package io.github.kawaiicakes.chemistrycraft;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// TODO: tooltips can be modified through events (RenderTooltipEvent.GatherComponents)
// TODO: research materials science https://www.quora.com/How-do-you-increase-the-tensile-strength-of-a-sword-and-or-blade , https://www.google.com/search?q=what+kind+of+stress+is+applied+to+a+sword+blade&sca_esv=557314902&rlz=1C1VDKB_enCA1069CA1069&sxsrf=AB5stBjIWTS4cZCoqDP-Y_Kr20CrMhqn4w%3A1692152470603&ei=ljLcZKK4JMuO0PEP9tu1oAg&ved=0ahUKEwii26mXj-CAAxVLBzQIHfZtDYQQ4dUDCBA&uact=5&oq=what+kind+of+stress+is+applied+to+a+sword+blade&gs_lp=Egxnd3Mtd2l6LXNlcnAiL3doYXQga2luZCBvZiBzdHJlc3MgaXMgYXBwbGllZCB0byBhIHN3b3JkIGJsYWRlMgcQIRigARgKSLw0UABYgzRwAngBkAEBmAGLAaAB4ySqAQUzNy4xM7gBA8gBAPgBAcICBxAjGIoFGCfCAgQQIxgnwgIHEAAYigUYQ8ICDRAAGIoFGLEDGIMBGEPCAggQABiABBixA8ICCBAAGIoFGLEDwgIFEAAYgATCAgYQABgWGB7CAggQABgWGB4YD8ICBxAAGA0YgATCAgoQABiABBgUGIcCwgIIEAAYFhgeGArCAggQABiKBRiGA8ICBRAhGKAB4gMEGAAgQYgGAQ&sclient=gws-wiz-serp

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
    }
}
