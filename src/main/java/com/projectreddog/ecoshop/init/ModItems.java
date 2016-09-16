package com.projectreddog.ecoshop.init;

import com.projectreddog.ecoshop.item.ItemCheck;
import com.projectreddog.ecoshop.item.ItemCreditFive;
import com.projectreddog.ecoshop.item.ItemCreditFiveHundred;
import com.projectreddog.ecoshop.item.ItemCreditFiveThousand;
import com.projectreddog.ecoshop.item.ItemCreditOne;
import com.projectreddog.ecoshop.item.ItemCreditOneHundred;
import com.projectreddog.ecoshop.item.ItemCreditOneThousand;
import com.projectreddog.ecoshop.item.ItemCreditTen;
import com.projectreddog.ecoshop.item.ItemCreditTenThousand;
import com.projectreddog.ecoshop.item.ItemCreditTwenty;
import com.projectreddog.ecoshop.item.ItemUnlimitedInventory;
import com.projectreddog.ecoshop.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	public static final ItemCheck CHECK = new ItemCheck();
	public static final ItemCreditOne CREDIT_ONE = new ItemCreditOne();
	public static final ItemCreditFive CREDIT_FIVE = new ItemCreditFive();
	public static final ItemCreditTen CREDIT_TEN = new ItemCreditTen();
	public static final ItemCreditTwenty CREDIT_TWENTY = new ItemCreditTwenty();
	public static final ItemCreditOneHundred CREDIT_ONEHUNDRED = new ItemCreditOneHundred();
	public static final ItemCreditFiveHundred CREDIT_FIVEHUNDRED = new ItemCreditFiveHundred();
	public static final ItemCreditOneThousand CREDIT_ONETHOUSAND = new ItemCreditOneThousand();
	public static final ItemCreditFiveThousand CREDIT_FIVETHOUSAND = new ItemCreditFiveThousand();
	public static final ItemCreditTenThousand CREDIT_TENTHOUSAND = new ItemCreditTenThousand();

	public static final ItemUnlimitedInventory ITEM_UPGRADE_UNLIMITED_INVENTORY = new ItemUnlimitedInventory();

	public static void init() {
		GameRegistry.registerItem(CHECK, Reference.ITEM_CHECK);

		GameRegistry.registerItem(CREDIT_ONE, Reference.ITEM_CREDIT_ONE);
		GameRegistry.registerItem(CREDIT_FIVE, Reference.ITEM_CREDIT_FIVE);
		GameRegistry.registerItem(CREDIT_TEN, Reference.ITEM_CREDIT_TEN);
		GameRegistry.registerItem(CREDIT_TWENTY, Reference.ITEM_CREDIT_TWENTY);
		GameRegistry.registerItem(CREDIT_ONEHUNDRED, Reference.ITEM_CREDIT_ONEHUNDRED);
		GameRegistry.registerItem(CREDIT_FIVEHUNDRED, Reference.ITEM_CREDIT_FIVEHUNDRED);
		GameRegistry.registerItem(CREDIT_ONETHOUSAND, Reference.ITEM_CREDIT_ONETHOUSAND);
		GameRegistry.registerItem(CREDIT_FIVETHOUSAND, Reference.ITEM_CREDIT_FIVETHOUSAND);
		GameRegistry.registerItem(CREDIT_TENTHOUSAND, Reference.ITEM_CREDIT_TENTHOUSAND);

		GameRegistry.registerItem(ITEM_UPGRADE_UNLIMITED_INVENTORY, Reference.ITEM_UPGRADE_UNLIMITED_INVENTORY);
	}

}
