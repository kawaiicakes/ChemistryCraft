package com.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kawaiicakes.chemistrycraft.ChemistryCraft;
import com.kawaiicakes.chemistrycraft.api.Constants;
import com.kawaiicakes.chemistrycraft.common.items.NonAllotropeMineralItem;
import net.minecraft.world.item.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class MineralRegistry {

    public static final JsonObject DEFINITIONS_JSON = Registry.getStreamAsJsonObject("/data/chemistrycraft/definitions.json");
    MineralRegistry() {
    }
    static void generate() {
        DEFINITIONS_JSON.getAsJsonArray("minerals").forEach(mineral -> {
            final JsonObject minObj = mineral.getAsJsonObject();
            final JsonObject crystalProperties = minObj.get("crystal_properties").getAsJsonObject();

            final String name = minObj.get("name").getAsString(); //    TODO: replace String local variables with magic numbers (defined in a magic number class)?
            final String constituents = minObj.get("constituents").getAsString();
            final boolean allotrope = minObj.get("allotrope").getAsBoolean();
            final String description = minObj.get("description").getAsString();
            final float meltingPoint = minObj.get("melting_point").getAsFloat();
            final float radiation = minObj.get("radioactivity").getAsFloat();
            final byte hardness = crystalProperties.get("hardness").getAsByte();
            final String tenacity = crystalProperties.get("tenacity").getAsString();
            final String crystalSystem = crystalProperties.get("crystal_system").getAsString();
            final JsonArray strata = minObj.get("present_in_strata").getAsJsonArray();

            final boolean burnsInLava = meltingPoint <= Constants.LAVA_TEMPERATURE;

            if (!allotrope) {
                ItemRegistry.RAW_MINERAL_REGISTRY.register(name, () -> new NonAllotropeMineralItem(new Item.Properties(), constituents, description, burnsInLava, radiation));
            }
        });
    }
}
