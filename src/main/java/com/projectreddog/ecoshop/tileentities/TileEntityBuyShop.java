package com.projectreddog.ecoshop.tileentities;

import java.util.UUID;

import com.projectreddog.ecoshop.init.ModItems;
import com.projectreddog.ecoshop.item.ItemCredit;
import com.projectreddog.ecoshop.item.ItemEcoShopUpgrade;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.utility.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
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

	// slot 27 items in
	// slot 28 credits in
	// slot 29 items out
	// slot 30 credits out

	private int mode = 0; // 0=buy, 1 = sell
	private UUID owner;
	private int CreditAmount;
	private int IOH;

	private int itemsOnHand;

	private int creditsOnHand;

	public int getCreditsOnHand() {
		return creditsOnHand;
	}

	// last stack for ref used to save item details Ignore the stack size!
	private ItemStack lastStack;

	public void resupplyItemOutput() {
		if (itemsOnHand > 0) {
			// we have some on hand
			ItemStack isOutput = inventory[29];
			if (isOutput != null) {
				// we have some stack !
				if (isOutput.stackSize < isOutput.getMaxStackSize()) {
					// we have room for more!
					int amtToMove = isOutput.getMaxStackSize() - isOutput.stackSize;
					if (amtToMove > itemsOnHand) {
						// we can only move some :(
						inventory[29].stackSize = inventory[29].stackSize + itemsOnHand;
						itemsOnHand = 0;
						LogHelper.info("Range Check Returned :");
					} else {
						// move just what is needed
						inventory[29].stackSize = inventory[29].stackSize + amtToMove;
						itemsOnHand = itemsOnHand - amtToMove;
					}
				}
			} else {
				// no stack so move it !
				int amtToMove = lastStack.getMaxStackSize();

				if (amtToMove > itemsOnHand) {
					// we can only move some :(
					// lastStack.stackSize = 0;
					inventory[29] = lastStack.copy();
					inventory[29].stackSize = itemsOnHand;
					itemsOnHand = 0;
				} else {
					// move just what is needed
					// lastStack.stackSize = 0;
					inventory[29] = lastStack.copy();
					inventory[29].stackSize = amtToMove;
					itemsOnHand = itemsOnHand - amtToMove;

				}

			}

		}
	}

	public void consumeItemInput() {
		ItemStack isInput = inventory[27];
		ItemStack isOutput = inventory[29];
		if (isOutput != null) {

			lastStack = isOutput.copy();
		}
		if (isInput != null) {
			if (isOutput != null) {
				// lastStack = isOutput;
				// both slots occupied
				// so they must Match else kick the stack out of the inventory into the world !! HAHAHAHAHAH

				if (isInput.getItem() == isOutput.getItem() && isInput.getItemDamage() == isOutput.getItemDamage()) {
					if (isOutput.stackSize < isOutput.getMaxStackSize()) {
						// it can hold more
						int amtToMove = isOutput.getMaxStackSize() - isOutput.stackSize;
						if (isInput.stackSize > amtToMove) {
							// move only part
							itemsOnHand = itemsOnHand + isInput.stackSize - amtToMove;
							inventory[29].stackSize = inventory[29].stackSize + amtToMove;
							inventory[27] = null;
						} else {
							// move it all to output
							inventory[29].stackSize = inventory[29].stackSize + isInput.stackSize;
							inventory[27] = null;
						}
					} else {
						itemsOnHand = itemsOnHand + isInput.stackSize;
						inventory[27] = null; // Remove the item !
					}
				} else {
					// Its a different item so pop it out !
					inventory[27] = null; // Remove the item !
					// TODO : Add code to remove this stack & spawn it in the world !
				}
			} else {
				// input has an item output not so put it in output
				inventory[29] = isInput; // Remove the item !
				inventory[27] = null; // Remove the item !
			}

		}
	}

	public void consumeCreditInput() {
		ItemStack isInput = inventory[28];
		ItemStack isOutput = inventory[30];// not sure if needed
		if (isOutput != null) {
			// may not be needed
			lastStack = isOutput.copy();
		}
		if (isInput != null) {
			// MUST BE a credit type
			if (isInput.getItem() instanceof ItemCredit) {
				ItemCredit ic = (ItemCredit) isInput.getItem();
				creditsOnHand = creditsOnHand + (ic.GetValue() * isInput.stackSize);
				inventory[28] = null;
			} else {
				// its not a credit drop it on the ground in the world
				// TODO spawn this in the world
			}
		}

	}

	public void resupplyCreditOutput() {
		// keep credits in the output stack
		ItemStack isCreditOut = inventory[30];
		if (isCreditOut != null) {
			// we have a stack in the output ! for now keep as is
			// TODO make this stack grow to max size if we have the $$$ in place
		} else {
			if (creditsOnHand > 0) {
				// no stack & we have credits put max amt in output!!.

				if (creditsOnHand > 10000) {
					int qty = creditsOnHand / 10000;
					creditsOnHand = creditsOnHand - 10000 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_TENTHOUSAND, qty);
				} else if (creditsOnHand > 5000) {
					int qty = creditsOnHand / 5000;
					creditsOnHand = creditsOnHand - 5000 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_FIVETHOUSAND, qty);
				} else if (creditsOnHand > 1000) {
					int qty = creditsOnHand / 1000;
					creditsOnHand = creditsOnHand - 1000 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_ONETHOUSAND, qty);
				} else if (creditsOnHand > 500) {
					int qty = creditsOnHand / 500;
					creditsOnHand = creditsOnHand - 500 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_FIVEHUNDRED, qty);
				} else if (creditsOnHand > 100) {
					int qty = creditsOnHand / 100;
					creditsOnHand = creditsOnHand - 100 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_ONEHUNDRED, qty);
				} else if (creditsOnHand > 20) {
					int qty = creditsOnHand / 20;
					creditsOnHand = creditsOnHand - 20 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_TWENTY, qty);
				} else if (creditsOnHand > 10) {
					int qty = creditsOnHand / 10;
					creditsOnHand = creditsOnHand - 10 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_TEN, qty);
				} else if (creditsOnHand > 5) {
					int qty = creditsOnHand / 5;
					creditsOnHand = creditsOnHand - 5 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_FIVE, qty);
				} else if (creditsOnHand > 1) {
					int qty = creditsOnHand / 1;
					creditsOnHand = creditsOnHand - 1 * qty;
					inventory[30] = new ItemStack(ModItems.CREDIT_ONE, qty);
				}
			}
		}
	}

	// public int getInventoryOnHand() {
	// if (getMode() == Reference.STORE_BLOCK_MODE_SELL) {
	// // we are selling the item in this slot so lets prep this stuff (slot 0)
	// if (inventory[0] != null) {
	// Item item = inventory[0].getItem();
	// // int qty = inventory[0].stackSize;
	// int qty = 0;
	// // TODO replace this placeholder with real code.
	// for (int i = 27; i < 31; i++) {
	// if (inventory[i] != null) {
	// Item item2 = inventory[i].getItem();
	// if (item2 == item) {
	// if (inventory[i].getItemDamage() == inventory[0].getItemDamage()) {
	// // Same Damage value only!
	// // same item count the stock!
	// qty = qty + inventory[i].stackSize;
	// }
	// }
	// }
	// }
	// return qty;
	// }
	// } else if (getMode() == Reference.STORE_BLOCK_MODE_BUY) {
	// // we need to count the money we have in inventory so we know how much the player can get paid for!
	// int qty = 0;
	// for (int i = 27; i < 31; i++) {
	// if (inventory[i] != null) {
	// Item item = inventory[i].getItem();
	// if (item instanceof ItemCredit) {
	// ItemCredit ic = (ItemCredit) item;
	// qty = qty + (ic.GetValue() * inventory[i].stackSize);
	// }
	// }
	// }
	// return qty;
	// }
	// // must be in an invalid mode just go ahead and return 0 as a fail safe
	// // Else i'm going to have to go ahead and ask you to work this weekend on TPS reports
	// return 0;
	// }

	public int getIOH() {
		return itemsOnHand;
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

	public int calcCustomersPayment() {
		// customers input slots
		// 3-11
		int payAmount = 0;
		for (int i = 3; i < 12; i++) {
			if (getStackInSlot(i) != null) {
				if (getStackInSlot(i).getItem() instanceof ItemCredit) {
					payAmount = payAmount + ((ItemCredit) getStackInSlot(i).getItem()).GetValue();
				}
			}
		}
		return payAmount;
	}

	@Override
	public void updateEntity() {
		// LogHelper.info("Range Check Returned :" + isRangeInSecondRange(0, 8, 20, 28));

		//

		if (this.worldObj.isRemote) {
			// client
		} else {
			// server
			consumeItemInput();
			resupplyItemOutput();
			consumeCreditInput();
			resupplyCreditOutput();

			if (mode == Reference.STORE_BLOCK_MODE_SELL) {
				// we are selling
				// slots

				// calculate the value of the input
				int amtPaid = calcCustomersPayment();

				// if input is > required amount (creditAmount) then check if we have enoug to sell
				if (amtPaid > CreditAmount) {
					// if we have enough to sell
					if (getStackInSlot(0) != null) {
						if (getStackInSlot(29) != null) {
							if (itemsOnHand + getStackInSlot(29).stackSize >= getStackInSlot(0).stackSize) {
								int amtToSell = getStackInSlot(0).stackSize;
								// then check if we have room in the output sell them the stack worth
								if (isOutputRoomForStack(getStackInSlot(0))) {
									// we have room !

									// remove the amt of money required & spawn the items in output stacks & reduce the inventory level
								}

							}
						}
					}
				}

			} else if (mode == Reference.STORE_BLOCK_MODE_BUY) {
				// we are buying a block
			}
		}
	}

	public boolean isOutputRoomForStack(ItemStack itemStack) {
		if (itemStack == null) {
			// input is null so just return false
			return false;
		}

		// cache the item type, damage , stack size
		int qtyNeeded = itemStack.stackSize;
		for (int i = 12; i <= 26; i++) {

			if (doStacksMatchOtherThanSize(itemStack, getStackInSlot(i))) {
				// same stack ! check sizes
				if (getStackInSlot(i) != null) {
					qtyNeeded = qtyNeeded - (getStackInSlot(i).getMaxStackSize() - getStackInSlot(i).stackSize);
					if (qtyNeeded <= 0) {
						// as soon as we have enough space stop & return dont care about the rest.
						return true;
					}
				}
			}

		}

		return false;
	}

	public boolean doStacksMatchOtherThanSize(ItemStack is1, ItemStack is2) {
		if (is1 != null) {
			if (is2 != null) {
				if (is1.getItem() == is2.getItem()) {
					if (is1.getItemDamage() == is2.getItemDamage()) {
						return true;
					}
				}
			}
		}
		// default
		return false;
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
		inventory = new ItemStack[31];

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
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer playerIn) {
		return playerIn.getDistanceSq(this.xCoord, this.yCoord, this.zCoord) < 64;

	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {

		// upgrade slots
		// 1-2
		// customer slots left
		// 3-11
		// customer slots right
		// 12-26
		// Owner only STOCK area

		// slot 27 items in
		// slot 28 credits in
		// slot 29 items out
		// slot 30 credits out

		if (slot == 1 || slot == 2) {
			if (itemStack.getItem() instanceof ItemEcoShopUpgrade) {
				return true;
			} else {
				return false;
			}
		}
		if (slot == 28) {
			if (itemStack.getItem() instanceof ItemCredit) {
				return true;

			} else {
				return false;
			}
		}
		if (slot == 27) {
			if (itemStack.getItem() instanceof ItemCredit) {
				// dont allow money to be instrted into the item in slot
				return false;

			} else {
				return true;
			}
		}
		return true;
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this block.
	 */
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// ??? {DOWN, UP, NORTH, SOUTH, WEST, EAST}; // GUESS
		// upgrade slots
		// 1-2
		// customer slots left
		// 3-11
		// customer slots right
		// 12-26
		// Owner only STOCK area

		// slot 27 items in
		// slot 28 credits in
		// slot 29 items out
		// slot 30 credits out

		switch (side) {
		case 0:// down
			return new int[] { 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
		case 1:// up
			return new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		case 2:// n
			return new int[] { 27 };
		case 3:// s
			return new int[] { 27 };
		case 4:// w
			return new int[] { 27 };
		case 5:// e
			return new int[] { 27 };
		}

		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
		return isItemValidForSlot(slot, itemStack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		// TODO need to test & review this
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);

		// inventory
		mode = compound.getInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "MODE");
		CreditAmount = compound.getInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "CREDITAMOUNT");
		itemsOnHand = compound.getInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "ITEMONHAND");
		creditsOnHand = compound.getInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "CREDITSONHAND");
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
		compound.setInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "ITEMONHAND", itemsOnHand);
		compound.setInteger(Reference.ECOSHOP_MOD_NBT_PREFIX + "CREDITSONHAND", creditsOnHand);
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

			return itemsOnHand + ((inventory[29] == null) ? 0 : inventory[29].stackSize);

		case 3:

			return creditsOnHand + (inventory[30] == null ? 0 : inventory[30].stackSize * (inventory[30].getItem() instanceof ItemCredit ? ((ItemCredit) inventory[30].getItem()).GetValue() : 0));

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
			itemsOnHand = value;
			break;
		case 3:
			creditsOnHand = value;
		default:
			break;
		}

	}

	public int getFieldCount() {
		return 4;
	}

}
