package io.github.kawaiicakes.chemistrycraft.registry.item;

import com.smashingmods.chemlib.api.Element;
import com.smashingmods.chemlib.api.MetalType;
import com.smashingmods.chemlib.common.items.ElementItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class AllotropeMineralItem extends MineralItem<ElementItem> implements Element {
    public AllotropeMineralItem(String allotropeName, @Nullable String phase, String description, ElementItem parent, Properties properties) {
        super(allotropeName, phase, description, parent, properties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {

    }

    @Override
    public final int getAtomicNumber() {
        return this.parent.getAtomicNumber();
    }

    @Override
    public final int getGroup() {
        return this.parent.getGroup();
    }

    @Override
    public final int getPeriod() {
        return this.parent.getPeriod();
    }

    @Override
    public final MetalType getMetalType() {
        return this.parent.getMetalType();
    }

    @Override
    public final boolean isArtificial() {
        return this.parent.isArtificial();
    }

    @Override
    public final String getGroupName() {
        return this.parent.getGroupName();
    }

    @Override
    public String getChemicalName() {
        return this.name;
    }

    // TODO: #calculateAbbreviation static method call here
    @Override
    public String getAbbreviation() {
        return this.phase + "-" + this.parent.getAbbreviation();
    }

    @Override
    public String getChemicalDescription() {
        return this.description;
    }

    @Override
    public List<MobEffectInstance> getEffects() {
        return this.parent.getEffects();
    }

    @Override
    public int getColor() {
        return this.parent.getColor();
    }
}
