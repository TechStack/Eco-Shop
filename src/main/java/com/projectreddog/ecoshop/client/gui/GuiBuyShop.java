package com.projectreddog.ecoshop.client.gui;

import org.lwjgl.opengl.GL11;

import com.projectreddog.ecoshop.container.ContainerBuyShop;
import com.projectreddog.ecoshop.init.ModNetwork;
import com.projectreddog.ecoshop.network.EcoShopStoreButtonClickToServer;
import com.projectreddog.ecoshop.network.EcoShopStoreOwnerRequestToServer;
import com.projectreddog.ecoshop.reference.Reference;
import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBuyShop extends GuiContainer {
	private TileEntityBuyShop buyShop;
	private GuiButton buttonBuySell;
	private GuiButton buttonPlus;
	private GuiButton buttonMinus;
	private GuiButton buttonPlus10;
	private GuiButton buttonMinus10;
	private GuiButton buttonPlus100;
	private GuiButton buttonMinus100;

	// private EntityPlayer player;
	private String OperationText = "Selling";
	private static String SELLING = "Selling";
	private static String BUYING = "Buying";
	private int CreditAmount = 0;

	@Override
	protected void actionPerformed(GuiButton btn) {
		// TODO Auto-generated method stub
		super.actionPerformed(btn);

		//
		// TODO need to send packet to the server!
		ModNetwork.simpleNetworkWrapper.sendToServer((new EcoShopStoreButtonClickToServer(btn.id, buyShop.xCoord, buyShop.yCoord, buyShop.zCoord)));

		//
		// if (btn.id == Reference.GUI_BUTTON_ID_MINUS) {
		// CreditAmount--;
		// }
		// if (btn.id == Reference.GUI_BUTTON_ID_PLUS) {
		// CreditAmount++;
		// }
		// if (btn.id == Reference.GUI_BUTTON_ID_MINUS10) {
		// CreditAmount = CreditAmount - 10;
		// }
		// if (btn.id == Reference.GUI_BUTTON_ID_PLUS10) {
		// CreditAmount = CreditAmount + 10;
		// }
		//
		// if (btn.id == Reference.GUI_BUTTON_ID_MINUS100) {
		// CreditAmount = CreditAmount - 100;
		// }
		// if (btn.id == Reference.GUI_BUTTON_ID_PLUS100) {
		// CreditAmount = CreditAmount + 100;
		// }
		//
		// if (CreditAmount < 0) {
		// CreditAmount = 0;
		// }

	}

	public GuiBuyShop(InventoryPlayer inventoryPlayer, TileEntityBuyShop buyShop) {
		// the container is instanciated and passed to the superclass for
		// handling

		super(new ContainerBuyShop(inventoryPlayer, buyShop));
		// player = inventoryPlayer.player;
		this.buyShop = buyShop;

	}

	@Override
	public void initGui() {

		// this.buttonBuySell.enabled = false;
		this.xSize = 176;
		this.ySize = 222;
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		buttonBuySell = new GuiButton(Reference.GUI_BUTTON_ID_BUY_SELL, x + 77, y + 7, 36, 20, BUYING);
		this.buttonList.add(buttonBuySell);

		int buttonHeight = 20;
		int buttonWidth = 12;
		int topOffset = 26;
		buttonMinus100 = new GuiButton(Reference.GUI_BUTTON_ID_MINUS100, x + 77, y + topOffset, buttonWidth, buttonHeight, "<<");
		buttonMinus10 = new GuiButton(Reference.GUI_BUTTON_ID_MINUS10, x + 77 + buttonWidth, y + topOffset, buttonWidth, buttonHeight, "<");
		buttonMinus = new GuiButton(Reference.GUI_BUTTON_ID_MINUS, x + 77 + buttonWidth * 2, y + topOffset, buttonWidth, buttonHeight, "-");
		buttonPlus = new GuiButton(Reference.GUI_BUTTON_ID_PLUS, x + 77 + buttonWidth * 3, y + topOffset, buttonWidth, buttonHeight, "+");
		buttonPlus10 = new GuiButton(Reference.GUI_BUTTON_ID_PLUS10, x + 77 + buttonWidth * 4, y + topOffset, buttonWidth, buttonHeight, ">");
		buttonPlus100 = new GuiButton(Reference.GUI_BUTTON_ID_PLUS100, x + 77 + buttonWidth * 5, y + topOffset, buttonWidth, buttonHeight, ">>");
		this.buttonList.add(buttonMinus100);
		this.buttonList.add(buttonMinus10);
		this.buttonList.add(buttonMinus);
		this.buttonList.add(buttonPlus);
		this.buttonList.add(buttonPlus10);
		this.buttonList.add(buttonPlus100);
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
		fontRendererObj.drawString("This store is:", 9, 12, 4210752);

		fontRendererObj.drawString("For: " + buyShop.getCreditAmount(), 9, 30, 4210752);
		if (buyShop.GetOwnerName() == "") {
			ModNetwork.simpleNetworkWrapper.sendToServer((new EcoShopStoreOwnerRequestToServer(buyShop.xCoord, buyShop.yCoord, buyShop.zCoord)));
			// TODO possibly add a delay or max number of tries before giving up to avoid flooding the server with request before it can respond
		}
		fontRendererObj.drawString("Shop Owner: " + buyShop.GetOwnerName(), 9, 48, 4210752);

		fontRendererObj.drawString("We have " + buyShop.getIOH() + " in stock.", 9, 58, 4210752);

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

	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		super.updateScreen();

		if (buyShop.getMode() == Reference.STORE_BLOCK_MODE_BUY) {
			this.buttonBuySell.displayString = BUYING;
		} else {
			this.buttonBuySell.displayString = SELLING;
		}

	}

}
