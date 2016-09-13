package com.projectreddog.ecoshop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class EcoShopStoreOwnerRequestToServer implements IMessage {

	public int buttonID;
	public int x;
	public int y;
	public int z;

	public EcoShopStoreOwnerRequestToServer() {

	}

	public EcoShopStoreOwnerRequestToServer(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);

	}

}
