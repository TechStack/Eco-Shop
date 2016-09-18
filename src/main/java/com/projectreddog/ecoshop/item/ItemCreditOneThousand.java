package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.creativetab.CreativeTabEcoShop;
import com.projectreddog.ecoshop.reference.Reference;

public class ItemCreditOneThousand extends ItemCredit {
	public ItemCreditOneThousand() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_ONETHOUSAND);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_ONETHOUSAND);
		this.setCreativeTab(CreativeTabEcoShop.ECOSHOP_CREATIVE_TAB);

	}

	@Override
	public int GetValue() {
		return 1000;
	}
}
