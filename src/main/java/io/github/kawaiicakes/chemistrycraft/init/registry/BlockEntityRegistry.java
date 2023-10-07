package io.github.kawaiicakes.chemistrycraft.init.registry;

import io.github.kawaiicakes.chemistrycraft.init.registry.block.entity.BloomeryBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;
import static io.github.kawaiicakes.chemistrycraft.init.registry.BlockRegistry.BLOOMERY;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTRY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<BlockEntityType<BloomeryBlockEntity>> BLOOMERY_ENTITY = //  TODO: temporary implementation for purposes of practice
            BLOCK_ENTITY_REGISTRY.register("bloomery", () -> BlockEntityType.Builder.of(BloomeryBlockEntity::new, BLOOMERY.get()).build(null));
}
