package com.projectreddog.ecoshop.container;

import com.projectreddog.ecoshop.item.ItemEcoShopUpgrade;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBuyShopOwner extends Container {

	public ContainerBuyShopOwner(InventoryPlayer inventoryPlayer, TileEntityBuyShop buyShop) {

		// for (int i = 0; i < 6; i++) {
		// for (int j = 0; j < 9; j++) {
		//
		// addSlotToContainer(new Slot(buyShop, (j + i * 9) + 27, 8 + j * 18, 18 + i * 18));
		//
		// }
		// }

		// in Item
		addSlotToContainer(new Slot(buyShop, 27, 8 + 2 * 18, 21 + 1 * 18));
		// credit
		addSlotToContainer(new Slot(buyShop, 28, 7 + 6 * 18, 21 + 1 * 18));

		// out item
		addSlotToContainer(new Slot(buyShop, 29, 8 + 2 * 18, 24 + 3 * 18));

		// credit
		addSlotToContainer(new Slot(buyShop, 30, 7 + 6 * 18, 24 + 3 * 18));

		// commonly used vanilla code that adds the player's inventory
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
			if (slot < 4) {
				if (!this.mergeItemStack(stackInSlot, 4, this.inventorySlots.size(), true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 40, false)) {
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

	protected boolean mergeItemStack(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_) {
		boolean flag1 = false;
		int k = p_75135_2_;

		if (p_75135_4_) {
			k = p_75135_3_ - 1;
		}

		Slot slot;
		ItemStack itemstack1;

		if (p_75135_1_.isStackable()) {
			while (p_75135_1_.stackSize > 0 && (!p_75135_4_ && k < p_75135_3_ || p_75135_4_ && k >= p_75135_2_)) {
				slot = (Slot) this.inventorySlots.get(k);
				if (p_75135_1_.getItem() instanceof ItemEcoShopUpgrade) {
					// its an upgrade so slots 18 & 19 are ok
				} else {
					if (k == 2 || k == 3) {
						if (p_75135_4_) {
							--k;
						} else {
							++k;
						}
						continue;
						// keep on keeping on if it is slot 18 or 19 because those are the upgrade slots
					}
				}

				itemstack1 = slot.getStack();

				if (itemstack1 != null && itemstack1.getItem() == p_75135_1_.getItem() && (!p_75135_1_.getHasSubtypes() || p_75135_1_.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(p_75135_1_, itemstack1)) {
					int l = itemstack1.stackSize + p_75135_1_.stackSize;

					if (l <= p_75135_1_.getMaxStackSize()) {
						p_75135_1_.stackSize = 0;
						itemstack1.stackSize = l;
						slot.onSlotChanged();
						flag1 = true;
					} else if (itemstack1.stackSize < p_75135_1_.getMaxStackSize()) {
						p_75135_1_.stackSize -= p_75135_1_.getMaxStackSize() - itemstack1.stackSize;
						itemstack1.stackSize = p_75135_1_.getMaxStackSize();
						slot.onSlotChanged();
						flag1 = true;
					}
				}

				if (p_75135_4_) {
					--k;
				} else {
					++k;
				}
			}
		}

		if (p_75135_1_.stackSize > 0) {
			if (p_75135_4_) {
				k = p_75135_3_ - 1;
			} else {
				k = p_75135_2_;
			}

			while (!p_75135_4_ && k < p_75135_3_ || p_75135_4_ && k >= p_75135_2_) {
				slot = (Slot) this.inventorySlots.get(k);
				itemstack1 = slot.getStack();
				if (p_75135_1_.getItem() instanceof ItemEcoShopUpgrade) {
					// its an upgrade so slots 18 & 19 are ok
				} else {
					if (k == 2 || k == 3) {
						if (p_75135_4_) {
							--k;
						} else {
							++k;
						}
						continue;
						// keep on keeping on if it is slot 18 or 19 because those are the upgrade slots
					}
				}
				if (itemstack1 == null) {
					slot.putStack(p_75135_1_.copy());
					slot.onSlotChanged();
					p_75135_1_.stackSize = 0;
					flag1 = true;
					break;
				}

				if (p_75135_4_) {
					--k;
				} else {
					++k;
				}
			}
		}

		return flag1;
	}

}
