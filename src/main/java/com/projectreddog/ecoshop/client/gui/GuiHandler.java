package com.projectreddog.ecoshop.client.gui;

import com.projectreddog.ecoshop.reference.Reference;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub

		if (ID == Reference.GUI_BLOCK_SELL_SHOP) {

		} else if (ID == Reference.GUI_BLOCK_BUY_SHOP) {

		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		if (ID == Reference.GUI_BLOCK_SELL_SHOP) {

		} else if (ID == Reference.GUI_BLOCK_BUY_SHOP) {

		}
		return null;
	}

}
