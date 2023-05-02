package api;

import ru.fakeduck_king.utils.player.*;
import me.api.command.listener.*;
import org.bukkit.plugin.java.*;
import me.api.configuration.*;
import me.api.listener.*;
import me.api.runnable.*;
import me.api.command.*;
import me.api.data.*;
import org.bukkit.*;
import java.text.*;

public class API extends JavaPlugin {

	
	private void registerCommands() {
		new BroadcastCommand().register();
		new ClearCommand().register();
		new DonateCommand().register();
		new EnderchestCommand().register();
		new ExtCommand().register();
		new FeedCommand().register();
		new FlyCommand().register();
		new GamemodeCommand().register();
		new GiveRUBCommand().register();
		new HatCommand().register();
		new HealCommand().register();
		new HelpCommand().register();
		new OnlineCommand().register();
		new PingCommand().register();
		new StatusCommand().register();
		new TeleportCommand().register();
		new TeleportHereCommand().register();
		new TimeCommand().register();
	}
	
	private void registerListeners() {
		new DonateListener(getInstance());
		new Handlers(getInstance());
	}
	
	@Override
	public void onEnable() {
		//CONFIGMANAGER
		ConfigManager.getConfigManager().setup();
		
		//DATABASEMANAGER
		DatabaseManager.getDatabaseManager().setup();
		
		Bukkit.getServer().getConsoleSender().sendMessage("ANALYSIS"
		+ "§c\n"
		+ "⠄⠄⠄⢰⣧⣼⣯⠄⣸⣠⣶⣶⣦⣾⠄⠄⠄⠄⡀⠄⢀⣿⣿⠄⠄⠄⢸⡇⠄⠄\n"
		+ "⠄⠄⠄⣾⣿⠿⠿⠶⠿⢿⣿⣿⣿⣿⣦⣤⣄⢀⡅⢠⣾⣛⡉⠄⠄⠄⠸⢀⣿⠄\n"
		+ "⠄⠄⢀⡋⣡⣴⣶⣶⡀⠄⠄⠙⢿⣿⣿⣿⣿⣿⣴⣿⣿⣿⢃⣤⣄⣀⣥⣿⣿⠄\n"
		+ "⠄⠄⢸⣇⠻⣿⣿⣿⣧⣀⢀⣠⡌⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⣿⣿⣿⠄\n"
		+ "⠄⢀⢸⣿⣷⣤⣤⣤⣬⣙⣛⢿⣿⣿⣿⣿⣿⣿⡿⣿⣿⡍⠄⠄⢀⣤⣄⠉⠋⣰\n"
		+ "⠄⣼⣖⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⢇⣿⣿⡷⠶⠶⢿⣿⣿⠇⢀⣤\n"
		+ "⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⣷⣶⣥⣴⣿⡗\n"
		+ "⢀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠄\n"
		+ "⢸⣿⣦⣌⣛⣻⣿⣿⣧⠙⠛⠛⡭⠅⠒⠦⠭⣭⡻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄\n"
		+ "⠘⣿⣿⣿⣿⣿⣿⣿⣿⡆⠄⠄⠄⠄⠄⠄⠄⠄⠹⠈⢋⣽⣿⣿⣿⣿⣵⣾⠃⠄\n"
		+ "⠄⠘⣿⣿⣿⣿⣿⣿⣿⣿⠄⣴⣿⣶⣄⠄⣴⣶⠄⢀⣾⣿⣿⣿⣿⣿⣿⠃⠄⠄\n"
		+ "⠄⠄⠈⠻⣿⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⠄⣿⣿⡀⣾⣿⣿⣿⣿⣛⠛⠁⠄⠄⠄\n"
		+ "⠄⠄⠄⠄⠈⠛⢿⣿⣿⣿⠁⠞⢿⣿⣿⡄⢿⣿⡇⣸⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄\n"
		+ "⠄⠄⠄⠄⠄⠄⠄⠉⠻⣿⣿⣾⣦⡙⠻⣷⣾⣿⠃⠿⠋⠁⠄⠄⠄⠄⠄⢀⣠⣴\n"
		+ "⣿⣿⣿⣶⣶⣮⣥⣒⠲⢮⣝⡿⣿⣿⡆⣿⡿⠃⠄⠄⠄⠄⠄⠄⠄⣠⣴⣿⣿⣿");
		Bukkit.getServer().getConsoleSender().sendMessage("§cDEVELOPED BY vk.com/fakeduck_king");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		
		Bukkit.getServer().getConsoleSender().sendMessage("§cCurrent date and time: " + dateFormat.format(date));
		
		//REGISTER
		this.registerCommands();
		this.registerListeners();
		
		//RUNNABLE
		new TablistTag().runTaskTimer(this, 0L, 100L);
		
		//COOLDOWN
		PlayerCooldown.getPlayerCooldown().setup();
		
		//PLAYERSET
		PlayerSet.getPlayerSet().setup();
	}
	
	@Override
	public void onDisable() {
		DatabaseManager.getDatabaseManager().closeConnection();
	}
	
	public static API getInstance() {
		return API.getPlugin(API.class);
	}
}