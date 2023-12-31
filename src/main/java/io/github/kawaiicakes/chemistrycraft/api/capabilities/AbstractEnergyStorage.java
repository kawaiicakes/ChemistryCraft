package io.github.kawaiicakes.chemistrycraft.api.capabilities;

import net.minecraftforge.energy.EnergyStorage;

/**
 * Abstract subclassing of EnergyStorage allows implementation of energy capabilities with the
 * ability to call <code>#onEnergyChanged</code> on change of energy. Subclasses agree to define
 * a custom behaviour when energy is changed by overriding <code>#onEnergyChanged.</code>
 */
public abstract class AbstractEnergyStorage extends EnergyStorage {
    public AbstractEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractedEnergy = super.extractEnergy(maxExtract, simulate);
        if (extractedEnergy != 0) {
            onEnergyChanged();
        }

        return extractedEnergy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receivedEnergy = super.receiveEnergy(maxReceive, simulate);
        if (receivedEnergy != 0) {
            onEnergyChanged();
        }

        return receivedEnergy;
    }

    public int setEnergy(int energy) {
        this.energy = energy;
        return energy;
    }

    public abstract void onEnergyChanged();
}
