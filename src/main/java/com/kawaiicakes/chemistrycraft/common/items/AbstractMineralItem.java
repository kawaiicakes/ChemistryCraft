package com.kawaiicakes.chemistrycraft.common.items;

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

    public AbstractMineralItem(Properties properties, String constituents, String description, boolean burnsInLava, float radioactivity) {
        super(propertyBuilder(properties, burnsInLava));
        this.abbreviation = "";
        this.constituents = constituents;
        this.description = description;
        this.radioactivity = radioactivity;
    }

    //  I'm gonna be adding more onto this
    private static Properties propertyBuilder(Properties properties, boolean burnsInLava) {
        if (burnsInLava) properties.fireResistant();
        return properties.tab(ChemistryCraftTabs.MINERALS);
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
