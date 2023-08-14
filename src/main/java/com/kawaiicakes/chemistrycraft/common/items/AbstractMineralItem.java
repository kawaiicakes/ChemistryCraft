package com.kawaiicakes.chemistrycraft.common.items;

import com.kawaiicakes.chemistrycraft.api.registry.MaterialDefinition;
import com.kawaiicakes.chemistrycraft.registry.ChemistryCraftTabs;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractMineralItem extends Item {
    private String abbreviation;
    private final String constituents;
    private final String description;
    private final float radioactivity;

    public AbstractMineralItem(MaterialDefinition materialDefinition) {
        super(materialDefinition.buildItemProperties());
        this.abbreviation = "";
        this.constituents = materialDefinition.constituents;
        this.description = materialDefinition.description;
        this.radioactivity = materialDefinition.radiation;
    }

    @Override
    public abstract void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag);
    public void setAbbreviation(String newAbbreviation) {this.abbreviation = newAbbreviation;}
    public String getAbbreviation() {return this.abbreviation;}
    public String getConstituents() {
        return this.constituents;
    }
    public String getJEIDescription(){return this.description;}
    public float getRadioactivity(){return this.radioactivity;}
}
