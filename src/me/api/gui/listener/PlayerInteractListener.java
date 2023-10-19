package me.api.gui.listener;

import ru.fakeduck_king.register.listeners.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import me.api.gui.*;
import org.bukkit.*;

public class PlayerInteractListener extends SexyEvent {
	
	public PlayerInteractListener(Plugin plugin) {
		super(plugin);
	}
	
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        
        if (event.getAction() ==  Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (itemStack != null && itemStack.getType() == Material.COMPASS) {
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(Prefix.SERVER_SELECTION)) {
                    GUI.openServerSelection(player);
                }
            }
        }
    }
}