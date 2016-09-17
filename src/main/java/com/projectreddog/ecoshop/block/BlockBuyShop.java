package com.projectreddog.ecoshop.block;

import com.projectreddog.ecoshop.EcoShop;
import com.projectreddog.ecoshop.init.ModNetwork;
import com.projectreddog.ecoshop.network.EcoShopStoreOwnerRequestToServer;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBuyShop extends BlockContainerEcoShop {
	public BlockBuyShop() {
		super();
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
		if (te != null) {
			if (te instanceof TileEntityBuyShop) {
				// playerIn.openGui(EcoShop.instance, Reference.GUI_BLOCK_BUY_SHOP, worldIn, x, y, z);
				if (!worldIn.isRemote) {
					// only set it on the server side. client will get it in the gui
					if (((TileEntityBuyShop) te).getOwner() == null) {
						((TileEntityBuyShop) te).setOwner(MinecraftServer.getServer().func_152358_ax().func_152655_a(playerIn.getDisplayName()).getId());
						//// GameProfile profile = MinecraftServer.getServer().getPlayerProfileCache().func_152655_a(username).getId();
						// set the blocks owner!
					}
				} else {
					// client side so request the owner info
					ModNetwork.simpleNetworkWrapper.sendToServer((new EcoShopStoreOwnerRequestToServer(x, y, z)));

				}
			}

		}
		if (te != null && !playerIn.isSneaking()) {
			if (te instanceof TileEntityBuyShop) {
				playerIn.openGui(EcoShop.instance, Reference.GUI_BLOCK_BUY_SHOP, worldIn, x, y, z);
			}

			return true;
		} else if (te != null && playerIn.isSneaking()) {
			if (te instanceof TileEntityBuyShop) {
				if (!worldIn.isRemote) {
					// only set it on the server side. client will get it in the gui
					if (((TileEntityBuyShop) te).getOwner() == null) {
						playerIn.openGui(EcoShop.instance, Reference.GUI_BLOCK_BUY_SHOP_OWNER, worldIn, x, y, z);
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null) {
			if (te instanceof TileEntityBuyShop) {
				TileEntityBuyShop shop = (TileEntityBuyShop) te;
				if (shop.getOwner() == null) {
					// owner is null so allow
					super.getPlayerRelativeBlockHardness(player, world, x, y, z);
				} else {
					// not null owner
					if (shop.getOwner().getLeastSignificantBits() == player.getUniqueID().getLeastSignificantBits() && shop.getOwner().getMostSignificantBits() == player.getUniqueID().getMostSignificantBits()) {
						// this is the owner so set hardness to a valid value

						return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
					}
				}

			}
		}
		return 0.0f;
	}

	@Override
	public void breakBlock(World worldIn, int x, int y, int z, Block block, int meta) {
		TileEntity tileentity = worldIn.getTileEntity(x, y, z);

		if (tileentity instanceof IInventory) {
			dropInventoryItems(worldIn, x, y, z, (IInventory) tileentity);
		}

		super.breakBlock(worldIn, x, y, z, block, meta);
	}

	private static void dropInventoryItems(World worldIn, double x, double y, double z, IInventory inventory) {
		for (int i = 0; i < inventory.getSizeInventory(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);

			if (itemstack != null) {
				EntityItem entityitem = new EntityItem(worldIn, x, y, z, itemstack);

				worldIn.spawnEntityInWorld(entityitem);
			}
		}
	}

}
