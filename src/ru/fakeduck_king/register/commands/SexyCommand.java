//https://github.com/Goblom/Bukkit-Libraries/blob/master/src/main/java/command/AbstractCommand.java
package ru.fakeduck_king.register.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandMap;
import org.bukkit.command.Command;
import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import java.util.List;

public abstract class SexyCommand implements CommandExecutor {
	protected static CommandMap commandMap;
	protected String command;
	protected String usage;
	protected String description;
	protected String permissionMessage;
	protected List<String> aliases;
	
	public SexyCommand(String command) {
		this(command, null, null, null, null);
	}
	
	public SexyCommand(String command, String usage) {
		this(command, usage, null, null, null);
	}
	
	public SexyCommand(String command, String usage, String description) {
		this(command, usage, description, null, null);
	}
	
	public SexyCommand(String command, String usage, String description, String permissionMessage) {
		this(command, usage, description, permissionMessage, null);
	}
	
	public SexyCommand(String command, String usage, String description, List<String> aliases) {
		this(command, usage, description, null, aliases);
	}
	
	public SexyCommand(String command, String usage, String description, String permissionMessage, List<String> aliases) {
		this.command = command.toLowerCase();
		this.usage = usage;
		this.description = description;
		this.permissionMessage = permissionMessage;
		this.aliases = aliases;
	}
	
	public void register() {
		ReflectCommand reflectCommand = new ReflectCommand(this.command);
		
		if (this.aliases != null) {
			reflectCommand.setAliases(this.aliases);
		}
		
		if (this.description != null) {
			reflectCommand.setDescription(this.description);
		}
		
		if (this.usage != null) {
			reflectCommand.setUsage(this.usage);
		}
		
		if (this.permissionMessage != null) {
			reflectCommand.setPermissionMessage(this.permissionMessage);
		}
		
		this.getCommandMap().register("", reflectCommand);
		reflectCommand.setExecutor(this);
	}
	
	private CommandMap getCommandMap() {
		if (SexyCommand.commandMap == null) {
			try {
				Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
				
				field.setAccessible(true);
				
				SexyCommand.commandMap = (CommandMap)field.get(Bukkit.getServer());
				
				return this.getCommandMap();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (SexyCommand.commandMap != null) {
			return SexyCommand.commandMap;
		}
		return this.getCommandMap();
	}
	
	private class ReflectCommand extends Command {
		SexyCommand sexyCommand = null;
		
		private ReflectCommand(String command) {
			super(command);
		}
		
		public void setExecutor(SexyCommand sexyCommand) {
			this.sexyCommand = sexyCommand;
		}
		
		@Override
		public boolean execute(CommandSender sender, String command, String[] args) {
			if (sexyCommand != null) {
				return sexyCommand.onCommand(sender, this, command, args);
			}
			return false;
		}
		
		@Override
		public List<String> tabComplete(CommandSender sender, String aliases, String[] args) {
			if (sexyCommand != null) {
				return sexyCommand.onTabComplete(sender, this, aliases, args);
			}
			return null;
		}
	}
	
	public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
	
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return null;
	}
}