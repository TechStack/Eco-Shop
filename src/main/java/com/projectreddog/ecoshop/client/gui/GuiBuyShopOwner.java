package com.projectreddog.ecoshop.client.gui;

import org.lwjgl.opengl.GL11;

import com.projectreddog.ecoshop.container.ContainerBuyShopOwner;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBuyShopOwner extends GuiContainer {
	private TileEntityBuyShop buyShop;
	// private GuiButton gb;

	@Override
	protected void actionPerformed(GuiButton p_146284_1_) {
		super.actionPerformed(p_146284_1_);

	}

	public GuiBuyShopOwner(InventoryPlayer inventoryPlayer, TileEntityBuyShop buyShop) {
		// the container is instanciated and passed to the superclass for
		// handling

		super(new ContainerBuyShopOwner(inventoryPlayer, buyShop));
		this.buyShop = buyShop;
	}

	@Override
	public void initGui() {
		// gb = new GuiButton(1, 134, 45, 34, 16, "Inv");
		// this.buttonList.add(gb);
		this.xSize = 176;
		this.ySize = 222;
		super.initGui();

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		// fontRenderer.drawString("Tiny", 8, 6, 4210752);
		// //draws "Inventory" or your regional equivalent
		// fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"),
		// 8, ySize - 96 + 2, 4210752);
		// fontRendererObj.drawString("Fuel: " + canner.fuelStorage, 5, 5, 4210752);

		// TODO need to Draw owner name !

		fontRendererObj.drawString("In:", 28, 48, 4210752);
		fontRendererObj.drawString("Stored: " + 1000, 5, 65, 4210752);
		fontRendererObj.drawString("Out:", 22, 87, 4210752);

		fontRendererObj.drawString("In:", 100, 48, 4210752);
		fontRendererObj.drawString("Credits: " + 1000, 75, 65, 4210752);
		fontRendererObj.drawString("Out:", 94, 87, 4210752);

		fontRendererObj.drawString("Items:", 35, 30, 4210752);
		fontRendererObj.drawString("Credits:", 110, 30, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MODID + ":" + "textures/gui/buyshopowner.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}