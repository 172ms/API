package me.api.listener.oldpvp;

import ru.fakeduck_king.register.listeners.*;
import org.bukkit.event.player.*;
import me.api.configuration.*;
import org.bukkit.attribute.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;

public class HandlersPvP extends SexyEvent {
	
	public HandlersPvP(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (ConfigManager.getConfigManager().getConfig().getBoolean("oldPvP")) {
			event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100.D);
		}
		else {
			event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.D);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (ConfigManager.getConfigManager().getConfig().getBoolean("oldPvP")) {
			event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100.D);
		}
		else {
			event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.D);
		}
	}
}