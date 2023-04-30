package me.api.data;

import org.bukkit.*;
import java.text.*;
import java.util.*;

public class PlayerAPI {
	private String name;
	private int RUB;
	private String lastJoin;
	private String firstJoin;
	
	public PlayerAPI(String name, int RUB, String lastJoin, String firstJoin) {
		this.name = name;
		this.RUB = RUB;
		this.lastJoin = lastJoin;
		this.firstJoin = firstJoin;
	}
	
	//getPlayerAPI
	public static PlayerAPI getPlayerAPI(OfflinePlayer player) {
		PlayerAPI playerAPI = DatabaseManager.getDatabaseManager().load(player.getName());
		if (playerAPI == null) {
			playerAPI = new PlayerAPI(player.getName(), 0, null, null);
			DatabaseManager.getDatabaseManager().create(playerAPI);
		}
		return playerAPI;
	}
	
	//name
	public String getName() {
		return this.name;
	}
	
	//RUB
	public int getRUB() {
		return this.RUB;
	}
	
	public void addRUB(int RUB) {
		if (RUB < 0) {
			Bukkit.getServer().getConsoleSender().sendMessage("§c[ERROR] REMOVE RUB < 0 CALLED: " + this.getName());
			return;
		}
		if (this.RUB + RUB > 1000000) {
			Bukkit.getServer().getConsoleSender().sendMessage("§c[ERROR] ADD THIS.RUB + RUB > 1000000 CALLED: " + this.getName());
			return;
		}
		this.RUB += RUB;
		Bukkit.getServer().getConsoleSender().sendMessage("§aTHE OPERATION ON '+' WAS PERFORMED. BALANCE FOR THE AMOUNT OF: " + RUB + " PLAYER " + this.getName());
	}
	
	public void removeRUB(int RUB) {
		if (RUB < 0) {
			Bukkit.getServer().getConsoleSender().sendMessage("§c[ERROR] REMOVE RUB < 0 CALLED: " + this.getName());
			return;
		}
		if (this.RUB - RUB < 0) {
			Bukkit.getServer().getConsoleSender().sendMessage("§c[ERROR] REMOVE THIS.RUB - RUB < 0 CALLED: " + this.getName());
			return;
		}
		this.RUB -= RUB;
		Bukkit.getServer().getConsoleSender().sendMessage("§aTHE OPERATION ON '-' WAS PERFORMED. BALANCE FOR THE AMOUNT OF: " + RUB + " PLAYER " + this.getName());
	}
	
	public void setRUB(int RUB) {
		if (RUB < 0) {
			Bukkit.getServer().getConsoleSender().sendMessage("§c[ERROR] SET RUB < 0 CALLED: " + this.getName());
			return;
		}
		if (RUB > 1000000) {
			Bukkit.getServer().getConsoleSender().sendMessage("§c[ERROR] SET RUB > 1000000 CALLED: " + this.getName());
			return;
		}
		this.RUB = RUB;
		Bukkit.getServer().getConsoleSender().sendMessage("§aTHE OPERATION ON '=' WAS PERFORMED. BALANCE FOR THE AMOUNT OF: " + RUB + " PLAYER " + this.getName());
	}
	
	//lastJoin
	public String getLastJoin() {
		return this.lastJoin;
	}
	
	public void setLastJoin(Date lastJoin) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.lastJoin = dateFormat.format(lastJoin);
	}
	
	//firstJoin
	public String getFirstJoin() {
		return this.firstJoin;
	}
	
	public void setFirstJoin(Date firstJoin) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.firstJoin = dateFormat.format(firstJoin);
	}
}