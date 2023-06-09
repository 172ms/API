package me.api.data;

import ru.fakeduck_king.messages.*;
import org.bukkit.*;
import java.text.*;
import java.util.*;

public class PlayerAPI {
	private String name;
	private int RUB;
	private String lastJoin;
	private String firstJoin;
	private boolean messageStaff;
	private boolean messageDonators;
	
	public PlayerAPI(String name, int RUB, String lastJoin, String firstJoin, boolean messageStaff, boolean messageDonators) {
		this.name = name;
		this.RUB = RUB;
		this.lastJoin = lastJoin;
		this.firstJoin = firstJoin;
		this.messageStaff = messageStaff;
		this.messageDonators = messageDonators;
	}
	
	//getPlayerAPI
	public static PlayerAPI getPlayerAPI(OfflinePlayer player) {
		PlayerAPI playerAPI = DatabaseManager.getDatabaseManager().load(player.getName());
		
		if (playerAPI == null) {
			playerAPI = new PlayerAPI(player.getName(), 0, null, null, false, false);
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
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "REMOVE RUB < 0 CALLED: " + this.getName());
			return;
		}
		
		if (this.RUB + RUB > 1000000) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "ADD THIS.RUB + RUB > 1000000 CALLED: " + this.getName());
			return;
		}
		
		this.RUB += RUB;
		Bukkit.getServer().getConsoleSender().sendMessage(Prefix.SUCCESSFULLY + "THE OPERATION ON '+' WAS PERFORMED. BALANCE FOR THE AMOUNT OF: " + RUB + " PLAYER " + this.getName());
	}
	
	public void removeRUB(int RUB) {
		if (RUB < 0) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "REMOVE RUB < 0 CALLED: " + this.getName());
			return;
		}
		
		if (this.RUB - RUB < 0) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "REMOVE THIS.RUB - RUB < 0 CALLED: " + this.getName());
			return;
		}
		
		this.RUB -= RUB;
		Bukkit.getServer().getConsoleSender().sendMessage(Prefix.SUCCESSFULLY + "THE OPERATION ON '-' WAS PERFORMED. BALANCE FOR THE AMOUNT OF: " + RUB + " PLAYER " + this.getName());
	}
	
	public void setRUB(int RUB) {
		if (RUB < 0) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "SET RUB < 0 CALLED: " + this.getName());
			return;
		}
		
		if (RUB > 1000000) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "SET RUB > 1000000 CALLED: " + this.getName());
			return;
		}
		
		this.RUB = RUB;
		Bukkit.getServer().getConsoleSender().sendMessage(Prefix.SUCCESSFULLY + "THE OPERATION ON '=' WAS PERFORMED. BALANCE FOR THE AMOUNT OF: " + RUB + " PLAYER " + this.getName());
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
	
	//messageStaff
	public boolean getMessageStaff() {
		return this.messageStaff;
	}
	
	public void setMessageStaff(boolean messageStaff) {
		this.messageStaff = messageStaff;
	}
	
	//messageDonators
	public boolean getMessageDonators() {
		return this.messageDonators;
	}
	
	public void setMessageDonators(boolean messageDonators) {
		this.messageDonators = messageDonators;
	}
}