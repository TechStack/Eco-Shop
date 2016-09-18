package com.projectreddog.ecoshop.network;

import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class EcoShopStoreUpdateGuiToClientHandler implements IMessageHandler<EcoShopStoreUpdateGuiToClient, IMessage> {
	@Override
	public IMessage onMessage(final EcoShopStoreUpdateGuiToClient message, final MessageContext ctx) {

		// ctx.getServerHandler().playerEntity.getServerForPlayer().addScheduledTask(new Runnable() {
		// public void run() {
		// processMessage(message, ctx);
		// }
		// });

		processMessage(message, ctx);
		return null;
	}

	public void processMessage(EcoShopStoreUpdateGuiToClient message, MessageContext ctx) {

		if (message != null) {
			if (Minecraft.getMinecraft().theWorld != null) {
				TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
				if (te != null) {

					if (te instanceof TileEntityBuyShop) {
						TileEntityBuyShop buyShop = ((TileEntityBuyShop) te);
						buyShop.setMode(message.mode);
						buyShop.setCreditAmount(message.creditAmount);
						buyShop.setItemsOnHand(message.itemsOnHand);
						buyShop.setCreditsOnHand(message.creditsOnhand);

					}
				}
			}
		}
	}
}
