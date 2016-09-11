package com.projectreddog.ecoshop.init;

import com.projectreddog.ecoshop.block.BlockBuyShop;
import com.projectreddog.ecoshop.block.BlockSellShop;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static final BlockSellShop SELLSHOP = new BlockSellShop();
	public static final BlockBuyShop BUYSHOP = new BlockBuyShop();

	public static void init() {
		GameRegistry.registerBlock(SELLSHOP, Reference.BLOCK_SELLSHOP);
		GameRegistry.registerBlock(BUYSHOP, Reference.BLOCK_BUYSHOP);

		GameRegistry.registerTileEntity(TileEntityBuyShop.class, Reference.BLOCK_BUYSHOP);

	}
}
