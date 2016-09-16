package com.projectreddog.ecoshop.inventory;

import java.util.UUID;

import com.projectreddog.ecoshop.tileentities.TileEntityBuyShop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.server.MinecraftServer;

public class SlotOwnerOnly extends Slot {
	UUID owner;
	TileEntityBuyShop buyShop;

	public SlotOwnerOnly(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, UUID owner, TileEntityBuyShop buyShop) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		this.owner = owner;
		this.buyShop = buyShop;
	}

	/**
	 * Return whether this slot's stack can be taken from this slot.
	 */
	public boolean canTakeStack(EntityPlayer player) {
		if (!player.worldObj.isRemote) {
			// server only !
			if (MinecraftServer.getServer().func_152358_ax().func_152655_a(player.getDisplayName()).getId().getLeastSignificantBits() == owner.getLeastSignificantBits() && MinecraftServer.getServer().func_152358_ax().func_152655_a(player.getDisplayName()).getId().getMostSignificantBits() == owner.getMostSignificantBits()) {
				return true;
			} else {
				return false;
			}

		} else {
			if (buyShop == null) {
				return false;
			}
			if (buyShop.getOwner() == null) {
				// no owner no way to get access bub!
				return false;
			}
			if (buyShop.getOwner().getLeastSignificantBits() == MinecraftServer.getServer().func_152358_ax().func_152655_a(player.getDisplayName()).getId().getLeastSignificantBits() && buyShop.getOwner().getMostSignificantBits() == MinecraftServer.getServer().func_152358_ax().func_152655_a(player.getDisplayName()).getId().getMostSignificantBits()) {
				return true;
			} else {
				return false;
			}
		}

	}

}
