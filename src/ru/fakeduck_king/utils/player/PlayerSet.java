package ru.fakeduck_king.utils.player;

import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;

public class PlayerSet {
	private static final PlayerSet playerSet = new PlayerSet();
	private Set<Player> set;
	
	public static PlayerSet getPlayerSet() {
		return PlayerSet.playerSet;
	}
	
	public void setup() {
		this.set = new HashSet<>();
	}
	
	public boolean get(Player player) {
		return this.set.contains(player);
	}
	
	public void add(Player player) {
		this.set.add(player);
	}
	
	public void remove(Player player) {
		this.set.remove(player);
	}
}