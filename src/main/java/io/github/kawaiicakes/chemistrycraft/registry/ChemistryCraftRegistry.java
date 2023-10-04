package io.github.kawaiicakes.chemistrycraft.registry;

public class ChemistryCraftRegistry {
    // New compound automatically passed through ChemLib via ChemistryCraftAddonRegistry.
    // ChemLib automatically registers the appropriate shit (dusts, etc.) for a compound.
    // ChemistryCraft will automatically register the appropriate shit (ores, etc.) for a compound.
    // ChemistryCraftAddonRegistry contains references to ChemistryCraft to make use of automatic registration.
    // This class will make a new ChemistryCraftAddonRegistry instance and feed it JSON data (compounds.json).
    // p.s. it will be necessary to make ChemistryCraft iterate over default ChemLib compounds and register appropriate
    // shit.
}
