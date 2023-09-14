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
		"13"
	};
	
	private static final String[] PREFIXES = { 
		"DEVELOPER",
		"ADMIN",
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
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-CURATORBUILDER"), "2");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-BUILDER"), "3");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-CURATORMODERATOR"), "4");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-MODERATOR"), "5");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-HELPER"), "6");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-FLUX"), "7");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-DIAMOND"), "8");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-EMERALD"), "9");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-GOLD"), "10");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-IRON"), "11");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-COAL"), "12");
		SortDonation.GROUPS.put((ConfigManager.getConfigManager().getConfig()).getString("tablistTag.NAME-DEFAULT"), "13");
	}
	
	public static void setup() {
		SortDonation.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Arrays.asList(SortDonation.TEAM_NAMES).forEach(teamNAME -> {
			String prefix = ConfigManager.getConfigManager().getConfig().getString(SortDonation.PREFIX_PATH + SortDonation.PREFIXES[Arrays.asList(SortDonation.TEAM_NAMES).indexOf(teamNAME)]).replace("&", "§");
			
			SortDonation.scoreboard.registerNewTeam(teamNAME);
			SortDonation.scoreboard.getTeam(teamNAME).setPrefix(prefix);
		});
	}
	
	public static void unload() {
		Arrays.stream(SortDonation.TEAM_NAMES).forEach(teamNAME -> SortDonation.scoreboard.getTeam(teamNAME).unregister());
	}
	
	public static void sort(Player player) {
		String team = SortDonation.GROUPS.getOrDefault(PermissionsEx.getUser(player).getGroups()[0].getName(), "13");
		
		SortDonation.scoreboard.getTeam(team).addPlayer(player);
		player.setDisplayName(SortDonation.scoreboard.getTeam(team).getPrefix() + player.getName());
		
		Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {
			onlinePlayers.setScoreboard(SortDonation.scoreboard);
		});
	}
}