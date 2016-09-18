package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.creativetab.CreativeTabEcoShop;
import com.projectreddog.ecoshop.reference.Reference;

public class ItemCreditFive extends ItemCredit {
	public ItemCreditFive() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVE);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVE);
		this.setCreativeTab(CreativeTabEcoShop.ECOSHOP_CREATIVE_TAB);

	}

	@Override
	public int GetValue() {
		return 5;
	}
}
