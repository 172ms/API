package ru.fakeduck_king.utils.player;

import com.google.common.collect.Maps;
import java.util.concurrent.TimeUnit;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.Map;

public class PlayerCooldown {
	private static final PlayerCooldown playerCooldown = new PlayerCooldown();
	private Map<UUID, Double> time;
	
	public static PlayerCooldown getPlayerCooldown() {
		return PlayerCooldown.playerCooldown;
	}
		
	public void setup() {
		this.time = Maps.newHashMap();
	}
	
	public int get(Player player) {
		return Math.toIntExact(Math.round((this.time.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000F));
	}
	
	public boolean has(Player player) {
		return !this.time.containsKey(player.getUniqueId()) || this.time.get(player.getUniqueId()) <= System.currentTimeMillis();
	}
	
	public void set(Player player, int seconds) {
		double delay = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds);
		this.time.put(player.getUniqueId(), delay);
	}
}