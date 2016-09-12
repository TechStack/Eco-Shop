package com.projectreddog.ecoshop.client.gui;

import org.lwjgl.opengl.GL11;

import com.projectreddog.ecoshop.container.ContainerBuyShop;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBuyShop extends GuiContainer {
	private TileEntityBuyShop buyShop;
	// private GuiButton gb;
	// private EntityPlayer player;

	// @Override
	// protected void actionPerformed(GuiButton p_146284_1_) {
	// // TODO Auto-generated method stub
	// super.actionPerformed(p_146284_1_);
	// player.openGui(Reference.MODID, Reference.GUI_BLOCK_BUY_SHOP_OWNER, player.worldObj, buyShop.xCoord, buyShop.yCoord, buyShop.zCoord);
	// }

	public GuiBuyShop(InventoryPlayer inventoryPlayer, TileEntityBuyShop buyShop) {
		// the container is instanciated and passed to the superclass for
		// handling

		super(new ContainerBuyShop(inventoryPlayer, buyShop));
		// player = inventoryPlayer.player;
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
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MODID + ":" + "textures/gui/shopgui.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}