package me.api.command.listener;

import ru.fakeduck_king.register.listeners.*;
import ru.tehkode.permissions.bukkit.*;
import org.bukkit.event.inventory.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import me.api.utils.*;
import me.api.data.*;
import me.api.gui.*;
import org.bukkit.*;
import java.util.*;

@SuppressWarnings("all")
public class DonateListener extends SexyEvent {
	
	public DonateListener(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onInventoryClickDonate(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		
		if (!Prefix.DONATE.equals(player.getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		if (event.getClick() == ClickType.MIDDLE) {
			switch (itemStack.getType()) {
				case COAL: {
					GUI.openSelectMiniGamesCoal(player);
					break;
				}
				case IRON_INGOT: {
					GUI.openSelectMiniGamesIron(player);
					break;
				}
				case GOLD_INGOT: {
					GUI.openSelectMiniGamesGold(player);
					break;
				}
				case EMERALD: {
					GUI.openSelectMiniGamesEmerald(player);
					break;
				}
				case DIAMOND: {
					GUI.openSelectMiniGamesDiamond(player);
					break;
				}
				case TOTEM_OF_UNDYING: {
					GUI.openSelectMiniGamesFlux(player);
					break;
				}
			}
			return;
		}
		
		switch (itemStack.getType()) {
			case COAL: {
				GUI.openConfirmDonateCoal(player);
				break;
			}
			case IRON_INGOT: {
				GUI.openConfirmDonateIron(player);
				break;
			}
			case GOLD_INGOT: {
				GUI.openConfirmDonateGold(player);
				break;
			}
			case EMERALD: {
				GUI.openConfirmDonateEmerald(player);
				break;
			}
			case DIAMOND: {
				GUI.openConfirmDonateDiamond(player);
				break;
			}
			case TOTEM_OF_UNDYING: {
				GUI.openConfirmDonateFlux(player);
				break;
			}
			case BARRIER: {
				player.closeInventory();
				break;
			}
		}
		return;
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateCoal(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.COAL).equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.COAL_COST - validGroup.getCost();
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			 if (validGroup.ordinal() <= ValidGroup.COAL.ordinal()) {
				 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 }
			 else if (playerAPI.getRUB() >= cost) {
				 playerAPI.removeRUB(cost);
				 DatabaseManager.getDatabaseManager().save(playerAPI);
				 
				 PermissionsEx.getUser(player).setGroups(new String[]{ "COAL" });
				 SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.COAL + "!");
			 }
			 else {
				 SexyMessage.send(player, "&cНедостаточно средств!");
			 }
			 player.closeInventory();
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateIron(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.IRON).equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.IRON_COST - validGroup.getCost();
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			 if (validGroup.ordinal() <= ValidGroup.IRON.ordinal()) {
				 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 }
			 else if (playerAPI.getRUB() >= cost) {
				 playerAPI.removeRUB(cost);
				 DatabaseManager.getDatabaseManager().save(playerAPI);
				 
				 PermissionsEx.getUser(player).setGroups(new String[]{ "IRON" });
				 SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.IRON + "!" + (validGroup.getCost() > 0 ? " &aДоплатив " + cost + " RUB." : ""));
			 }
			 else {
				 SexyMessage.send(player, "&cНедостаточно средств!");
			 }
			 player.closeInventory();
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateGold(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);

		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.GOLD).equals(player.getOpenInventory().getTitle())) {
			return;
		}

