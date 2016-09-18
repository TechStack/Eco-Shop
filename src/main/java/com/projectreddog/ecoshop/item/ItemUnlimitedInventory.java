package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.creativetab.CreativeTabEcoShop;
import com.projectreddog.ecoshop.reference.Reference;

public class ItemUnlimitedInventory extends ItemEcoShopUpgrade {
	public ItemUnlimitedInventory() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_UPGRADE_UNLIMITED_INVENTORY);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_UPGRADE_UNLIMITED_INVENTORY);
		this.setCreativeTab(CreativeTabEcoShop.ECOSHOP_CREATIVE_TAB);
		this.setMaxStackSize(1);

	}

}
