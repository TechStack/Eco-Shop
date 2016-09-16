package com.projectreddog.ecoshop.inventory;

import java.util.UUID;

import com.projectreddog.ecoshop.item.ItemEcoShopUpgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class SlotUpgradeOnly extends Slot {
	UUID owner;

	public SlotUpgradeOnly(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, UUID owner) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		this.owner = owner;

	}

	@Override
	public boolean isItemValid(ItemStack itemStack) {
		if (itemStack.getItem() instanceof ItemEcoShopUpgrade) {
			return true;
		} else {
			return false;
		}
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
			if (owner.getLeastSignificantBits() == MinecraftServer.getServer().func_152358_ax().func_152655_a(player.getDisplayName()).getId().getLeastSignificantBits() && owner.getMostSignificantBits() == MinecraftServer.getServer().func_152358_ax().func_152655_a(player.getDisplayName()).getId().getMostSignificantBits()) {
				return true;
			} else {
				return false;
			}
		}

	}

}
