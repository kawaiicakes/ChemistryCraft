package io.github.kawaiicakes.chemistrycraft.registry.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smashingmods.chemlib.api.MatterState;
import com.smashingmods.chemlib.common.items.CompoundItem;
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

import java.util.*;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID_TEXT_STYLE;
import static io.github.kawaiicakes.chemistrycraft.registry.ChemistryCraftRegistry.getJsonObjectByCompoundName;

public class NonAllotropeMineralItem extends CompoundItem implements Mineral {
    private final String nonAllotropeName;
    private final String phase;
    private final String description;
    private final String parentChemical;

    public NonAllotropeMineralItem(String nonAllotropeName, @Nullable String phase, String description, String parentChemical) {
        super(
                parentChemical,
                MatterState.SOLID,
                components(parentChemical),
                description(parentChemical),
                color(parentChemical),
                mobEffectsFactory(getJsonObjectByCompoundName(parentChemical))
        );
        this.nonAllotropeName = nonAllotropeName;
        this.phase = phase;
        this.description = description;
        this.parentChemical = parentChemical;
    }

    public String getNamespace() {
        return ForgeRegistries.ITEMS.getResourceKey(this).orElseThrow().location().getNamespace();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(MutableComponent.create(new LiteralContents(this.getAbbreviation())).withStyle(ChatFormatting.DARK_AQUA));
        pTooltipComponents.add(MutableComponent.create(new LiteralContents(StringUtils.capitalize(this.getNamespace()))).withStyle(MOD_ID_TEXT_STYLE));
    }

    @Override
    public String getChemicalName() {
        return this.nonAllotropeName;
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
    public @Nullable String getPhase() {
        return this.phase;
    }

    @Override
    public String getParentChemical() {
        return this.parentChemical;
    }

    public static String description(String parentChemical) {
        return getJsonObjectByCompoundName(parentChemical).get("description").getAsString();
    }

    public static String abbreviation(String parentChemical) {
        return getJsonObjectByCompoundName(parentChemical).get("abbreviation").getAsString();
    }

    public static String color(String parentChemical) {
        return getJsonObjectByCompoundName(parentChemical).get("color").getAsString();
    }

    public static Map<String, Integer> components(String parentChemical) {
        JsonArray components = getJsonObjectByCompoundName(parentChemical).getAsJsonArray("components");

        HashMap<String, Integer> componentMap = new LinkedHashMap<>();
        for (JsonElement component : components) {
            JsonObject componentObject = component.getAsJsonObject();
            String componentName = componentObject.get("name").getAsString();
            int count = componentObject.has("count") ? componentObject.get("count").getAsInt() : 1;
            componentMap.put(componentName, count);
        }
        return componentMap;
    }

    public static List<MobEffectInstance> mobEffectsFactory(JsonObject compoundJson) {
        List<MobEffectInstance> effectsList = new ArrayList<>();
        JsonArray effects = compoundJson.getAsJsonArray("effect");
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
