package com.kawaiicakes.chemistrycraft;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import static com.kawaiicakes.chemistrycraft.registries.BlockRegistry.BLOCKS;
import static com.kawaiicakes.chemistrycraft.registries.ItemRegistry.ITEMS;

@Mod(ChemistryCraft.MOD_ID)
public class ChemistryCraft
{
    public static final String MOD_ID = "chemistrycraft";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Style MOD_ID_TEXT_STYLE = Style.EMPTY.withFont(Style.DEFAULT_FONT).withItalic(true).withColor(ChatFormatting.BLUE);

    public ChemistryCraft()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //code here lawl
        }

    }

}
