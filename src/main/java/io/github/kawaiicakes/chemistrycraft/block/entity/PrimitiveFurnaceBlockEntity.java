package io.github.kawaiicakes.chemistrycraft.block.entity;

import io.github.kawaiicakes.chemistrycraft.screen.PrimitiveFurnaceBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.github.kawaiicakes.chemistrycraft.registry.BlockEntityRegistry.PRIMITIVE_FURNACE_ENTITY;
import static io.github.kawaiicakes.chemistrycraft.registry.ItemRegistry.BALLSMUNGUS;

public class PrimitiveFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    //  Inventory of the block entity
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged(); //    Reloads chunk/block on change of contents
        }
    };

    //  Makes inventory available via capabilities
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data; //  This field is responsible for carrying data generated via ticking for display on the GUI later.
    private int progress = 0;
    private int maxProgress = 78;

    public PrimitiveFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(PRIMITIVE_FURNACE_ENTITY.get(), blockPos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) { //  'Saves' these values into our ContainerData
                return switch (index) {
                    case 0 -> PrimitiveFurnaceBlockEntity.this.progress;
                    case 1 -> PrimitiveFurnaceBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) { //  'Saves' these values into our ContainerData
                switch (index) {
                    case 0 -> PrimitiveFurnaceBlockEntity.this.progress = value;
                    case 1 -> PrimitiveFurnaceBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() { //  number of saved variables in ContainerData
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BALLS!!");
    }

    @Nullable
    @Override //    renders GUI when called
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new PrimitiveFurnaceBlockMenu(id, inventory, this, this.data);
    }

    @Override //    Allows import/export to inventory
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override //    ?
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override //    ?
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override   //  Saves inventory
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("furnace_progress", this.progress);

        super.saveAdditional(nbt);
    }

    @Override   //  Loads inventory
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.progress = nbt.getInt("furnace_progress");
    }

    //  Called inside of block class when destroyed so that inventory drops
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PrimitiveFurnaceBlockEntity primitiveFurnaceBlockEntity) {
        if (!level.isClientSide()) {
            if (hasRecipe(primitiveFurnaceBlockEntity)) {
                primitiveFurnaceBlockEntity.progress++;
                setChanged(level, blockPos, blockState); // Causes reload when necessary

                if (primitiveFurnaceBlockEntity.progress >= primitiveFurnaceBlockEntity.maxProgress) {
                    craftItem(primitiveFurnaceBlockEntity);
                }
            } else {
                primitiveFurnaceBlockEntity.resetProgress();
                setChanged(level, blockPos, blockState);
            }
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }
    private static void craftItem(PrimitiveFurnaceBlockEntity entity) { //  FIXME: hard coded.
        if (hasRecipe(entity)) {
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.setStackInSlot(2, new ItemStack(BALLSMUNGUS.get(), entity.itemHandler.getStackInSlot(2).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(PrimitiveFurnaceBlockEntity entity) { //   FIXME: this is hard coded. Datagen and JSON recipes come later.
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots()); //  makes inventory to make life easier
        for (int i = 0; i < entity.itemHandler.getSlots(); ++i) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasMineralInSlot1 = entity.itemHandler.getStackInSlot(1).getItem() == BALLSMUNGUS.get();

        return hasMineralInSlot1 && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, new ItemStack(BALLSMUNGUS.get(), 1));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem() == itemStack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }


}
