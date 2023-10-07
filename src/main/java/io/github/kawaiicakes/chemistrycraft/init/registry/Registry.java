package io.github.kawaiicakes.chemistrycraft.init.registry;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.kawaiicakes.chemistrycraft.ChemistryCraft;
import net.minecraftforge.eventbus.api.IEventBus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Registry {
    public static void register(IEventBus bus) {
        ChemistryCraftRegistry.init();
        ItemRegistry.register(bus);
    }

    public static JsonObject getStreamAsJsonObject(String pPath) {
        return JsonParser.parseReader(new BufferedReader(new InputStreamReader(Objects.requireNonNull(ChemistryCraft.class.getResourceAsStream(pPath))))).getAsJsonObject();
    }
}
