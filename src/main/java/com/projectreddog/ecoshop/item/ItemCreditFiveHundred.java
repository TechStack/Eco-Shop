package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditFiveHundred extends ItemCredit {
	public ItemCreditFiveHundred() {
		super();
		this.setUnlocalizedName(Reference.ITEM_CREDIT_FIVEHUNDRED);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVEHUNDRED);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 500;
	}
}
