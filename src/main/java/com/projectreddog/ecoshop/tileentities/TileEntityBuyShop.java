package com.projectreddog.ecoshop.tileentities;

import java.util.UUID;

import com.projectreddog.ecoshop.item.ItemCredit;
import com.projectreddog.ecoshop.item.ItemEcoShopUpgrade;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.utility.LogHelper;

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
	// itemstack in trasnaction slot 0 Selling or buying this
	// upgrade slots
	// 1-2
	// customer slots left
	// 3-11
	// customer slots right
	// 12-26
	// Owner only STOCK area
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
							if (inventory[i].getItemDamage() == inventory[0].getItemDamage()) {
								// Same Damage value only!
								// same item count the stock!
								qty = qty + inventory[i].stackSize;
							}
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

			if (mode == Reference.STORE_BLOCK_MODE_SELL) {
				// we are selling
				// slots

			} else if (mode == Reference.STORE_BLOCK_MODE_BUY) {
				/// block is buying the item in the slot 0
				ItemStack stackToBuy = inventory[0];
				if (stackToBuy != null) {

					if (IOH >= CreditAmount) {
						// we have enough or more than enough
						int amountNeeded = stackToBuy.stackSize;
						for (int i = 3; i < 12; i++) {
							ItemStack stackToCheck = inventory[i];
							if (stackToCheck != null) {
								if (stackToCheck.getItem() == stackToBuy.getItem()) {
									// same item !
									if (stackToCheck.getItemDamage() == stackToBuy.getItemDamage()) {
										// same damage !
										// check amounts
										int saveStackSize = stackToCheck.stackSize;
										// check here
										if (amountNeeded >= stackToCheck.stackSize) {
											decrStackSize(i, stackToCheck.stackSize);
											amountNeeded = amountNeeded - saveStackSize;

										} else if (amountNeeded < stackToCheck.stackSize) {
											decrStackSize(i, amountNeeded);
											amountNeeded = amountNeeded - amountNeeded;

										}
									}
								}
							}
							if (amountNeeded < 1) {
								// TODO add code to reduce stock inventory 27-80
								amountToCustomerOutSlots(CreditAmount);
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean amountToCustomerOutSlots(int amount) {
		int oneThousand = amount / 1000;
		amount = amount - (oneThousand * 1000);

		int fiveHundred = amount / 500;
		amount = amount - (fiveHundred * 500);

		int oneHundred = amount / 100;
		amount = amount - (oneHundred * 100);

		int twenty = amount / 20;
		amount = amount - (twenty * 20);

		int ten = amount / 10;
		amount = amount - (ten * 10);

		int five = amount / 5;
		amount = amount - (five * 5);

		int one = amount / 1;
		amount = amount - (one * 5);

		LogHelper.info("1000 x " + oneThousand + ", 500 x " + fiveHundred + ", 100 x " + oneHundred + ", 20 x " + twenty + ", 10 x " + ten + ", 5 x " + five + ", 1 x " + one);
		return false;
	}

	public boolean processesButton(int buttonID) {
		if (buttonID == Reference.GUI_BUTTON_ID_BUY_SELL) {
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

	public TileEntityBuyShop() {
		inventory = new ItemStack[81];

	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
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
