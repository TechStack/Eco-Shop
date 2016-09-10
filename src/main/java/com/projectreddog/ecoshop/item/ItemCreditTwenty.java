package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditTwenty extends ItemCredit {

	public ItemCreditTwenty() {
		super();
		this.setUnlocalizedName(Reference.ITEM_CREDIT_TWENTY);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_TWENTY);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 20;
	}

}
