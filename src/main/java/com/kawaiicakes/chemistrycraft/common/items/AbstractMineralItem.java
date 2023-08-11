package com.kawaiicakes.chemistrycraft.common.items;

import com.google.gson.JsonElement;
import com.smashingmods.chemlib.ChemLib;
import com.smashingmods.chemlib.api.Chemical;
import com.smashingmods.chemlib.api.MatterState;
import com.smashingmods.chemlib.registry.ChemicalRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractMineralItem extends Item implements Chemical, Mineral {
    private final String name;
    private final String parent;
    private final String description;
    private final float radiation;
    private final boolean dye;

    public AbstractMineralItem(Properties properties, String name, String parent, String description, float radiation, boolean dye) {
        super(properties);
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.radiation = radiation;
        this.dye = dye;
    }

    @Override
    public final void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        components.add(MutableComponent.create(new LiteralContents(this.getAbbreviation())).withStyle(ChatFormatting.DARK_AQUA));
        components.add(MutableComponent.create(new LiteralContents(StringUtils.capitalize(this.getNamespace()))).withStyle(ChemLib.MOD_ID_TEXT_STYLE));
    }

    @Override
    public final @NotNull String getChemicalName() {
        return this.name;
    }

    @Override
    public abstract @NotNull String getAbbreviation();

    @Override
    public @NotNull String getChemicalDescription() {
        return this.description;
    }

    @Override
    public String getParent() {
        return this.parent;
    }

    @Override
    public float getRadiation() {
        return this.radiation;
    }

    public final String getNamespace() {
        //noinspection OptionalGetWithoutIsPresent
        return ForgeRegistries.ITEMS.getResourceKey(this).get().location().getNamespace();
    }
}
