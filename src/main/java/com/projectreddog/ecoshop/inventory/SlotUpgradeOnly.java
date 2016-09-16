package com.projectreddog.ecoshop.inventory;

import com.projectreddog.ecoshop.item.ItemEcoShopUpgrade;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUpgradeOnly extends Slot {
	public SlotUpgradeOnly(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	@Override
	public boolean isItemValid(ItemStack itemStack) {
		if (itemStack.getItem() instanceof ItemEcoShopUpgrade) {
			return true;
		} else {
			return false;
		}
	}
}
