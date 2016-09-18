package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.creativetab.CreativeTabEcoShop;
import com.projectreddog.ecoshop.reference.Reference;

public class ItemCreditTenThousand extends ItemCredit {
	public ItemCreditTenThousand() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_TENTHOUSAND);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_TENTHOUSAND);
		this.setCreativeTab(CreativeTabEcoShop.ECOSHOP_CREATIVE_TAB);

	}

	@Override
	public int GetValue() {
		return 10000;
	}
}
