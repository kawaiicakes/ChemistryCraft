package io.github.kawaiicakes.chemistrycraft.screen;

import io.github.kawaiicakes.chemistrycraft.api.block.entity.BloomeryBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

import static io.github.kawaiicakes.chemistrycraft.init.registry.BlockRegistry.BLOOMERY;
import static io.github.kawaiicakes.chemistrycraft.init.registry.MenuRegistry.BLOOMERY_MENU;

public class BloomeryBlockMenu extends AbstractContainerMenu {
    public final BloomeryBlockEntity entity;
    private final Level level;
    private final ContainerData data; // takes ContainerData from BloomeryBlockEntity

    public BloomeryBlockMenu(int id, Inventory inv, FriendlyByteBuf data) {
        this(id, inv, inv.player.level.getBlockEntity(data.readBlockPos()), new SimpleContainerData(2));
        //  SimpleContainerData constructor argument must = return of getCount of BloomeryBlockEntity$data
    }
    public BloomeryBlockMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(BLOOMERY_MENU.get(), id);
        checkContainerSize(inv, 3); //  Number = that in ItemStackHandler
        this.entity = (BloomeryBlockEntity) entity;
        this.level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 12, 15));
            this.addSlot(new SlotItemHandler(handler, 1, 86, 15));
            this.addSlot(new SlotItemHandler(handler, 2, 86, 60));
        }); // Defines custom slots after making sure appropriate capabilities are present

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0; //  Checks for progress
    }

    public BloomeryBlockEntity getBlockEntity() {
        return this.entity;
    }

    public int getScaledProgress() { // Determines visual progress percentage
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    // TODO: holy. shit. study how this (fields and quickMoveStack()) actually works.
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have! (check checkContainerSize() in constructor)

    @Override // defines shift click behaviour ig?
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override //    Checks if still able to actually right-click block at the position
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, this.entity.getBlockPos()), player, BLOOMERY.get());
    }

    private void addPlayerInventory(Inventory playerInventory) { //     FIXME: magic numbers
        for (int i = 0; i < 3; ++i) { //TODO: difference b/w ++i and i++? what is this method doing anyway?
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) { //    FIXME: magic numbers
        for (int i = 0; i < 9; ++i) { //how and where to place different slots? I'm gonna fix these magic numbers then
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
