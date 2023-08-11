package com.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smashingmods.chemlib.registry.ChemicalRegistry;

public class Registry {
    public static final JsonObject DEFINITIONS_JSON = com.smashingmods.chemlib.registry.Registry.getStreamAsJsonObject("data/chemistrycraft/definitions.json");

    private static void registerMinerals() {
        for (JsonElement jsonElement : DEFINITIONS_JSON.getAsJsonArray("minerals")) {
            final JsonObject jsonObj = jsonElement.getAsJsonObject();
            final String mineralName = jsonObj.get("name").getAsString();
        }
    }


}
