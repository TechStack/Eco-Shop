package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.creativetab.CreativeTabEcoShop;
import com.projectreddog.ecoshop.reference.Reference;

public class ItemCreditOneHundred extends ItemCredit {
	public ItemCreditOneHundred() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_ONEHUNDRED);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_ONEHUNDRED);
		this.setCreativeTab(CreativeTabEcoShop.ECOSHOP_CREATIVE_TAB);

	}

	@Override
	public int GetValue() {
		return 100;
	}
}
