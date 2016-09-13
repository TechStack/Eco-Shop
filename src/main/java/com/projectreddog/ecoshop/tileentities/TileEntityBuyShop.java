package com.projectreddog.ecoshop.tileentities;

import com.projectreddog.ecoshop.item.ItemEcoShopUpgrade;
import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBuyShop extends TileEntity implements ISidedInventory {
	protected ItemStack[] inventory;
	// upper left (accept this owner area)
	// 0-8
	// Middle top owner
	// 9-17
	// upgrade slots
	// 18-19
	// customer slots left
	// 20-28
	// customer slots right
	// 29-43
	// Owner only STOCK area
	// 44-97

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		// LogHelper.info("Range Check Returned :" + isRangeInSecondRange(0, 8, 20, 28));
	}

	public boolean isRangeInSecondRange(int start, int end, int secondStart, int secondEnd) {
		// is everything in the first range contained in the second range?
		ItemStack toCheck;
		ItemStack needed;
		for (int neededSlot = start; neededSlot <= end; neededSlot++) {

			boolean wasMatchFound = false;
			for (int checkslot = secondStart; checkslot <= secondEnd; checkslot++) {

				toCheck = inventory[checkslot];
				needed = inventory[neededSlot];
				if (needed == null) {
					wasMatchFound = true;
					break;
				}
				if (toCheck == null) {
					continue;
				}
				if (toCheck.getItem() == needed.getItem() && toCheck.getItemDamage() == needed.getItemDamage() && needed.stackSize <= toCheck.stackSize) {
					wasMatchFound = true;
					break;
				}

			}
			if (wasMatchFound == false) {
				return false;
			}
		}
		return true;
	}

	public TileEntityBuyShop() {
		inventory = new ItemStack[98];

	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}

			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return playerIn.getDistanceSq(this.xCoord, this.yCoord, this.zCoord) < 64;

	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		// TODO Auto-generated method stub
		if (slot == 18 || slot == 19) {
			if (itemStack.getItem() instanceof ItemEcoShopUpgrade) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);

		// inventory

		NBTTagList tagList = compound.getTagList(Reference.ECOSHOP_MOD_NBT_PREFIX + "Inventory", compound.getId());
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		// inventory

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		compound.setTag(Reference.ECOSHOP_MOD_NBT_PREFIX + "Inventory", itemList);

	}

}
