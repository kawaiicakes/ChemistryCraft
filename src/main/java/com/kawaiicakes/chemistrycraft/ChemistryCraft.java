package com.kawaiicakes.chemistrycraft;

import com.kawaiicakes.chemistrycraft.registry.BlockRegistry;
import com.kawaiicakes.chemistrycraft.registry.ItemRegistry;
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

@Mod(ChemistryCraft.MOD_ID)
public class ChemistryCraft
{
    public static final String MOD_ID = "chemistrycraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ChemistryCraft()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.register(modEventBus);
        ItemRegistry.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //code here lawl
        }

    }

}
