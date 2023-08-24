package io.github.kawaiicakes.chemistrycraft.api.block.entity.interfaces;

import io.github.kawaiicakes.chemistrycraft.api.capabilities.IPressureHandler;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Implementing classes agree to store and handle pressure.
 */
public interface PressureProvider {
    IPressureHandler getPressureHandler();
    LazyOptional<IPressureHandler> getLazyPressureHandler();
    void setLazyPressureHandler(LazyOptional<IPressureHandler> handler);
    void setPressure(float kPa);
}
