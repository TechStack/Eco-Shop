package com.projectreddog.ecoshop.block;

import com.projectreddog.ecoshop.EcoShop;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBuyShop extends BlockContainerEcoShop {

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

		// NEED TO return the TE here
		return new TileEntityBuyShop();
	}

	// public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)

	@Override
	public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(x, y, z);
		if (te != null && !playerIn.isSneaking()) {
			if (te instanceof TileEntityBuyShop) {
				playerIn.openGui(EcoShop.instance, Reference.GUI_BLOCK_BUY_SHOP, worldIn, x, y, z);
			}

			return true;
		} else {
			return false;
		}
	}

}
