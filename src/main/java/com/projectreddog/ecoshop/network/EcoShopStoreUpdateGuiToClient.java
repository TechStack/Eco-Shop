package com.projectreddog.ecoshop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class EcoShopStoreUpdateGuiToClient implements IMessage {

	public int buttonID;
	public int x;
	public int y;
	public int z;
	public long least;
	public long most;
	public boolean isOwner;
	public String ownerName;
	public int mode;
	public int creditAmount;
	public int itemsOnHand;
	public int creditsOnhand;

	public EcoShopStoreUpdateGuiToClient() {

	}

	public EcoShopStoreUpdateGuiToClient(int x, int y, int z, int mode, int creditAmount, int itemsOnHand, int creditsOnhand) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.mode = mode;
		this.creditAmount = creditAmount;
		this.itemsOnHand = itemsOnHand;
		this.creditsOnhand = creditsOnhand;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.mode = buf.readInt();
		this.creditAmount = buf.readInt();
		this.itemsOnHand = buf.readInt();
		this.creditsOnhand = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(mode);
		buf.writeInt(creditAmount);
		buf.writeInt(itemsOnHand);
		buf.writeInt(creditsOnhand);
	}

}
