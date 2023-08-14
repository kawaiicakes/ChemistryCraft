package com.kawaiicakes.chemistrycraft.common.items;

import com.kawaiicakes.chemistrycraft.ChemistryCraft;
import com.kawaiicakes.chemistrycraft.api.registry.MaterialDefinition;
import com.smashingmods.chemlib.common.items.ElementItem;
import com.smashingmods.chemlib.registry.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllotropeMineralItem extends AbstractMineralItem {
    public AllotropeMineralItem(MaterialDefinition materialDefinition) {
        super(materialDefinition);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        final ElementItem parentElement = ItemRegistry.getElementByName(this.getConstituents()).orElseThrow(); // FIXME: ooo. I expect there to be race conditions w/ this
        setAbbreviation(parentElement.getAbbreviation());

        components.add(MutableComponent.create(new LiteralContents(String.format("%s (%d)", parentElement.getAbbreviation(), parentElement.getAtomicNumber()))).withStyle(ChatFormatting.AQUA));
        if (!parentElement.getGroupName().isEmpty()) {
            components.add(MutableComponent.create(new LiteralContents(parentElement.getGroupName())).withStyle(ChatFormatting.GRAY));
        }
        components.add(MutableComponent.create(new LiteralContents("ChemistryCraft")).withStyle(ChemistryCraft.MOD_ID_TEXT_STYLE)); //   TODO: ugh.
    }
}
