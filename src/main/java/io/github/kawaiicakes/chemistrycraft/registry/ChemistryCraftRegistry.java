package io.github.kawaiicakes.chemistrycraft.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smashingmods.chemlib.common.items.ElementItem;
import io.github.kawaiicakes.chemistrycraft.registry.item.AllotropeMineralItem;
import net.minecraft.world.item.Item;

import java.util.Optional;

import static com.smashingmods.chemlib.registry.ItemRegistry.getElementByName;
import static com.smashingmods.chemlib.registry.Registry.getStreamAsJsonObject;

public class ChemistryCraftRegistry {
    public static final JsonObject MINERALS = getStreamAsJsonObject("/data/chemistrycraft/minerals.json");

    public static void registerMinerals() {
        for (JsonElement jsonElement: MINERALS.getAsJsonArray("allotropes")) {
            JsonObject obj = jsonElement.getAsJsonObject();
            String name = obj.get("name").getAsString();
            String phase = obj.get("phase").getAsString().isEmpty() ? null : obj.get("phase").getAsString();
            String description = obj.get("description").getAsString();
            String parent = obj.get("parent").getAsString();

            ItemRegistry.registerOre(new AllotropeMineralItem(name, phase, description, parent));
        }

        for (JsonElement jsonElement : MINERALS.getAsJsonArray("non_allotropes")) {

        }
    }
}
