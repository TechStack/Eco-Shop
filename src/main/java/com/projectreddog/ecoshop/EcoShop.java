package com.projectreddog.ecoshop;

import com.projectreddog.ecoshop.init.ModBlocks;
import com.projectreddog.ecoshop.init.ModItems;
import com.projectreddog.ecoshop.reference.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = EcoShop.MODID, version = EcoShop.VERSION)
public class EcoShop
{
    public static final String MODID = Reference.MODID;
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    	ModItems.init();
		ModBlocks.init();
        
    }
}