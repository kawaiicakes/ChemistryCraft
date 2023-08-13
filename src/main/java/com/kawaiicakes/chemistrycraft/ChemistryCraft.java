package com.kawaiicakes.chemistrycraft;

import com.kawaiicakes.chemistrycraft.registry.BlockRegistry;
import com.kawaiicakes.chemistrycraft.registry.ItemRegistry;
import com.kawaiicakes.chemistrycraft.registry.Registry;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("chemistrycraft")
public class ChemistryCraft
{
    public static final String MOD_ID = "chemistrycraft";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Style MOD_ID_TEXT_STYLE;

    public ChemistryCraft()
    {
        MinecraftForge.EVENT_BUS.register(this);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Registry.init(modEventBus);
    }

    static {
        MOD_ID_TEXT_STYLE = Style.EMPTY.withFont(Style.DEFAULT_FONT).withItalic(true).withColor(ChatFormatting.BLUE);
        LOGGER.info("Successfully initialized " + MOD_ID);
    }
}
