package com.projectreddog.ecoshop;

import com.projectreddog.ecoshop.init.ModBlocks;
import com.projectreddog.ecoshop.init.ModItems;
import com.projectreddog.ecoshop.init.ModNetwork;
import com.projectreddog.ecoshop.init.ModRecipies;
import com.projectreddog.ecoshop.proxy.IProxy;
import com.projectreddog.ecoshop.reference.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EcoShop.MODID, version = EcoShop.VERSION, name = EcoShop.NAME)
public class EcoShop {
	public static final String MODID = Reference.MODID;
	public static final String VERSION = "1.0";
	public static final String NAME = Reference.MOD_NAME;
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.Instance(Reference.MODID)
	public static EcoShop instance; // an instance back to this mod

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ModItems.init();
		ModBlocks.init();
		ModNetwork.init();

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipies.init();
	}
}
