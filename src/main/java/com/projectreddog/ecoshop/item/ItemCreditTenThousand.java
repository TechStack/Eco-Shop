package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCreditTenThousand extends ItemCredit {
	public ItemCreditTenThousand() {
		super();
		this.setUnlocalizedName(Reference.ITEM_CREDIT_TENTHOUSAND);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_TENTHOUSAND);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public int GetValue() {
		return 10000;
	}
}
