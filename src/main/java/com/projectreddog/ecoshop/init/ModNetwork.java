package com.projectreddog.ecoshop.init;

import java.util.List;

import com.projectreddog.ecoshop.EcoShop;
import com.projectreddog.ecoshop.client.gui.GuiHandler;
import com.projectreddog.ecoshop.network.EcoShopStoreButtonClickToServer;
import com.projectreddog.ecoshop.network.EcoShopStoreButtonClickToServerHandler;
import com.projectreddog.ecoshop.reference.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;

public class ModNetwork {

	public static SimpleNetworkWrapper simpleNetworkWrapper;

	public static void init() {
		simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		NetworkRegistry.INSTANCE.registerGuiHandler(EcoShop.instance, new GuiHandler());
		simpleNetworkWrapper.registerMessage(EcoShopStoreButtonClickToServerHandler.class, EcoShopStoreButtonClickToServer.class, 0, Side.SERVER);// message

	}

	public static void sendPacketToAllAround(IMessage packet, TargetPoint tp) {
		for (EntityPlayerMP player : (List<EntityPlayerMP>) FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList) {
			if (player.dimension == tp.dimension) {
				double d4 = tp.x - player.posX;
				double d6 = tp.z - player.posZ;

				// base distance only on the x & Z axis so you can see machines way above / below you. (blast a machine up and you'll understand why
				if (d4 * d4 + d6 * d6 < tp.range * tp.range) {

					ModNetwork.simpleNetworkWrapper.sendTo(packet, player);

				}
			}
		}
	}
}
