package io.github.kawaiicakes.chemistrycraft.registry.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smashingmods.chemlib.api.MatterState;
import com.smashingmods.chemlib.api.MetalType;
import com.smashingmods.chemlib.common.items.ElementItem;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID_TEXT_STYLE;
import static io.github.kawaiicakes.chemistrycraft.registry.ChemistryCraftRegistry.getJsonObjectByElementName;

public class AllotropeMineralItem extends ElementItem implements Mineral {
    private final String allotropeName;
    private final String phase;
    private final String description;
    private final String parentChemical;

    public AllotropeMineralItem(String allotropeName, @Nullable String phase, String description, String parentChemical) {
        super(
                parentChemical,
                atomicNumber(parentChemical),
                abbreviation(parentChemical),
                group(parentChemical),
                period(parentChemical),
                MatterState.SOLID,
                metalType(parentChemical),
                artificial(parentChemical),
                color(parentChemical),
                mobEffectsFactory(getJsonObjectByElementName(parentChemical))
        );
        this.allotropeName = allotropeName;
        this.phase = phase;
        this.description = description;
        this.parentChemical = parentChemical;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(MutableComponent.create(new LiteralContents(String.format("%s (%d)", this.getAbbreviation(), this.getAtomicNumber()))).withStyle(ChatFormatting.AQUA));
        if (!this.getGroupName().isEmpty()) {
            pTooltipComponents.add(MutableComponent.create(new LiteralContents(this.getGroupName())).withStyle(ChatFormatting.GRAY));
        }
        pTooltipComponents.add(MutableComponent.create(new LiteralContents(StringUtils.capitalize(this.getNamespace()))).withStyle(MOD_ID_TEXT_STYLE));
    }

    @Override
    public String getChemicalName() {
        return this.allotropeName;
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
    public @Nullable String getPhase() {
        return this.phase;
    }

    @Override
    public String getParentChemical() {
        return this.parentChemical;
    }

    public String getNamespace() {
        return ForgeRegistries.ITEMS.getResourceKey(this).orElseThrow().location().getNamespace();
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
        return getJsonObjectByElementName(parent).get("artificial") != null && getJsonObjectByElementName(parent).get("artificial").getAsBoolean();
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
