package com.projectreddog.ecoshop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class EcoShopStoreOwnerResponseToClient implements IMessage {

	public int buttonID;
	public int x;
	public int y;
	public int z;
	public long least;
	public long most;

	public EcoShopStoreOwnerResponseToClient() {

	}

	public EcoShopStoreOwnerResponseToClient(int x, int y, int z, long most, long least) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.most = most;
		this.least = least;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.most = buf.readLong();
		this.least = buf.readLong();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeLong(most);
		buf.writeLong(least);
	}

}
