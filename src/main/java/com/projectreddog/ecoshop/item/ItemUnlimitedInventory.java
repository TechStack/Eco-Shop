package com.projectreddog.ecoshop.item;

import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;

public class ItemUnlimitedInventory extends ItemEcoShopUpgrade {
	public ItemUnlimitedInventory() {
		super();
		this.setUnlocalizedName(Reference.MODID + ":" + Reference.ITEM_UPGRADE_UNLIMITED_INVENTORY);
		this.setTextureName(Reference.MODID + ":" + Reference.ITEM_UPGRADE_UNLIMITED_INVENTORY);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxStackSize(1);

	}

}
