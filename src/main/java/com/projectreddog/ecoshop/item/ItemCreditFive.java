package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditFive extends ItemCredit {
	public ItemCreditFive() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVE);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVE);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 5;
	}
}
