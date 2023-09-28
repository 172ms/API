package me.api.listener.oldpvp;

import ru.fakeduck_king.register.listeners.*;
import me.api.configuration.settings.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.attribute.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import org.bukkit.*;
import java.util.*;
import api.*;

public class HandlersPvP extends SexyEvent {
	private final Map<UUID, Long> healTimes = new WeakHashMap<>();
	private final long interval = 4000L;
	private final int addHP = 1;
	private final float exhaustion = 4;
	
	public HandlersPvP(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (ConfigSettings.getConfigSettings().isOldPvP()) {
			player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100.D);
		}
		else {
			player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.D);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		if (ConfigSettings.getConfigSettings().isOldPvP()) {
			player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100.D);
		}
		else {
			player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.D);
		}
		
		this.healTimes.remove(player.getUniqueId());
	}
	
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if (ConfigSettings.getConfigSettings().isOldPvP()) {
			if (event.getEntityType() != EntityType.PLAYER || event.getRegainReason() != EntityRegainHealthEvent.RegainReason.SATIATED) {
				return;
			}
			
			Player player = (Player)event.getEntity();
			UUID UUID = player.getUniqueId();
			
			event.setCancelled(true);
			
			float exhaustion = player.getExhaustion();
			long currentTime = System.currentTimeMillis();
			long lastHealTime = this.healTimes.computeIfAbsent(UUID, id -> currentTime);
			
			if (healTimes.containsKey(UUID) && currentTime - lastHealTime <= this.interval) {
				Bukkit.getScheduler().runTaskLater(API.getInstance(), () -> player.setExhaustion(exhaustion), 1L);
				return;
			}
			
			if (player.getHealth() < player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
				player.setHealth(this.clamp(player.getHealth() + this.addHP, 0.0, player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
				this.healTimes.put(UUID, currentTime);
			}
			
			Bukkit.getScheduler().runTaskLater(API.getInstance(), () -> {
				player.setExhaustion(exhaustion + this.exhaustion);
			}, 1L);
		}
	}
	
	private double clamp(double value, double minimum, double maximum) {
		double realMinimum = Math.min(minimum, maximum);
		double realMaximum = Math.max(minimum, maximum);
		
		if (value < realMinimum) {
			value = realMinimum;
		}
		
		if (value > realMaximum) {
			value = realMaximum;
		}
		return value;
	}
}