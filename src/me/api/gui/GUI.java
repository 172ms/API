package me.api.gui;

import ru.tehkode.permissions.bukkit.*;
import ru.fakeduck_king.messages.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import me.api.utils.*;
import me.api.data.*;
import org.bukkit.*;

@SuppressWarnings("deprecation")
public class GUI {
	//MAIN DONATE
	public static void openDonate(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 45, Prefix.DONATE);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		inventory.setItem(4, CustomItem.create(Material.PAPER, "&fВаш баланс: &a" + playerAPI.getRUB() + " RUB"));
		
		inventory.setItem(11, CustomItem.create(Material.COAL, Prefix.COAL, new String[] {
				" §fИнформация о донате:",
				"   §fСтоимость §a§l> §a" + Prefix.COAL_COST + " RUB",
				"   §fДля более подробной информации нажмите колёсиком мыши",
				"",
				" §aНажмите, чтобы купить"
		}));
		
		inventory.setItem(15, CustomItem.create(Material.IRON_INGOT, Prefix.IRON, new String[] {
				" §fИнформация о донате:",
				"   §fСтоимость §a§l> §a" + Prefix.IRON_COST + " RUB",
				"   §fДля более подробной информации нажмите колёсиком мыши",
				"",
				" §aНажмите, чтобы купить"
		}));
		
		inventory.setItem(21, CustomItem.create(Material.GOLD_INGOT, Prefix.GOLD, new String[] {
				" §fИнформация о донате:",
				"   §fСтоимость §a§l> §a" + Prefix.GOLD_COST + " RUB",
				"   §fДля более подробной информации нажмите колёсиком мыши",
				"",
				" §aНажмите, чтобы купить"
		}));
		
		inventory.setItem(22, CustomItem.create(Material.EMERALD, Prefix.EMERALD, new String[] {
				" §fИнформация о донате:",
				"   §fСтоимость §a§l> §a" + Prefix.EMERALD_COST + " RUB",
				"   §fДля более подробной информации нажмите колёсиком мыши",
				"",
				" §aНажмите, чтобы купить"
		}));
		
		inventory.setItem(23, CustomItem.create(Material.DIAMOND, Prefix.DIAMOND, new String[] {
				" §fИнформация о донате:",
				"   §fСтоимость §a§l> §a" + Prefix.DIAMOND_COST + " RUB",
				"   §fДля более подробной информации нажмите колёсиком мыши",
				"",
				" §aНажмите, чтобы купить"
		}));
		
		inventory.setItem(31, CustomItem.create(Material.TOTEM_OF_UNDYING, Prefix.FLUX, new String[] {
				" §fИнформация о донате:",
				"   §fСтоимость §a> §a" + Prefix.FLUX_COST + " RUB",
				"   §fДля более подробной информации нажмите колёсиком мыши",
				"",
				" §aНажмите, чтобы купить"
		}));
		
		inventory.setItem(36, CustomItem.create(Material.BARRIER, "&cЗакрыть"));
		
		inventory.setItem(44, CustomItem.create(Material.BARRIER, "&cЗакрыть"));
		
