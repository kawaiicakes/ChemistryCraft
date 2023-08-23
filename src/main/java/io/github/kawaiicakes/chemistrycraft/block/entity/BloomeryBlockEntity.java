package io.github.kawaiicakes.chemistrycraft.block.entity;

import io.github.kawaiicakes.chemistrycraft.block.BloomeryBlock;
import io.github.kawaiicakes.chemistrycraft.capabilities.AbstractEnergyStorage;
import io.github.kawaiicakes.chemistrycraft.capabilities.WrappedItemHandler;
import io.github.kawaiicakes.chemistrycraft.network.ChemistryPackets;
import io.github.kawaiicakes.chemistrycraft.network.packets.EnergyS2CPacket;
import io.github.kawaiicakes.chemistrycraft.recipe.BloomeryRecipe;
import io.github.kawaiicakes.chemistrycraft.screen.BloomeryBlockMenu;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

import static io.github.kawaiicakes.chemistrycraft.registry.BlockEntityRegistry.BLOOMERY_ENTITY;
import static net.minecraft.world.item.Items.REDSTONE;
import static net.minecraft.world.item.Items.IRON_INGOT;

public class BloomeryBlockEntity extends BlockEntity implements MenuProvider {
    //  Inventory of the block entity
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged(); //    Reloads chunk/block on change of contents
        }

        @Override // Checks if an item can go into a slot.
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == REDSTONE; //Leftmost slot here
                case 1 -> stack.getItem() == IRON_INGOT; //Stuff to smelt goes here
                case 2 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    // Referenced in #getCapability
    private final AbstractEnergyStorage ENERGY_STORAGE = new AbstractEnergyStorage(60000, 256) {
        @Override
        public void onEnergyChanged() {
            setChanged(); //    Forces chunk to save later
            ChemistryPackets.sendToClients(new EnergyS2CPacket(this.energy, getBlockPos()));
        }
    };

    private static final int ENERGY_REQ = 32; // Energy change per tick

    //  Makes inventory available via capabilities. a lazy handler must exist for each capability used
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    //  When #getCapability on BloomerBlockEntity is called, this method ensures that the correct sides line up for I/O.
    //  Furthermore, it defines the faces that accept certain I/O.
    private final Map<Direction, LazyOptional<WrappedItemHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (index) -> index == 1,
                            (index, stack) -> itemHandler.isItemValid(1, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(1, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (index) -> index == 0 || index == 1,
                            (index, stack) -> itemHandler.isItemValid(0, stack) || itemHandler.isItemValid(1, stack))));

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data; //  This field is responsible for carrying data generated via ticking for display on the GUI later.
    private int progress = 0;
    private int maxProgress = 78;

    public BloomeryBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BLOOMERY_ENTITY.get(), blockPos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) { //  'Saves' these values into our ContainerData
                return switch (index) {
                    case 0 -> BloomeryBlockEntity.this.progress;
                    case 1 -> BloomeryBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) { //  'Saves' these values into our ContainerData
                switch (index) {
                    case 0 -> BloomeryBlockEntity.this.progress = value;
                    case 1 -> BloomeryBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() { //  number of saved variables in ContainerData
                return 2;
            }
        };
    }

    @Override // FIXME: this isn't displaying anything.
    public Component getDisplayName() {
        return Component.translatable("bloomery");
    }

    @Nullable
    @Override //    renders GUI when called
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new BloomeryBlockMenu(id, inventory, this, this.data);
    }

    public IEnergyStorage getEnergyStorage() {
        return this.ENERGY_STORAGE;
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }

    @Override //    Allows import/export to inventory
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return this.lazyEnergyHandler.cast();
        }

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) {
                return this.lazyItemHandler.cast();
            }

            if (this.directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(BloomeryBlock.FACING);

                if (side == Direction.UP || side == Direction.DOWN) {
                    return this.directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> this.directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> this.directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> this.directionWrappedHandlerMap.get(side).cast();
                    case WEST -> this.directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }

    @Override //    ?
    public void onLoad() {
        super.onLoad();
        this.lazyItemHandler = LazyOptional.of(() -> this.itemHandler);
        this.lazyEnergyHandler = LazyOptional.of(() -> this.ENERGY_STORAGE);
    }

    @Override //    ?
    public void invalidateCaps() {
        super.invalidateCaps();
        this.lazyItemHandler.invalidate();
        this.lazyEnergyHandler.invalidate();
    }

    @Override   //  Saves inventory
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("bloomery.progress", this.progress);
        nbt.putInt("bloomery.energy", this.ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(nbt);
    }

    @Override   //  Loads inventory
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.progress = nbt.getInt("bloomery.progress");
        this.ENERGY_STORAGE.setEnergy(nbt.getInt("bloomery.energy"));
    }

    //  Called inside of block class when destroyed so that inventory drops
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, BloomeryBlockEntity bloomeryBlockEntity) {
        if (!level.isClientSide()) {
            if (hasItemInFirstSlot(bloomeryBlockEntity)) {
                bloomeryBlockEntity.ENERGY_STORAGE.receiveEnergy(64, false);
            }

            if (hasRecipe(bloomeryBlockEntity) && hasEnoughEnergy(bloomeryBlockEntity)) {
                bloomeryBlockEntity.progress++;
                extractEnergy(bloomeryBlockEntity);
                setChanged(level, blockPos, blockState); // Causes reload when necessary

                if (bloomeryBlockEntity.progress >= bloomeryBlockEntity.maxProgress) {
                    craftItem(bloomeryBlockEntity);
                }
            } else {
                setChanged(level, blockPos, blockState);
            }
        }
    }

    private static void extractEnergy(BloomeryBlockEntity bloomeryBlockEntity) {
        bloomeryBlockEntity.ENERGY_STORAGE.extractEnergy(ENERGY_REQ, false);
    }

    private static boolean hasEnoughEnergy(BloomeryBlockEntity bloomeryBlockEntity) {
        return bloomeryBlockEntity.ENERGY_STORAGE.getEnergyStored() >= ENERGY_REQ;
    }

    // FIXME: hardcoded
    private static boolean hasItemInFirstSlot(BloomeryBlockEntity bloomeryBlockEntity) {
        return bloomeryBlockEntity.itemHandler.getStackInSlot(0).getItem() == REDSTONE;
    }

    private void resetProgress() {
        this.progress = 0;
    }
    private static void craftItem(BloomeryBlockEntity entity) { // Called when item is crafted
        Level level = entity.getLevel();
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots()); //  makes inventory to make life easier
        for (int i = 0; i < entity.itemHandler.getSlots(); ++i) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BloomeryRecipe> recipe = level.getRecipeManager().getRecipeFor(BloomeryRecipe.Type.INSTANCE, inventory, level);

        if (hasRecipe(entity)) {
            entity.itemHandler.extractItem(1, 1, false); // recipe.get().getResultItem().getItem(); ignores count!
            entity.itemHandler.setStackInSlot(2, new ItemStack(recipe.get().getResultItem().getItem(), entity.itemHandler.getStackInSlot(2).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(BloomeryBlockEntity entity) { // Checks if a valid recipe exists
        Level level = entity.getLevel();
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots()); //  makes inventory to make life easier
        for (int i = 0; i < entity.itemHandler.getSlots(); ++i) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BloomeryRecipe> recipe = level.getRecipeManager().getRecipeFor(BloomeryRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem() == itemStack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}
