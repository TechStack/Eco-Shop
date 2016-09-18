package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.creativetab.CreativeTabEcoShop;
import com.projectreddog.ecoshop.reference.Reference;

public class ItemCreditFiveHundred extends ItemCredit {
	public ItemCreditFiveHundred() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVEHUNDRED);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVEHUNDRED);
		this.setCreativeTab(CreativeTabEcoShop.ECOSHOP_CREATIVE_TAB);

	}

	@Override
	public int GetValue() {
		return 500;
	}
}
