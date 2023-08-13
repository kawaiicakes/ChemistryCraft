package com.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kawaiicakes.chemistrycraft.ChemistryCraft;
import com.kawaiicakes.chemistrycraft.api.Constants;
import com.kawaiicakes.chemistrycraft.common.items.NonAllotropeMineralItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Registry {
    public Registry() {
    }
    public static void init(IEventBus bus) {
        MineralRegistry.generate();

        ItemRegistry.register(bus);
        BlockRegistry.register(bus);
    }
    public static JsonObject getStreamAsJsonObject(String path) {
        return JsonParser.parseReader(new BufferedReader(new InputStreamReader(Objects.requireNonNull(ChemistryCraft.class.getResourceAsStream(path))))).getAsJsonObject();
    }
}
