package com.kawaiicakes.chemistrycraft.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Map;

public class Json {

    //i think i should generalize this method more. it's written exclusively for ChemLibMixinRegistry.
    public static void injectNewColorToIndexFromJsonMap(@NotNull Map<String, JsonArray> src, int index, String color) {
        final JsonArray jsonArray = src.get("elements");
        final JsonObject srcObj = (JsonObject) jsonArray.get(index);

        srcObj.add("color", new JsonPrimitive(color));
    }

    public static Map<String, JsonArray> getMapFromJsonElement(JsonElement src) {
        final Type typeOfT = new TypeToken<Map<String, JsonArray>>(){}.getType();
        return new Gson().fromJson(src, typeOfT);
    }
}
