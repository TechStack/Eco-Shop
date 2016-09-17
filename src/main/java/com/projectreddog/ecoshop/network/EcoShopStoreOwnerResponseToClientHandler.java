package com.projectreddog.ecoshop.network;

import java.util.UUID;

import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class EcoShopStoreOwnerResponseToClientHandler implements IMessageHandler<EcoShopStoreOwnerResponseToClient, IMessage> {
	@Override
	public IMessage onMessage(final EcoShopStoreOwnerResponseToClient message, final MessageContext ctx) {

		// ctx.getServerHandler().playerEntity.getServerForPlayer().addScheduledTask(new Runnable() {
		// public void run() {
		// processMessage(message, ctx);
		// }
		// });

		processMessage(message, ctx);
		return null;
	}

	public void processMessage(EcoShopStoreOwnerResponseToClient message, MessageContext ctx) {

		if (message != null) {
			if (Minecraft.getMinecraft().theWorld != null) {
				TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
				if (te != null) {

					if (te instanceof TileEntityBuyShop) {
						UUID owner = new UUID(message.most, message.least);
						((TileEntityBuyShop) te).setOwner(owner);
						((TileEntityBuyShop) te).setIsOwner(message.isOwner);

					}
				}
			}
		}
	}
}
