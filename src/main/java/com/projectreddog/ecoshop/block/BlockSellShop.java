package com.projectreddog.ecoshop.block;

import com.projectreddog.ecoshop.reference.Reference;

public class BlockSellShop extends BlockContainerEcoShop {

	public BlockSellShop() {
		super();
		this.setBlockName(Reference.BLOCK_SELLSHOP);
		this.setBlockTextureName(Reference.MODID + ":" + Reference.BLOCK_SELLSHOP);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
	}

}
