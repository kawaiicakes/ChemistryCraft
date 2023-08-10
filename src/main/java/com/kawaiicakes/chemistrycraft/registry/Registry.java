package com.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kawaiicakes.chemistrycraft.ChemistryCraft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Registry {
    public static final JsonObject DEFINITIONS = getStreamAsJsonObject("data/chemistrycraft/definitions.json");

    private static void registerMinerals() {
        for (JsonElement jsonElement : DEFINITIONS.getAsJsonArray("minerals")) {
            final JsonObject jsonObj = jsonElement.getAsJsonObject();
            final String mineralName = jsonObj.get("name").getAsString();
            final
        }
    }

    public static JsonObject getStreamAsJsonObject(String pPath) {
        return JsonParser.parseReader(new
                BufferedReader(new InputStreamReader(Objects.requireNonNull(ChemistryCraft.class.getResourceAsStream(pPath))))).getAsJsonObject();
    }
}
