package io.github.kawaiicakes.chemistrycraft.api;

import com.smashingmods.chemlib.api.Chemical;
import com.smashingmods.chemlib.api.MatterState;
import org.jetbrains.annotations.Nullable;

public interface Mineral extends Chemical {
    @Nullable
    String getPhase();
    String getParentChemical();

    default MatterState getMatterState() {
        return MatterState.SOLID;
    }
}
