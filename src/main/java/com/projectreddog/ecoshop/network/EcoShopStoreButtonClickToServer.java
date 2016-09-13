package com.projectreddog.ecoshop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class EcoShopStoreButtonClickToServer implements IMessage {

	public int buttonID;
	public int x;
	public int y;
	public int z;

	public EcoShopStoreButtonClickToServer() {

	}

	public EcoShopStoreButtonClickToServer(int buttonID, int x, int y, int z) {
		super();
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.buttonID = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(buttonID);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);

	}

}