		event.setCancelled(true);

		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}

		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.GOLD_COST - validGroup.getCost();
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			 if (validGroup.ordinal() <= ValidGroup.GOLD.ordinal()) {
				 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 }
			 else if (playerAPI.getRUB() >= cost) {
				 playerAPI.removeRUB(cost);
				 DatabaseManager.getDatabaseManager().save(playerAPI);
				 
				 PermissionsEx.getUser(player).setGroups(new String[]{ "GOLD" });
				 SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.GOLD + "!" + (validGroup.getCost() > 0 ? " &aДоплатив " + cost + " RUB." : ""));
			 }
			 else {
				 SexyMessage.send(player, "&cНедостаточно средств!");
			 }
			 player.closeInventory();
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateEmerald(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);

		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.EMERALD).equals(player.getOpenInventory().getTitle())) {
			return;
		}

		event.setCancelled(true);

		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}

		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.EMERALD_COST - validGroup.getCost();
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			 if (validGroup.ordinal() <= ValidGroup.EMERALD.ordinal()) {
				 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 }
			 else if (playerAPI.getRUB() >= cost) {
				 playerAPI.removeRUB(cost);
				 DatabaseManager.getDatabaseManager().save(playerAPI);
				 
				 PermissionsEx.getUser(player).setGroups(new String[]{ "EMERALD" });
				 SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.EMERALD + "!" + (validGroup.getCost() > 0 ? " &aДоплатив " + cost + " RUB." : ""));
			 }
			 else {
				 SexyMessage.send(player, "&cНедостаточно средств!");
			 }
			 player.closeInventory();
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateDiamond(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);

		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.DIAMOND).equals(player.getOpenInventory().getTitle())) {
			return;
		}

		event.setCancelled(true);

		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.DIAMOND_COST - validGroup.getCost();
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			 if (validGroup.ordinal() <= ValidGroup.DIAMOND.ordinal()) {
				 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 }
			 else if (playerAPI.getRUB() >= cost) {
				 playerAPI.removeRUB(cost);
				 DatabaseManager.getDatabaseManager().save(playerAPI);
				 
				 PermissionsEx.getUser(player).setGroups(new String[]{ "DIAMOND" });
				 SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.DIAMOND + "!" + (validGroup.getCost() > 0 ? " &aДоплатив " + cost + " RUB." : ""));
			 }
			 else {
				 SexyMessage.send(player, "&cНедостаточно средств!");
			 }
			 player.closeInventory();
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateFlux(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.FLUX).equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.FLUX_COST - validGroup.getCost();
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			 if (validGroup.ordinal() <= ValidGroup.FLUX.ordinal()) {
				 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 }
			 else if (playerAPI.getRUB() >= cost) {
				 playerAPI.removeRUB(cost);
				 DatabaseManager.getDatabaseManager().save(playerAPI);
				 
				 PermissionsEx.getUser(player).setGroups(new String[]{ "FLUX" });
				 SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.FLUX + "!" + (validGroup.getCost() > 0 ? " &aДоплатив " + cost + " RUB." : ""));
			 }
			 else {
				 SexyMessage.send(player, "&cНедостаточно средств!");
			 }
			 player.closeInventory();
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickSelectMiniGamesCoal(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		
		if (!Prefix.SELECT_MINI_GAMES_COAL.equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		if (itemStack.getType() == Material.PAPER) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickSelectMiniGamesIron(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		
		if (!Prefix.SELECT_MINI_GAMES_IRON.equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		if (itemStack.getType() == Material.PAPER) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickSelectMiniGamesGold(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		
		if (!Prefix.SELECT_MINI_GAMES_GOLD.equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		if (itemStack.getType() == Material.PAPER) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickSelectMiniGamesEmerald(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		
		if (!Prefix.SELECT_MINI_GAMES_EMERALD.equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		if (itemStack.getType() == Material.PAPER) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickSelectMiniGameDiamond(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		
		if (!Prefix.SELECT_MINI_GAMES_DIAMOND.equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		if (itemStack.getType() == Material.PAPER) {
			GUI.openDonate(player);
		}
	}
	
	@EventHandler
	public void onInventoryClickSelectMiniGameFlux(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		
		if (!Prefix.SELECT_MINI_GAMES_FLUX.equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		if (itemStack.getType() == Material.PAPER) {
			GUI.openDonate(player);
		}
	}
}