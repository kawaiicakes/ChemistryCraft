package io.github.kawaiicakes.chemistrycraft.registry;

import io.github.kawaiicakes.chemistrycraft.block.entity.BloomeryBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.registry.BlockRegistry.BLOOMERY;
import static io.github.kawaiicakes.chemistrycraft.registry.ChemistryCraftRegistry.BLOCK_ENTITY_REGISTRY;

public class BlockEntityRegistry {
    public static final RegistryObject<BlockEntityType<BloomeryBlockEntity>> BLOOMERY_ENTITY = //  TODO: temporary implementation for purposes of practice
            BLOCK_ENTITY_REGISTRY.register("bloomery", () -> BlockEntityType.Builder.of(BloomeryBlockEntity::new, BLOOMERY.get()).build(null));
}
