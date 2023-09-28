package me.api.utils;

import ru.tehkode.permissions.bukkit.*;
import org.bukkit.scoreboard.*;
import me.api.configuration.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

@SuppressWarnings("deprecation")
public class SortDonation {
	private static Scoreboard scoreboard;
	private static final String PREFIX_PATH = "tablistTag.PREFIX-";
	
	private static final String[] TEAM_NAMES = { 
		"0",
		"1",
		"2",
		"3",
		"4",
		"5",
		"6",
		"7",
		"8",
		"9",
		"10",
		"11",
		"12",
		"13",
		"14"
	};
	
	private static final String[] PREFIXES = { 
		"DEVELOPER",
		"ADMIN",
		"YOUTUBE",
		"CURATORBUILDER",
		"BUILDER",
		"CURATORMODERATOR",
		"MODERATOR",
		"HELPER",
		"FLUX",
		"DIAMOND",
		"EMERALD",
		"GOLD",
		"IRON",
		"COAL",
		"DEFAULT"
	};
	
	private static final HashMap<String, String> GROUPS = new HashMap<>();
	
	static {
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-DEVELOPER"), "0");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-ADMIN"), "1");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-YOUTUBE"), "2");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-CURATORBUILDER"), "3");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-BUILDER"), "4");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-CURATORMODERATOR"), "5");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-MODERATOR"), "6");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-HELPER"), "7");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-FLUX"), "8");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-DIAMOND"), "9");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-EMERALD"), "10");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-GOLD"), "11");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-IRON"), "12");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-COAL"), "13");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-DEFAULT"), "14");
	}
	
	public static void setup() {
		SortDonation.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Arrays.asList(SortDonation.TEAM_NAMES).forEach(teamName -> {
			String prefix = ConfigManager.getConfigManager().getConfig().getString(SortDonation.PREFIX_PATH + SortDonation.PREFIXES[Arrays.asList(SortDonation.TEAM_NAMES).indexOf(teamName)]).replace("&", "§");
			
			SortDonation.scoreboard.registerNewTeam(teamName);
			SortDonation.scoreboard.getTeam(teamName).setPrefix(prefix);
		});
	}
	
	public static void unload() {
		Arrays.stream(SortDonation.TEAM_NAMES).forEach(teamName -> SortDonation.scoreboard.getTeam(teamName).unregister());
	}
	
	public static void sort(Player player) {
		String team = SortDonation.GROUPS.getOrDefault(PermissionsEx.getUser(player).getGroups()[0].getName(), "14");
		
		SortDonation.scoreboard.getTeam(team).addPlayer(player);
		player.setDisplayName(SortDonation.scoreboard.getTeam(team).getPrefix() + player.getName());
		
		Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {
			onlinePlayers.setScoreboard(SortDonation.scoreboard);
		});
	}
}