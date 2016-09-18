package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.creativetab.CreativeTabEcoShop;
import com.projectreddog.ecoshop.reference.Reference;

public class ItemCreditTwenty extends ItemCredit {

	public ItemCreditTwenty() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_CREDIT_TWENTY);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_CREDIT_TWENTY);
		this.setCreativeTab(CreativeTabEcoShop.ECOSHOP_CREATIVE_TAB);

	}

	@Override
	public int GetValue() {
		return 20;
	}

}
