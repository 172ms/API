package api;

import ru.fakeduck_king.utils.player.*;
import me.api.command.listener.*;
import me.api.command.friends.*;
import org.bukkit.plugin.java.*;
import me.api.configuration.*;
import me.api.command.chat.*;
import me.api.listener.*;
import me.api.runnable.*;
import me.api.command.*;
import me.api.utils.*;
import me.api.data.*;
import org.bukkit.*;

public class API extends JavaPlugin {
	private void registerCommands() {
		new BroadcastCommand().register();
		new ClearChatCommand().register();
		new ClearCommand().register();
		new DeopCommand().register();
		new DifficultyCommand().register();
		new DonateCommand().register();
		//new EffectCommand().register();
		new EnderchestCommand().register();
		new ExtCommand().register();
		new FeedCommand().register();
		new FlyCommand().register();
		new GamemodeCommand().register();
		//new GiveCommand().register();
		new GiveRUBCommand().register();
		new HatCommand().register();
		new HealCommand().register();
		new HelpCommand().register();
		new KillCommand().register();
		new OnlineCommand().register();
		new OpCommand().register();
		new PingCommand().register();
		new PluginsCommand().register();
		new SaveallCommand().register();
		new StatusCommand().register();
		new StopCommand().register();
		new TeleportCommand().register();
		new TeleportHereCommand().register();
		new TimeCommand().register();
		new WeatherCommand().register();
		//new WhiteListCommand().register();
		
		if (ConfigManager.getConfigManager().getConfig().getBoolean("donatorsEnabled")) {
			new DonatorsCommand().register();
			new DonatorsToggleCommand().register();
		}
		
		new FriendsCommand().register();
	}
	
	private void registerListeners() {
		new DonateListener(getInstance());
		
		new Handlers(getInstance());
	}
	
	@Override
	public void onEnable() {
		DatabaseManager.getDatabaseManager().setup();
		
		ConfigManager.getConfigManager().setup();
		
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
		
		this.registerCommands();
		this.registerListeners();
		
		SortDonation.setup();
		
		new TablistTag().runTaskTimer(this, 0L, 100L);
		
		PlayerCooldown.getPlayerCooldown().setup();
		
		PlayerSet.getPlayerSet().setup();
	}
	
	@Override
	public void onDisable() {
		DatabaseManager.getDatabaseManager().closeConnection();
		
		SortDonation.unload();
	}
	
	public static API getInstance() {
		return API.getPlugin(API.class);
	}
}