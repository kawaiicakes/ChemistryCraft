package com.kawaiicakes.chemistrycraft.common.items;

import com.kawaiicakes.chemistrycraft.ChemistryCraft;
import com.smashingmods.chemlib.ChemLib;
import com.smashingmods.chemlib.registry.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NonAllotropeMineralItem extends AbstractMineralItem {
    public NonAllotropeMineralItem(Properties properties, String constituents, String description, boolean burnsInLava, float radioactivity) {
        super(properties, constituents, description, burnsInLava, radioactivity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) { //    TODO: redo temp. impl. of getting abbreviation. This relies on ChemLib having the compound. What if it doesn't?
        setAbbreviation(ItemRegistry.getCompoundByName(this.getConstituents()).orElseThrow().getAbbreviation());
        components.add(MutableComponent.create(new LiteralContents(ItemRegistry.getCompoundByName(this.getConstituents()).orElseThrow().getAbbreviation())).withStyle(ChatFormatting.DARK_AQUA));
        components.add(MutableComponent.create(new LiteralContents("ChemistryCraft")).withStyle(ChemistryCraft.MOD_ID_TEXT_STYLE)); //   TODO: ugh.
    }

    public String getNamespace() {
        return ForgeRegistries.ITEMS.getResourceKey(this).orElseThrow().location().getNamespace();
    }
}
