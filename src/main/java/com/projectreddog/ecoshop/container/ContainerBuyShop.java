package com.projectreddog.ecoshop.container;

import com.projectreddog.ecoshop.inventory.SlotUpgradeOnly;
import com.projectreddog.ecoshop.item.ItemEcoShopUpgrade;
import com.projectreddog.ecoshop.item.ItemUnlimitedInventory;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBuyShop extends Container {

	public ContainerBuyShop(InventoryPlayer inventoryPlayer, TileEntityBuyShop buyShop) {

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

		// upper right
		addSlotToContainer(new SlotUpgradeOnly(buyShop, 18, 8 + 8 * 18, 9 + 0 * 18));
		addSlotToContainer(new SlotUpgradeOnly(buyShop, 19, 8 + 8 * 18, 9 + 1 * 18));

		
		
		///
		
		//mid row far left (input) 
		addSlotToContainer(new Slot(buyShop, 20, 8 + 0 * 18, 18 + 3 * 18));
		addSlotToContainer(new Slot(buyShop, 21, 8 + 1 * 18, 18 + 3 * 18));
		addSlotToContainer(new Slot(buyShop, 22, 8 + 2 * 18, 18 + 3 * 18));
		addSlotToContainer(new Slot(buyShop, 23, 8 + 0 * 18, 18 + 4 * 18));
		addSlotToContainer(new Slot(buyShop, 24, 8 + 1 * 18, 18 + 4 * 18));
		addSlotToContainer(new Slot(buyShop, 25, 8 + 2 * 18, 18 + 4 * 18));
		addSlotToContainer(new Slot(buyShop, 26, 8 + 0 * 18, 18 + 5 * 18));
		addSlotToContainer(new Slot(buyShop, 27, 8 + 1 * 18, 18 + 5 * 18));
		addSlotToContainer(new Slot(buyShop, 28, 8 + 2 * 18, 18 + 5 * 18));
//
		// mid row far rigth (output slots)
		addSlotToContainer(new Slot(buyShop, 29, 8 + 4 * 18, 18 + 3 * 18));
		addSlotToContainer(new Slot(buyShop, 30, 8 + 5 * 18, 18 + 3 * 18));
		addSlotToContainer(new Slot(buyShop, 31, 8 + 6 * 18, 18 + 3 * 18));
		addSlotToContainer(new Slot(buyShop, 32, 8 + 7 * 18, 18 + 3 * 18));
		addSlotToContainer(new Slot(buyShop, 33, 8 + 8 * 18, 18 + 3 * 18));

		addSlotToContainer(new Slot(buyShop, 34, 8 + 4 * 18, 18 + 4 * 18));
		addSlotToContainer(new Slot(buyShop, 35, 8 + 5 * 18, 18 + 4 * 18));
		addSlotToContainer(new Slot(buyShop, 36, 8 + 6 * 18, 18 + 4 * 18));
		addSlotToContainer(new Slot(buyShop, 37, 8 + 7 * 18, 18 + 4 * 18));
		addSlotToContainer(new Slot(buyShop, 38, 8 + 8 * 18, 18 + 4 * 18));

		addSlotToContainer(new Slot(buyShop, 39, 8 + 4 * 18, 18 + 5 * 18));
		addSlotToContainer(new Slot(buyShop, 40, 8 + 5 * 18, 18 + 5 * 18));
		addSlotToContainer(new Slot(buyShop, 41, 8 + 6 * 18, 18 + 5 * 18));
		addSlotToContainer(new Slot(buyShop, 42, 8 + 7 * 18, 18 + 5 * 18));
		addSlotToContainer(new Slot(buyShop, 43, 8 + 8 * 18, 18 + 5 * 18));

//		for (int i = 3; i < 6; i++) {
//			for (int j = 0; j < 9; j++) {
//				if (j == 3) {
//				} else {
//					addSlotToContainer(new Slot(buyShop, j + i * 9, 8 + j * 18, 18 + i * 18));
//				}
//			}
//		}

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
			if (slot < 44) {
				if (!this.mergeItemStack(stackInSlot, 54, this.inventorySlots.size(), true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if ( !this.mergeItemStack(stackInSlot, 0, 54, false)) {
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

	
	@Override
	/**
     * merges provided ItemStack with the first avaliable one in the container/player inventory
     */
    protected boolean mergeItemStack(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_)
    {
        boolean flag1 = false;
        int k = p_75135_2_;

        if (p_75135_4_)
        {
            k = p_75135_3_ - 1;
        }

        Slot slot;
        ItemStack itemstack1;

        if (p_75135_1_.isStackable())
        {
            while (p_75135_1_.stackSize > 0 && (!p_75135_4_ && k < p_75135_3_ || p_75135_4_ && k >= p_75135_2_))
            {
                slot = (Slot)this.inventorySlots.get(k);
                if ( p_75135_1_.getItem() instanceof ItemEcoShopUpgrade){
                	// its an upgrade so slots 18  & 19 are ok
                }else{
                    if (k ==18 || k ==19){
                    	 if (p_75135_4_)
                         {
                             --k;
                         }
                         else
                         {
                             ++k;
                         }
	                	continue;
	                	//keep on keeping on if it is slot 18 or 19 because those are the upgrade slots
	                }
                }
                	
                itemstack1 = slot.getStack();

                if (itemstack1 != null && itemstack1.getItem() == p_75135_1_.getItem() && (!p_75135_1_.getHasSubtypes() || p_75135_1_.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(p_75135_1_, itemstack1))
                {
                    int l = itemstack1.stackSize + p_75135_1_.stackSize;

                    if (l <= p_75135_1_.getMaxStackSize())
                    {
                        p_75135_1_.stackSize = 0;
                        itemstack1.stackSize = l;
                        slot.onSlotChanged();
                        flag1 = true;
                    }
                    else if (itemstack1.stackSize < p_75135_1_.getMaxStackSize())
                    {
                        p_75135_1_.stackSize -= p_75135_1_.getMaxStackSize() - itemstack1.stackSize;
                        itemstack1.stackSize = p_75135_1_.getMaxStackSize();
                        slot.onSlotChanged();
                        flag1 = true;
                    }
                }

                if (p_75135_4_)
                {
                    --k;
                }
                else
                {
                    ++k;
                }
            }
        }

        if (p_75135_1_.stackSize > 0)
        {
            if (p_75135_4_)
            {
                k = p_75135_3_ - 1;
            }
            else
            {
                k = p_75135_2_;
            }

            while (!p_75135_4_ && k < p_75135_3_ || p_75135_4_ && k >= p_75135_2_)
            {
                slot = (Slot)this.inventorySlots.get(k);
                itemstack1 = slot.getStack();
                if ( p_75135_1_.getItem() instanceof ItemEcoShopUpgrade){
                	// its an upgrade so slots 18  & 19 are ok
                }else{
                    if (k ==18 || k ==19){
                    	 if (p_75135_4_)
                         {
                             --k;
                         }
                         else
                         {
                             ++k;
                         }
	                	continue;
	                	//keep on keeping on if it is slot 18 or 19 because those are the upgrade slots
	                }
                }
                if (itemstack1 == null)
                {
                    slot.putStack(p_75135_1_.copy());
                    slot.onSlotChanged();
                    p_75135_1_.stackSize = 0;
                    flag1 = true;
                    break;
                }

                if (p_75135_4_)
                {
                    --k;
                }
                else
                {
                    ++k;
                }
            }
        }

        return flag1;
    }
}
