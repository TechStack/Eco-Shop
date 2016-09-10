package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditTen extends ItemCredit {
	public ItemCreditTen() {
		super();
		this.setUnlocalizedName(Reference.ITEM_CREDIT_TEN);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_TEN);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 10;
	}
}
