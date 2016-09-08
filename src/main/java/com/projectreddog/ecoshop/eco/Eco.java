package com.projectreddog.ecoshop.eco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

public class Eco {
	private static HashMap<UUID, Float> playerAmounts;
	private String fileName = "Funds.txt";

	public Eco() {

	}

	public void init() {
		// TODO add read code here

		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line;
			try {
				line = in.readLine();

				while (line != null) {

					long leastBit = Long.parseLong(line);
					long mostBit = Long.parseLong(line);
					long funds = Long.parseLong(line);

					line = in.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save() {
		File file = new File(fileName);
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);

			for (Map.Entry<UUID, Float> entry : playerAmounts.entrySet()) {

				pw.println(entry.getKey().getLeastSignificantBits());
				pw.println(entry.getKey().getMostSignificantBits());
				pw.println(entry.getValue());

			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static UUID PlayerToUUID(EntityPlayer player) {
		return player.getUniqueID();
	}

	public static float GetPlayerAmount(EntityPlayer player) {
		return GetPlayerAmount(PlayerToUUID(player));

	}

	public static float GetPlayerAmount(UUID playerUUID) {
		if (playerAmounts.containsKey(playerUUID)) {
			return playerAmounts.get(playerUUID);
		} else {
			return 0;
		}

	}

	public static boolean CheckPlayerHasFunds(EntityPlayer player, Float funds) {
		return GetPlayerAmount(player.getUniqueID()) >= funds;
	}

	public static boolean SubtractFundsFormPlayer(EntityPlayer player, Float amtToRemove) {
		if (CheckPlayerHasFunds(player, amtToRemove)) {
			// THe player has enough
			playerAmounts.put(PlayerToUUID(player), GetPlayerAmount(player) - amtToRemove);
			return true;
		} else {
			return false;
		}
	}

	public static boolean AddfundstoPlayer(EntityPlayer player, Float funds) {
		UUID tmpUUID = PlayerToUUID(player);
		if (playerAmounts.containsKey(tmpUUID)) {
			// Already in map update amount
			playerAmounts.put(tmpUUID, GetPlayerAmount(player) + funds);

		} else {
			// need to add new record to map
			playerAmounts.put(tmpUUID, +funds);
		}
		return true;
	}

}