		player.openInventory(inventory);
	}
	
	//CONFIRM DONATE COAL
	public static void openConfirmDonateCoal(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 27, Prefix.CONFIRM_DONATE + " " + Prefix.COAL);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		
		if (validGroup.ordinal() <= ValidGroup.COAL.ordinal()) {
			 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 return;
		}
		
		inventory.setItem(11, CustomItem.create(Material.LIME_WOOL, "&aПодтвердить"));
		
		inventory.setItem(13, CustomItem.create(Material.PAPER, "&fВаш баланс: &a" + playerAPI.getRUB() + " RUB"));
		
		inventory.setItem(15, CustomItem.create(Material.RED_WOOL, "&cОтменить"));
		
		player.openInventory(inventory);
	}
	
	//CONFIRM DONATE IRON
	public static void openConfirmDonateIron(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 27, Prefix.CONFIRM_DONATE + " " + Prefix.IRON);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.IRON_COST - validGroup.getCost();
		
		if (validGroup.ordinal() <= ValidGroup.IRON.ordinal()) {
			 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 return;
		}
		
		inventory.setItem(11, CustomItem.create(Material.LIME_WOOL, validGroup.getCost() > 0 ? "&aПодтвердить, доплатив " + cost + " RUB" : "&aПодтвердить"));
		
		inventory.setItem(13, CustomItem.create(Material.PAPER, "&fВаш баланс: &a" + playerAPI.getRUB() + " RUB"));
		
		inventory.setItem(15, CustomItem.create(Material.RED_WOOL, "&cОтменить"));
		
		player.openInventory(inventory);
	}
	
	//CONFIRM DONATE GOLD
	public static void openConfirmDonateGold(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 27, Prefix.CONFIRM_DONATE + " " + Prefix.GOLD);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.GOLD_COST - validGroup.getCost();
		
		if (validGroup.ordinal() <= ValidGroup.GOLD.ordinal()) {
			 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 return;
		}
		
		inventory.setItem(11, CustomItem.create(Material.LIME_WOOL, validGroup.getCost() > 0 ? "&aПодтвердить, доплатив " + cost + " RUB" : "&aПодтвердить"));
		
		inventory.setItem(13, CustomItem.create(Material.PAPER, "&fВаш баланс: &a" + playerAPI.getRUB() + " RUB"));
		
		inventory.setItem(15, CustomItem.create(Material.RED_WOOL, "&cОтменить"));
		
		player.openInventory(inventory);
	}
	
	//CONFIRM DONATE EMERALD
	public static void openConfirmDonateEmerald(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 27, Prefix.CONFIRM_DONATE + " " + Prefix.EMERALD);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.EMERALD_COST - validGroup.getCost();
		
		if (validGroup.ordinal() <= ValidGroup.EMERALD.ordinal()) {
			 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 return;
		}
		
		inventory.setItem(11, CustomItem.create(Material.LIME_WOOL, validGroup.getCost() > 0 ? "&aПодтвердить, доплатив " + cost + " RUB" : "&aПодтвердить"));
		
		inventory.setItem(13, CustomItem.create(Material.PAPER, "&fВаш баланс: &a" + playerAPI.getRUB() + " RUB"));
		
		inventory.setItem(15, CustomItem.create(Material.RED_WOOL, "&cОтменить"));
		
		player.openInventory(inventory);
	}
	
	//CONFIRM DONATE DIAMOND
	public static void openConfirmDonateDiamond(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 27, Prefix.CONFIRM_DONATE + " " + Prefix.DIAMOND);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.DIAMOND_COST - validGroup.getCost();
		
		if (validGroup.ordinal() <= ValidGroup.DIAMOND.ordinal()) {
			 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 return;
		}
		
		inventory.setItem(11, CustomItem.create(Material.LIME_WOOL, validGroup.getCost() > 0 ? "&aПодтвердить, доплатив " + cost + " RUB" : "&aПодтвердить"));
		
		inventory.setItem(13, CustomItem.create(Material.PAPER, "&fВаш баланс: &a" + playerAPI.getRUB() + " RUB"));
		
		inventory.setItem(15, CustomItem.create(Material.RED_WOOL, "&cОтменить"));
		
		player.openInventory(inventory);
	}
	
	//CONFIRM DONATE FLUX
	public static void openConfirmDonateFlux(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 27, Prefix.CONFIRM_DONATE + " " + Prefix.FLUX);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		ValidGroup validGroup = ValidGroup.valueOf(PermissionsEx.getUser(player).getGroups()[0].getName().toUpperCase());
		int cost = Prefix.FLUX_COST - validGroup.getCost();
		
		if (validGroup.ordinal() <= ValidGroup.FLUX.ordinal()) {
			 SexyMessage.send(player, "&cВы не можете купить ниже привилегию, или такую же.");
			 return;
		}
		
		inventory.setItem(11, CustomItem.create(Material.LIME_WOOL, validGroup.getCost() > 0 ? "&aПодтвердить, доплатив " + cost + " RUB" : "&aПодтвердить"));
		
		inventory.setItem(13, CustomItem.create(Material.PAPER, "&fВаш баланс: &a" + playerAPI.getRUB() + " RUB"));
		
		inventory.setItem(15, CustomItem.create(Material.RED_WOOL, "&cОтменить"));
		
		player.openInventory(inventory);
	}
	
	//SELECT MINI GAMES COAL
	public static void openSelectMiniGamesCoal(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 45, Prefix.SELECT_MINI_GAMES_COAL);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		inventory.setItem(10, CustomItem.create(Material.IRON_SHOVEL, "&aSPLEEF"));
		
		inventory.setItem(13, CustomItem.create(Material.IRON_PICKAXE, "&aPRISON NORMAL"));
		
		inventory.setItem(36, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		inventory.setItem(44, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		player.openInventory(inventory);
	}
	
	//SELECT MINI GAMES IRON
	public static void openSelectMiniGamesIron(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 45, Prefix.SELECT_MINI_GAMES_IRON);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}

		inventory.setItem(10, CustomItem.create(Material.IRON_SHOVEL, "&aSPLEEF"));
		
		inventory.setItem(13, CustomItem.create(Material.IRON_PICKAXE, "&aPRISON NORMAL"));
		
		inventory.setItem(36, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		inventory.setItem(44, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		player.openInventory(inventory);
	}
	
	//SELECT MINI GAMES GOLD
	public static void openSelectMiniGamesGold(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 45, Prefix.SELECT_MINI_GAMES_GOLD);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		inventory.setItem(10, CustomItem.create(Material.IRON_SHOVEL, "&aSPLEEF"));
		
		inventory.setItem(13, CustomItem.create(Material.IRON_PICKAXE, "&aPRISON NORMAL"));
		
		inventory.setItem(36, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		inventory.setItem(44, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		player.openInventory(inventory);
	}
	
	//SELECT MINI GAMES EMERALD
	public static void openSelectMiniGamesEmerald(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 45, Prefix.SELECT_MINI_GAMES_EMERALD);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		inventory.setItem(10, CustomItem.create(Material.IRON_SHOVEL, "&aSPLEEF"));
		
		inventory.setItem(13, CustomItem.create(Material.IRON_PICKAXE, "&aPRISON NORMAL"));
		
		inventory.setItem(36, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		inventory.setItem(44, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		player.openInventory(inventory);
	}
	
	//SELECT MINI GAMES DIAMOND
	public static void openSelectMiniGamesDiamond(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 45, Prefix.SELECT_MINI_GAMES_DIAMOND);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		inventory.setItem(10, CustomItem.create(Material.IRON_SHOVEL, "&aSPLEEF"));
		
		inventory.setItem(13, CustomItem.create(Material.IRON_PICKAXE, "&aPRISON NORMAL"));
		
		inventory.setItem(36, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		inventory.setItem(44, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		player.openInventory(inventory);
	}
	
	//SELECT MINI GAMES FLUX
	public static void openSelectMiniGamesFlux(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 45, Prefix.SELECT_MINI_GAMES_FLUX);
		
		for (int i = 0; i < inventory.getSize(); ++i) {
			inventory.setItem(i, CustomItem.create(Material.BLACK_STAINED_GLASS_PANE, " "));
		}
		
		inventory.setItem(10, CustomItem.create(Material.IRON_SHOVEL, "&aSPLEEF"));
		
		inventory.setItem(13, CustomItem.create(Material.IRON_PICKAXE, "&aPRISON NORMAL"));
		
		inventory.setItem(36, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		inventory.setItem(44, CustomItem.create(Material.PAPER, "&cВернуться назад"));
		
		player.openInventory(inventory);
	}
}