package com.projectreddog.ecoshop.creativetab;

import com.projectreddog.ecoshop.init.ModItems;
import com.projectreddog.ecoshop.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabEcoShop {

	public static final CreativeTabs ECOSHOP_CREATIVE_TAB = new CreativeTabs(Reference.MODID) {

		@Override
		public Item getTabIconItem() {

			return ModItems.CREDIT_TENTHOUSAND;
		}

		@Override
		public String getTranslatedTabLabel() {
			return "EcoShop";
		}
	};
}
