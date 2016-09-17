package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditFiveThousand extends ItemCredit {
	public ItemCreditFiveThousand() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVETHOUSAND);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVETHOUSAND);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 5000;
	}
}
