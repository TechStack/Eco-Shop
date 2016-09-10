package com.projectreddog.ecoshop.block;

import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBuyShop extends BlockEcoShop {

	public BlockBuyShop() {
		super();
		// TODO Auto-generated constructor stub
		this.setBlockName(Reference.BLOCK_BUYSHOP);
		this.setBlockTextureName(Reference.MODID + ":" + Reference.BLOCK_BUYSHOP);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
	}

	@Override
	public TileEntity createTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here\
		return new TileEntityBuyShop();
	}

}
