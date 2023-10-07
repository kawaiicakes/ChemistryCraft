package io.github.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.kawaiicakes.chemistrycraft.ChemistryCraft;
import io.github.kawaiicakes.chemistrycraft.registry.item.AllotropeMineralItem;

import java.util.concurrent.atomic.AtomicReference;

import static com.smashingmods.chemlib.registry.ChemicalRegistry.COMPOUNDS_JSON;
import static com.smashingmods.chemlib.registry.ChemicalRegistry.ELEMENTS_JSON;

public class ChemistryCraftRegistry {
    public static final JsonObject MINERALS = Registry.getStreamAsJsonObject("/data/chemistrycraft/minerals.json");

    public static void registerMinerals() {
        for (JsonElement jsonElement: MINERALS.getAsJsonArray("allotropes")) {
            JsonObject obj = jsonElement.getAsJsonObject();
            String name = obj.get("name").getAsString();
            String phase = obj.get("phase").getAsString().isEmpty() ? null : obj.get("phase").getAsString();
            String description = obj.get("description").getAsString();
            String parent = obj.get("parent").getAsString();

            ItemRegistry.registerOre(name, () -> new AllotropeMineralItem(name, phase, description, parent));
        }

        for (JsonElement jsonElement : MINERALS.getAsJsonArray("non_allotropes")) {

        }
    }

    public static void init() {
        ChemistryCraft.LOGGER.info("Please load :(");
        registerMinerals();
    }

    public static JsonObject getJsonObjectByElementName(String pName) {
        AtomicReference<JsonObject> toReturn = new AtomicReference<>();
        ELEMENTS_JSON.getAsJsonArray("elements").forEach(jsonElement -> {
            if (jsonElement.getAsJsonObject().get("name").getAsString().equals(pName)) {
                toReturn.set(jsonElement.getAsJsonObject());
            }
        });
        return toReturn.get();
    }

    public static JsonObject getJsonObjectByCompoundName(String pName) {
        AtomicReference<JsonObject> toReturn = new AtomicReference<>();
        COMPOUNDS_JSON.getAsJsonArray("elements").forEach(jsonElement -> {
            if (jsonElement.getAsJsonObject().get("name").getAsString().equals(pName)) {
                toReturn.set(jsonElement.getAsJsonObject());
            }
        });
        return toReturn.get();
    }
}
