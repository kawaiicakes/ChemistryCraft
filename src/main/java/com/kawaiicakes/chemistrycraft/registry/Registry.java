package com.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kawaiicakes.chemistrycraft.ChemistryCraft;
import net.minecraftforge.eventbus.api.IEventBus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Registry {
    public Registry() {} //  Loads class early. That's it

    public static void init(IEventBus bus) {
        DefinitionRegistry.generate();

        ItemRegistry.register(bus);
        BlockRegistry.register(bus);
    }

    //  For some reason it's necessary to write it like this
    static JsonObject getStreamAsJsonObject(@SuppressWarnings("SameParameterValue") String path) {
        return JsonParser.parseReader(new BufferedReader(new InputStreamReader(Objects.requireNonNull(ChemistryCraft.class.getResourceAsStream(path))))).getAsJsonObject();
    }
}
