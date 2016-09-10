package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditOneThousand extends ItemCredit {
	public ItemCreditOneThousand() {
		super();
		this.setUnlocalizedName(Reference.ITEM_CREDIT_ONETHOUSAND);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_ONETHOUSAND);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 1000;
	}
}
