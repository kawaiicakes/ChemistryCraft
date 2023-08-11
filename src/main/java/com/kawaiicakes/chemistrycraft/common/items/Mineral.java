package com.kawaiicakes.chemistrycraft.common.items;

import com.smashingmods.chemlib.api.Chemical;
import com.smashingmods.chemlib.api.MatterState;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public interface Mineral extends Chemical, ItemLike {

    String getParent();
    float getRadiation(); //measured in becquerels.
    boolean isDye();
}
