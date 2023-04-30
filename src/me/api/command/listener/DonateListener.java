package me.api.command.listener;

import ru.fakeduck_king.register.listeners.*;
import ru.tehkode.permissions.bukkit.*;
import org.bukkit.event.inventory.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import me.api.data.*;
import me.api.gui.*;
import org.bukkit.*;
import java.util.*;

//ТУТ ПОЛНЫЙ ПИЗДЕЦ
@SuppressWarnings("all")
public class DonateListener extends SexyEvent {
	private final String[] validGroups = {
		"COAL",
		"IRON",
		"GOLD",
		"EMERALD",
		"DIAMOND",
		"FLUX",
		"HELPER",
		"MODERATOR",
		"CURATORMODERATOR",
		"BUILDER",
		"CURATORBUILDER",
		"ADMIN",
		"DEVELOPER"
	};
	
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
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			if (Arrays.asList(this.validGroups).indexOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase()) >= 0) {
				SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же");
				player.closeInventory();
				return;
			}
			
			if (playerAPI.getRUB() >= 59) {
				playerAPI.removeRUB(59);
				DatabaseManager.getDatabaseManager().save(playerAPI);
				PermissionsEx.getUser(player).setGroups(new String[] { "COAL" });
				SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.COAL);
				player.closeInventory();
				return;
			}
			else {
				SexyMessage.send(player, "&cНедостаточно средств!");
				player.closeInventory();
				return;
			}
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			player.closeInventory();
			return;
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
		
		List validGroupsList = new ArrayList<>(Arrays.asList(this.validGroups));
		validGroupsList.removeAll(Arrays.asList("COAL"));
		Object[] validGroupsIron = validGroupsList.toArray(new String[validGroupsList.size()]);
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			if (Arrays.asList(validGroupsIron).indexOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase()) >= 0) {
				SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же");
				player.closeInventory();
				return;
			}
			
			if (PermissionsEx.getUser(player.getName()).inGroup("COAL")) {
				if (playerAPI.getRUB() >= 149 - 59) {
					playerAPI.removeRUB(149 - 59);
					DatabaseManager.getDatabaseManager().save(playerAPI);
					PermissionsEx.getUser(player).setGroups(new String[] { "IRON" });
					SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.IRON + "! &aДоплатив 90 RUB");
					player.closeInventory();
					return;
				}
				else {
					SexyMessage.send(player, "&cНедостаточно средств!");
					player.closeInventory();
					return;
				}
			}
			
			if (playerAPI.getRUB() >= 149) {
				playerAPI.removeRUB(149);
				DatabaseManager.getDatabaseManager().save(playerAPI);
				PermissionsEx.getUser(player).setGroups(new String[] { "IRON" });
				SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.IRON);
				player.closeInventory();
				return;
			}
			else {
				SexyMessage.send(player, "&cНедостаточно средств!");
				player.closeInventory();
				return;
			}
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			player.closeInventory();
			return;
		}
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateGold(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.GOLD).equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		List validGroupsList = new ArrayList<>(Arrays.asList(this.validGroups));
		validGroupsList.removeAll(Arrays.asList("COAL", "IRON"));
		Object[] validGroupsGold = validGroupsList.toArray(new String[validGroupsList.size()]);
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			if (Arrays.asList(validGroupsGold).indexOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase()) >= 0) {
				SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же");
				player.closeInventory();
				return;
			}
			
			if (PermissionsEx.getUser(player.getName()).inGroup("COAL")) {
				if (playerAPI.getRUB() >= 299 - 59) {
					playerAPI.removeRUB(299 - 59);
					DatabaseManager.getDatabaseManager().save(playerAPI);
					PermissionsEx.getUser(player).setGroups(new String[] { "GOLD" });
					SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.GOLD + "! &aДоплатив 240 RUB");
					player.closeInventory();
					return;
				}
				else {
					SexyMessage.send(player, "&cНедостаточно средств!");
					player.closeInventory();
					return;
				}
			}
			
			if (PermissionsEx.getUser(player.getName()).inGroup("IRON")) {
				if (playerAPI.getRUB() >= 299 - 149) {
					playerAPI.removeRUB(299 - 149);
					DatabaseManager.getDatabaseManager().save(playerAPI);
					PermissionsEx.getUser(player).setGroups(new String[] { "GOLD" });
					SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.GOLD + "! &aДоплатив 150 RUB");
					player.closeInventory();
					return;
				}
				else {
					SexyMessage.send(player, "&cНедостаточно средств!");
					player.closeInventory();
					return;
				}
			}
			
			if (playerAPI.getRUB() >= 299) {
				playerAPI.removeRUB(299);
				DatabaseManager.getDatabaseManager().save(playerAPI);
				PermissionsEx.getUser(player).setGroups(new String[] { "GOLD" });
				SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.GOLD);
				player.closeInventory();
			}
			else {
				SexyMessage.send(player, "&cНедостаточно средств!");
				player.closeInventory();
			}
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			player.closeInventory();
		}
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateEmerald(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.EMERALD).equals((player).getOpenInventory().getTitle())) {
			return;
		}
		
		event.setCancelled(true);
		
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		
		List validGroupsList = new ArrayList<>(Arrays.asList(this.validGroups));
		validGroupsList.removeAll(Arrays.asList("COAL", "IRON", "GOLD"));
		Object[] validGroupsGold = validGroupsList.toArray(new String[validGroupsList.size()]);
		
		if (itemStack.getType() == Material.LIME_WOOL) {
			if (Arrays.asList(validGroupsGold).indexOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase()) >= 0) {
				SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же");
				player.closeInventory();
				return;
			}
			
			if (playerAPI.getRUB() >= 599) {
				playerAPI.removeRUB(599);
				DatabaseManager.getDatabaseManager().save(playerAPI);
				PermissionsEx.getUser(player).setGroups(new String[] { "EMERALD" });
				SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.EMERALD);
				player.closeInventory();
			}
			else {
				SexyMessage.send(player, "&cНедостаточно средств!");
				player.closeInventory();
			}
		}
		
		if (itemStack.getType() == Material.RED_WOOL) {
			player.closeInventory();
		}
	}
	
	@EventHandler
	public void onInventoryClickConfirmDonateDiamond(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		Player player = (Player)event.getWhoClicked();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		if (!(Prefix.CONFIRM_DONATE + " " + Prefix.DIAMOND).equals((player).getOpenInventory().getTitle())) {
			return;
		}
		event.setCancelled(true);
		if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) {
			return;
		}
		List validGroupsList = new ArrayList<>(Arrays.asList(this.validGroups));
		validGroupsList.removeAll(Arrays.asList("COAL", "IRON", "GOLD", "EMERALD"));
		Object[] validGroupsGold = validGroupsList.toArray(new String[validGroupsList.size()]);
		if (itemStack.getType() == Material.LIME_WOOL) {
			if (Arrays.asList(validGroupsGold).indexOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase()) >= 0) {
				SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же");
				player.closeInventory();
				return;
			}
			if (playerAPI.getRUB() >= 1199) {
				playerAPI.removeRUB(1199);
				DatabaseManager.getDatabaseManager().save(playerAPI);
				PermissionsEx.getUser(player).setGroups(new String[] { "DIAMOND" });
				SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.DIAMOND);
				player.closeInventory();
			}
			else {
				SexyMessage.send(player, "&cНедостаточно средств!");
				player.closeInventory();
			}
		}
		if (itemStack.getType() == Material.RED_WOOL) {
			player.closeInventory();
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
		List validGroupsList = new ArrayList<>(Arrays.asList(this.validGroups));
		validGroupsList.removeAll(Arrays.asList("COAL", "IRON", "GOLD", "EMERALD", "DIAMOND"));
		Object[] validGroupsGold = validGroupsList.toArray(new String[validGroupsList.size()]);
		if (itemStack.getType() == Material.LIME_WOOL) {
			if (Arrays.asList(validGroupsGold).indexOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase()) >= 0) {
				SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же");
				player.closeInventory();
				return;
			}
			if (playerAPI.getRUB() >= 2399) {
				playerAPI.removeRUB(2399);
				DatabaseManager.getDatabaseManager().save(playerAPI);
				PermissionsEx.getUser(player).setGroups(new String[] { "FLUX" });
				SexyMessage.send(player, "&aВы успешно купили привилегию " + Prefix.FLUX);
				player.closeInventory();
			}
			else {
				SexyMessage.send(player, "&cНедостаточно средств!");
				player.closeInventory();
			}
		}
		if (itemStack.getType() == Material.RED_WOOL) {
			player.closeInventory();
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