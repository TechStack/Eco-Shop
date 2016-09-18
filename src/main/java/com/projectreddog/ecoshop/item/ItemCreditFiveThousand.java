package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.creativetab.CreativeTabEcoShop;
import com.projectreddog.ecoshop.reference.Reference;

public class ItemCreditFiveThousand extends ItemCredit {
	public ItemCreditFiveThousand() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVETHOUSAND);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_FIVETHOUSAND);
		this.setCreativeTab(CreativeTabEcoShop.ECOSHOP_CREATIVE_TAB);

	}

	@Override
	public int GetValue() {
		return 5000;
	}
}
