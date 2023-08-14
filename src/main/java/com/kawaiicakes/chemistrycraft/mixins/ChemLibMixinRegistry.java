package com.kawaiicakes.chemistrycraft.mixins;

import com.google.gson.*;

import com.kawaiicakes.chemistrycraft.api.chemlib.Json;
import com.smashingmods.chemlib.ChemLib;
import com.smashingmods.chemlib.registry.Registry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

@Mixin(Registry.class)
public class ChemLibMixinRegistry {

    @Inject(method = "getStreamAsJsonObject", at = @At("RETURN"), remap = false, cancellable = true)
    private static void getStreamAsJsonObject(String pPath, CallbackInfoReturnable<JsonObject> callback) {

        final JsonElement jsonElements = JsonParser.parseReader(new BufferedReader(new InputStreamReader(Objects.requireNonNull(ChemLib.class.getResourceAsStream(pPath)))));
        if (pPath.equals("/data/chemlib/elements.json")) {

            Map<String, JsonArray> jsonMap = Json.getMapFromJsonElement(jsonElements);

            Json.injectNewColorToIndexFromJsonMap(jsonMap, 78, "ffd700");

            callback.setReturnValue(new Gson().toJsonTree(jsonMap).getAsJsonObject());

        } else if (pPath.equals("/data/chemlib/compounds.json")) {

            callback.setReturnValue(jsonElements.getAsJsonObject());

        }
    }
}