package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditOne extends ItemCredit {
	public ItemCreditOne() {
		super();
		this.setUnlocalizedName(Reference.ITEM_CREDIT_ONE);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_ONE);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 1;
	}
}
