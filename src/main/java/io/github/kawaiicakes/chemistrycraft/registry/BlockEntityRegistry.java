package io.github.kawaiicakes.chemistrycraft.registry;

import io.github.kawaiicakes.chemistrycraft.block.entity.PrimitiveFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;
import static io.github.kawaiicakes.chemistrycraft.registry.BlockRegistry.PRIMITIVE_FURNACE;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTRIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<BlockEntityType<PrimitiveFurnaceBlockEntity>> PRIMITIVE_FURNACE_ENTITY = //  TODO: temporary implementation for purposes of practice
            BLOCK_ENTITY_REGISTRIES.register("furnace", () -> BlockEntityType.Builder.of(PrimitiveFurnaceBlockEntity::new, PRIMITIVE_FURNACE.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITY_REGISTRIES.register(bus);
    }
}
