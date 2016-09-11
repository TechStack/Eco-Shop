package com.projectreddog.ecoshop.container;

import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBuyShop extends Container {

	public ContainerBuyShop(InventoryPlayer inventoryPlayer, TileEntityBuyShop buyShop) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == 0 || i == 1 || i == 2) {
					if (j == 3 || j == 7 || (j == 8 && i == 2)) {
						// none
					} else {
						// addSlotToContainer(new Slot(buyShop, j + i * 9, 8 + j * 18, 9 + i * 18));
					}
				} else {
					if (j == 3) {
						// none
					} else {
						addSlotToContainer(new Slot(buyShop, j + i * 9, 8 + j * 18, 18 + i * 18));
					}
				}
			}
		}
		// upper left (accept this owner area)
		addSlotToContainer(new Slot(buyShop, 0, 8 + 0 * 18, 9 + 0 * 18));
		addSlotToContainer(new Slot(buyShop, 1, 8 + 1 * 18, 9 + 0 * 18));
		addSlotToContainer(new Slot(buyShop, 2, 8 + 2 * 18, 9 + 0 * 18));
		addSlotToContainer(new Slot(buyShop, 3, 8 + 0 * 18, 9 + 1 * 18));
		addSlotToContainer(new Slot(buyShop, 4, 8 + 1 * 18, 9 + 1 * 18));
		addSlotToContainer(new Slot(buyShop, 5, 8 + 2 * 18, 9 + 1 * 18));
		addSlotToContainer(new Slot(buyShop, 6, 8 + 0 * 18, 9 + 2 * 18));
		addSlotToContainer(new Slot(buyShop, 7, 8 + 1 * 18, 9 + 2 * 18));
		addSlotToContainer(new Slot(buyShop, 8, 8 + 2 * 18, 9 + 2 * 18));

		// Middle top
		addSlotToContainer(new Slot(buyShop, 9, 8 + 4 * 18, 9 + 0 * 18));
		addSlotToContainer(new Slot(buyShop, 10, 8 + 5 * 18, 9 + 0 * 18));
		addSlotToContainer(new Slot(buyShop, 11, 8 + 6 * 18, 9 + 0 * 18));
		addSlotToContainer(new Slot(buyShop, 12, 8 + 4 * 18, 9 + 1 * 18));
		addSlotToContainer(new Slot(buyShop, 13, 8 + 5 * 18, 9 + 1 * 18));
		addSlotToContainer(new Slot(buyShop, 14, 8 + 6 * 18, 9 + 1 * 18));
		addSlotToContainer(new Slot(buyShop, 15, 8 + 4 * 18, 9 + 2 * 18));
		addSlotToContainer(new Slot(buyShop, 16, 8 + 5 * 18, 9 + 2 * 18));
		addSlotToContainer(new Slot(buyShop, 17, 8 + 6 * 18, 9 + 2 * 18));

		//
		addSlotToContainer(new Slot(buyShop, 9, 8 + 4 * 18, 9 + 0 * 18));
		addSlotToContainer(new Slot(buyShop, 10, 8 + 5 * 18, 9 + 0 * 18));

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == 0 || i == 1 || i == 2) {
					if (j == 3 || j == 7 || (j == 8 && i == 2)) {
						// none

					}
				} else {
					if (j == 3) {
						// none
					} else {
						// addSlotToContainer(new Slot(buyShop, j + i * 9, 8 + j * 18, 18 + i * 18));
					}
				}
			}
		} // commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return true;
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 139 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 197));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the Entity
			if (slot < 7) {
				if (!this.mergeItemStack(stackInSlot, 54, this.inventorySlots.size(), true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 54, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}

}
