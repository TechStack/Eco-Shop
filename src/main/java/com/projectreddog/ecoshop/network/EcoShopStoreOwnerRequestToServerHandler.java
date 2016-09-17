package com.projectreddog.ecoshop.network;

import com.projectreddog.ecoshop.init.ModNetwork;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

public class EcoShopStoreOwnerRequestToServerHandler implements IMessageHandler<EcoShopStoreOwnerRequestToServer, IMessage> {
	@Override
	public IMessage onMessage(final EcoShopStoreOwnerRequestToServer message, final MessageContext ctx) {

		// ctx.getServerHandler().playerEntity.getServerForPlayer().addScheduledTask(new Runnable() {
		// public void run() {
		// processMessage(message, ctx);
		// }
		// });

		processMessage(message, ctx);
		return null;
	}

	public void processMessage(EcoShopStoreOwnerRequestToServer message, MessageContext ctx) {

		TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);

		if (te != null) {

			if (te instanceof TileEntityBuyShop) {
				if (((TileEntityBuyShop) te).getOwner() != null) {
					boolean isOnwer = false;
					if (((TileEntityBuyShop) te).getOwner().getLeastSignificantBits() == MinecraftServer.getServer().func_152358_ax().func_152655_a(ctx.getServerHandler().playerEntity.getDisplayName()).getId().getLeastSignificantBits()
							&& ((TileEntityBuyShop) te).getOwner().getMostSignificantBits() == MinecraftServer.getServer().func_152358_ax().func_152655_a(ctx.getServerHandler().playerEntity.getDisplayName()).getId().getMostSignificantBits()) {
						isOnwer = true;
					}
					ModNetwork.simpleNetworkWrapper.sendTo((new EcoShopStoreOwnerResponseToClient(message.x, message.y, message.z, ((TileEntityBuyShop) te).getOwner().getMostSignificantBits(), ((TileEntityBuyShop) te).getOwner().getLeastSignificantBits(), isOnwer)), ctx.getServerHandler().playerEntity);
				}

			}
		}
	}
}
