package io.github.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static com.smashingmods.chemlib.registry.Registry.getStreamAsJsonObject;

public class ChemistryCraftRegistry {
    public static final JsonObject MINERALS = getStreamAsJsonObject("/data/chemistrycraft/minerals.json");

    public static void registerMinerals() {
        for (JsonElement jsonElement: MINERALS.getAsJsonArray("allotropes")) {

        }

        for (JsonElement jsonElement : MINERALS.getAsJsonArray("non_allotropes")) {

        }
    }
}
