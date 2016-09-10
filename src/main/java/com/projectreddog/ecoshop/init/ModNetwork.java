package com.projectreddog.ecoshop.init;

import com.projectreddog.ecoshop.EcoShop;
import com.projectreddog.ecoshop.client.gui.GuiHandler;

import cpw.mods.fml.common.network.NetworkRegistry;

public class ModNetwork {

	public void Init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(EcoShop.instance, new GuiHandler());

	}

}
