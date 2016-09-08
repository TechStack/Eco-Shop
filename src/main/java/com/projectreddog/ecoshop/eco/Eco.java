package com.projectreddog.ecoshop.eco;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

public class Eco {

	private HashMap<UUID, Float> PlayerAmounts;

	public Eco() {

	}

	public float GetPlayerAmount(EntityPlayer Player) {

		UUID tempUUID = Player.getUniqueID();

		if (PlayerAmounts.containsKey(tempUUID)) {
			return PlayerAmounts.get(tempUUID);
		} else {
			return 0;
		}

	}

}
