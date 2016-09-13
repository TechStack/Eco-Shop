package com.projectreddog.ecoshop.network;

import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.tileentity.TileEntity;

public class EcoShopStoreButtonClickToServerHandler implements IMessageHandler<EcoShopStoreButtonClickToServer, IMessage> {
	@Override
	public IMessage onMessage(final EcoShopStoreButtonClickToServer message, final MessageContext ctx) {

		// ctx.getServerHandler().playerEntity.getServerForPlayer().addScheduledTask(new Runnable() {
		// public void run() {
		// processMessage(message, ctx);
		// }
		// });

		processMessage(message, ctx);
		return null;
	}

	public void processMessage(EcoShopStoreButtonClickToServer message, MessageContext ctx) {

		TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);

		if (te != null) {

			if (te instanceof TileEntityBuyShop) {
				((TileEntityBuyShop) te).processesButton(message.buttonID);

			}
		}
	}
}
