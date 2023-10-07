package io.github.kawaiicakes.chemistrycraft.api;

import com.smashingmods.chemlib.api.Chemical;
import com.smashingmods.chemlib.api.MatterState;

public interface Mineral extends Chemical {
    @Override
    String getChemicalName();
    @Override
    String getChemicalDescription();
    @Override
    String getAbbreviation();
    String getPhase();
    String getParentChemical();

    @Override
    default MatterState getMatterState() {
        return MatterState.SOLID;
    }
}
