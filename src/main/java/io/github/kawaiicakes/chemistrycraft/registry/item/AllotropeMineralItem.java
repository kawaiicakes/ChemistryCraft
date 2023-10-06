package io.github.kawaiicakes.chemistrycraft.registry.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smashingmods.chemlib.api.MatterState;
import com.smashingmods.chemlib.api.MetalType;
import com.smashingmods.chemlib.client.AbbreviationRenderer;
import com.smashingmods.chemlib.common.items.ElementItem;
import com.smashingmods.chemlib.registry.ChemicalRegistry;
import io.github.kawaiicakes.chemistrycraft.api.Mineral;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class AllotropeMineralItem extends ElementItem implements Mineral {
    private final String name;
    private final String phase;
    private final String description;
    private final String parent;

    public AllotropeMineralItem(String allotropeName, @Nullable String phase, String description, String parent) {
        super(
                parent.toUpperCase(),
                atomicNumber(parent),
                abbreviation(parent),
                group(parent),
                period(parent),
                MatterState.SOLID,
                metalType(parent),
                artificial(parent),
                color(parent),
                mobEffectsFactory(getJsonObjectByElementName(parent))
        );
        this.name = allotropeName;
        this.phase = phase;
        this.description = description;
        this.parent = parent;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(MutableComponent.create(new LiteralContents(String.format("%s (%d)", this.getAbbreviation(), this.getAtomicNumber()))).withStyle(ChatFormatting.AQUA));
        if (!this.getGroupName().isEmpty()) {
            pTooltipComponents.add(MutableComponent.create(new LiteralContents(this.getGroupName())).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(AbbreviationRenderer.RENDERER);
    }

    @Override
    public String getChemicalName() {
        return this.name;
    }

    @Override
    public String getAbbreviation() {
        return this.phase != null ? this.phase + "-" + super.getAbbreviation() : super.getAbbreviation();
    }

    @Override
    public String getChemicalDescription() {
        return this.description;
    }

    @Override
    public List<MobEffectInstance> getEffects() {
        return super.getEffects();
    }

    @Override
    public int getColor() {
        return super.getColor();
    }

    @Override
    public @Nullable String getPhase() {
        return null;
    }

    @Override
    public String getParent() {
        return this.parent;
    }

    public static JsonObject getJsonObjectByElementName(String pName) {
        AtomicReference<JsonObject> toReturn = new AtomicReference<>();
        ChemicalRegistry.ELEMENTS_JSON.getAsJsonArray("elements").forEach(jsonElement -> {
            if (jsonElement.getAsJsonObject().get("name").getAsString().equals(pName)) {
                toReturn.set(jsonElement.getAsJsonObject());
            }
        });
        return toReturn.get();
    }

    public static int atomicNumber(String parent) {
        return getJsonObjectByElementName(parent).get("atomic_number").getAsInt();
    }

    public static String abbreviation(String parent) {
        return getJsonObjectByElementName(parent).get("abbreviation").getAsString();
    }

    public static int group(String parent) {
        return getJsonObjectByElementName(parent).get("group").getAsInt();
    }

    public static int period(String parent) {
        return getJsonObjectByElementName(parent).get("period").getAsInt();
    }

    public static MetalType metalType(String parent) {
        return MetalType.valueOf(getJsonObjectByElementName(parent).get("metal_type").getAsString().toUpperCase());
    }

    public static boolean artificial(String parent) {
        return getJsonObjectByElementName(parent).get("artificial").getAsBoolean();
    }

    public static String color(String parent) {
        return getJsonObjectByElementName(parent).get("color").getAsString();
    }

    public static List<MobEffectInstance> mobEffectsFactory(JsonObject object) {
        List<MobEffectInstance> effectsList = new ArrayList<>();
        JsonArray effects = object.getAsJsonArray("effect");
        if (effects != null) {

            for (JsonElement effect : effects) {
                JsonObject effectObject = effect.getAsJsonObject();
                String effectLocation = effectObject.get("location").getAsString();
                int effectDuration = effectObject.get("duration").getAsInt();
                int effectAmplifier = effectObject.get("amplifier").getAsInt();
                MobEffect mobEffect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(effectLocation));
                if (mobEffect != null) {
                    effectsList.add(new MobEffectInstance(mobEffect, effectDuration, effectAmplifier));
                }
            }
        }

        return effectsList;
    }
}
