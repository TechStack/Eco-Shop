package com.projectreddog.ecoshop.tileentities;

import java.util.UUID;

import com.projectreddog.ecoshop.item.ItemCredit;
import com.projectreddog.ecoshop.item.ItemEcoShopUpgrade;
import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
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
	// 27 -80 new

	private int mode = 0; // 0=buy, 1 = sell
	private UUID owner;
	private int CreditAmount;
	private int IOH;

	public int getInventoryOnHand() {
		if (getMode() == Reference.STORE_BLOCK_MODE_SELL) {
			// we are selling the item in this slot so lets prep this stuff (slot 0)
			if (inventory[0] != null) {
				Item item = inventory[0].getItem();
				// int qty = inventory[0].stackSize;
				int qty = 0;
				// TODO replace this placeholder with real code.
				for (int i = 27; i < 81; i++) {
					if (inventory[i] != null) {
						Item item2 = inventory[i].getItem();
						if (item2 == item) {
							// same item count the stock!
							qty = qty + inventory[i].stackSize;
						}
					}
				}
				return qty;
			}
		} else if (getMode() == Reference.STORE_BLOCK_MODE_BUY) {
			// we need to count the money we have in inventory so we know how much the player can get paid for!
			int qty = 0;
			for (int i = 27; i < 81; i++) {
				if (inventory[i] != null) {
					Item item = inventory[i].getItem();
					if (item instanceof ItemCredit) {
						ItemCredit ic = (ItemCredit) item;
						qty = qty + (ic.GetValue() * inventory[i].stackSize);
					}
				}
			}
			return qty;
		}
		// must be in an invalid mode just go ahead and return 0 as a fail safe
		// Else i'm going to have to go ahead and ask you to work this weekend on TPS reports
		return 0;
	}

	public int getIOH() {
		return IOH;
	}

	public int getCreditAmount() {
		return CreditAmount;
	}

	public void setCreditAmount(int creditAmount) {
		CreditAmount = creditAmount;
		if (CreditAmount < 0) {
			CreditAmount = 0;
		}
	}

	public String GetOwnerName() {
		if (owner == null) {
			return "";
		} else {
			// func_152358_ax= getting the playerprofilecache
			// func_152652_a gets the profile
			return MinecraftServer.getServer().func_152358_ax().func_152652_a(owner).getName();

		}
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public int getMode() {
		return mode;
	}

	public void toggleMode() {
		if (mode == Reference.STORE_BLOCK_MODE_BUY) {
			mode = Reference.STORE_BLOCK_MODE_SELL;
		} else if (mode == Reference.STORE_BLOCK_MODE_SELL) {
			mode = Reference.STORE_BLOCK_MODE_BUY;
		}
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		// LogHelper.info("Range Check Returned :" + isRangeInSecondRange(0, 8, 20, 28));

		//

		if (this.worldObj.isRemote) {
			// client
		} else {
			// server
			// TODO maybe optimize a bit and only calculate if there was a change in stock
			IOH = getInventoryOnHand();
		}
	}

	public boolean processesButton(int buttonID) {
		if (buttonID == Reference.GUI_BUTTON_ID_BUY_SELL) {
			// TODO toggle my buy / sell state
			toggleMode();
			return true;
		} else if (buttonID == Reference.GUI_BUTTON_ID_MINUS100) {
			setCreditAmount(getCreditAmount() - 100);
			return true;
		} else if (buttonID == Reference.GUI_BUTTON_ID_MINUS10) {
			setCreditAmount(getCreditAmount() - 10);
			return true;
		} else if (buttonID == Reference.GUI_BUTTON_ID_MINUS) {
			setCreditAmount(getCreditAmount() - 1);
			return true;
		} else if (buttonID == Reference.GUI_BUTTON_ID_PLUS) {
			setCreditAmount(getCreditAmount() + 1);
			return true;
		} else if (buttonID == Reference.GUI_BUTTON_ID_PLUS10) {
			setCreditAmount(getCreditAmount() + 10);
			return true;
		} else if (buttonID == Reference.GUI_BUTTON_ID_PLUS100) {
			setCreditAmount(getCreditAmount() + 100);
			return true;
		}
		return false;
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
		inventory = new ItemStack[81];

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
		mode = compound.getInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "MODE");
		CreditAmount = compound.getInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "CREDITAMOUNT");
		long least = compound.getLong(Reference.ECOSHOP_MOD_NBT_PREFIX + "OWNER_UUID_least");
		long most = compound.getLong(Reference.ECOSHOP_MOD_NBT_PREFIX + "OWNER_UUID_most");
		setOwner(new UUID(most, least));

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
		compound.setInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "MODE", mode);
		compound.setInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "CREDITAMOUNT", CreditAmount);
		compound.setLong(Reference.ECOSHOP_MOD_NBT_PREFIX + "OWNER_UUID_least", getOwner().getLeastSignificantBits());
		compound.setLong(Reference.ECOSHOP_MOD_NBT_PREFIX + "OWNER_UUID_most", getOwner().getMostSignificantBits());
		compound.setTag(Reference.ECOSHOP_MOD_NBT_PREFIX + "Inventory", itemList);

	}

	public int getField(int id) {
		switch (id) {
		case 0:
			return this.mode;
		case 1:
			return this.getCreditAmount();
		case 2:
			return IOH;
		default:
			break;
		}
		return 0;

	}

	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.mode = value;
			break;
		case 1:
			setCreditAmount(value);
			break;
		case 2:
			IOH = value;
			break;
		default:
			break;
		}

	}

	public int getFieldCount() {
		return 3;
	}

}
