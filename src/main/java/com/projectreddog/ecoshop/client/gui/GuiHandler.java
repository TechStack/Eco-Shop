package com.projectreddog.ecoshop.client.gui;

import com.projectreddog.ecoshop.container.ContainerBuyShop;
import com.projectreddog.ecoshop.container.ContainerBuyShopOwner;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub

		if (ID == Reference.GUI_BLOCK_BUY_SHOP_OWNER) {
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof TileEntityBuyShop) {

				return new ContainerBuyShopOwner(player.inventory, (TileEntityBuyShop) entity);
			}
		} else if (ID == Reference.GUI_BLOCK_BUY_SHOP) {
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof TileEntityBuyShop) {

				return new ContainerBuyShop(player.inventory, (TileEntityBuyShop) entity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		if (ID == Reference.GUI_BLOCK_BUY_SHOP_OWNER) {
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof TileEntityBuyShop) {

				return new GuiBuyShopOwner(player.inventory, (TileEntityBuyShop) entity);
			}
		} else if (ID == Reference.GUI_BLOCK_BUY_SHOP) {
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity != null) {
				if (entity instanceof TileEntityBuyShop) {
					return new GuiBuyShop(player.inventory, (TileEntityBuyShop) entity);
				}
			}
		}
		return null;
	}

}
