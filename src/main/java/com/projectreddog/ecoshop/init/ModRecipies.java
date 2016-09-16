package com.projectreddog.ecoshop.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class ModRecipies {

	public static void init() {

		// Convert up
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_FIVE), new Object[] { ModItems.CREDIT_ONE, ModItems.CREDIT_ONE, ModItems.CREDIT_ONE, ModItems.CREDIT_ONE, ModItems.CREDIT_ONE });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_TEN), new Object[] { ModItems.CREDIT_FIVE, ModItems.CREDIT_FIVE });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_TWENTY), new Object[] { ModItems.CREDIT_TEN, ModItems.CREDIT_TEN });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_TWENTY), new Object[] { ModItems.CREDIT_FIVE, ModItems.CREDIT_FIVE, ModItems.CREDIT_FIVE, ModItems.CREDIT_FIVE });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_ONEHUNDRED), new Object[] { ModItems.CREDIT_TWENTY, ModItems.CREDIT_TWENTY, ModItems.CREDIT_TWENTY, ModItems.CREDIT_TWENTY, ModItems.CREDIT_TWENTY });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_FIVEHUNDRED), new Object[] { ModItems.CREDIT_ONEHUNDRED, ModItems.CREDIT_ONEHUNDRED, ModItems.CREDIT_ONEHUNDRED, ModItems.CREDIT_ONEHUNDRED, ModItems.CREDIT_ONEHUNDRED });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_ONETHOUSAND), new Object[] { ModItems.CREDIT_FIVEHUNDRED, ModItems.CREDIT_FIVEHUNDRED });

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_FIVETHOUSAND), new Object[] { ModItems.CREDIT_ONETHOUSAND, ModItems.CREDIT_ONETHOUSAND, ModItems.CREDIT_ONETHOUSAND, ModItems.CREDIT_ONETHOUSAND, ModItems.CREDIT_ONETHOUSAND });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_TENTHOUSAND), new Object[] { ModItems.CREDIT_FIVETHOUSAND, ModItems.CREDIT_FIVETHOUSAND });

		// convert down
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_ONE, 5), new Object[] { ModItems.CREDIT_FIVE });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_FIVE, 2), new Object[] { ModItems.CREDIT_TEN });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_TEN, 2), new Object[] { ModItems.CREDIT_TWENTY });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_TWENTY, 5), new Object[] { ModItems.CREDIT_ONEHUNDRED });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_ONEHUNDRED, 5), new Object[] { ModItems.CREDIT_FIVEHUNDRED });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_FIVEHUNDRED, 2), new Object[] { ModItems.CREDIT_ONETHOUSAND });

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_ONETHOUSAND, 5), new Object[] { ModItems.CREDIT_FIVETHOUSAND });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.CREDIT_FIVETHOUSAND, 2), new Object[] { ModItems.CREDIT_TENTHOUSAND });

	}

}
