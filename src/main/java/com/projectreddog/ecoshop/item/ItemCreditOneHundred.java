package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditOneHundred extends ItemCredit {
	public ItemCreditOneHundred() {
		super();
		this.setUnlocalizedName(Reference.ITEM_CREDIT_ONEHUNDRED);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_ONEHUNDRED);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 100;
	}
}
