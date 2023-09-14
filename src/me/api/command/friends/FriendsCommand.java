package me.api.command.friends;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import com.google.common.collect.Lists;

import me.api.data.*;
import org.bukkit.*;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class FriendsCommand extends SexyCommand {
	
	public FriendsCommand() {
		super("friends",
		"",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("f"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		
		Player player = (Player)sender;
		
		if (args.length < 1) {
			this.sendHelper(player);
			return true;
		}
		
		String string = args[0];
		
		switch (string) {
			case "list": {
				PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
				SexyMessage.send(player, "Список друзей §a" + playerAPI.getFriends().size() + " &fиз &a10.");
				
				if (playerAPI.getFriends().isEmpty()) {
					player.sendMessage("§cУ вас нет друзей :(");
				}
				else {
					playerAPI.getFriends().forEach(friends -> {
						OfflinePlayer target = Bukkit.getOfflinePlayer(friends);
						PlayerAPI friendAPI = PlayerAPI.getPlayerAPI(target);
						
						if (target.isOnline()) {
							player.sendMessage("§a" + target.getName() + " (онлайн)");
						}
						else {
							player.sendMessage("§c" + target.getName() + " (последний раз был в сети " + friendAPI.getLastJoin() + ")");
						}
					});
				}
				return true;
			}
		}
		switch (string) {
			case "add": {
				if (args.length < 2) {
					SexyMessage.send(player, "§cИспользуйте правильно команду: /friends add §8[§cИгрок§8]");
					return true;
				}
				
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
				
				if (target == null) {
					SexyMessage.send(player, "&cИгрок " + args[1] + " не найден!");
					return true;
				}
				
				if (player == target) {
					SexyMessage.send(player, "&cВы совсем одиноки? :(");
					return true;
				}
				
				PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
				List<String> friendsList = playerAPI.getFriends();
				
				if (friendsList.contains(target.getName())) {
					SexyMessage.send(player, "&cИгрок " + args[1] + " уже есть в вашем списке друзей!");
					return true;
				}
				
				if (friendsList.size() >= 10) {
					SexyMessage.send(player, "&cВы достигли максимального лимита друзей (10)!");
					return true;
				}
				
				FriendRequest.addFriendRequest(player, target.getPlayer());
				return true;
			}
			case "remove": {
				if (args.length < 2) {
					SexyMessage.send(player, "§cИспользуйте правильно команду: /friends remove §8[§cИгрок§8]");
					return true;
				}
				
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
				PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
				
				if (!playerAPI.getFriends().contains(target.getName())) {
					SexyMessage.send(player, "&cИгрок " + args[1]  + " не находится в вашем списке друзей.");
					return true;
				}
				
				PlayerAPI friendAPI = PlayerAPI.getPlayerAPI(target);
				
				playerAPI.removeFriend(target.getName());
				friendAPI.removeFriend(player.getName());
				DatabaseManager.getDatabaseManager().save(playerAPI);
				DatabaseManager.getDatabaseManager().save(friendAPI);
				SexyMessage.send(player, "&cВы удалили игрока " + args[1] + " из списка друзей.");
				return true;
			}
			case "accept": {
				if (args.length < 2) {
					SexyMessage.send(player, "§cИспользуйте правильно команду: /friends accept §8[§cИгрок§8]");
					return true;
				}
				
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
				PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
				
				boolean isRequest = FriendRequest.friendRequests.removeIf(request ->
					request.getReceiver().equals(player.getName()) && request.getPlayer().equals(target.getName())
				);
				
				if (!isRequest) {
					SexyMessage.send(player, "&cЗапрос на добавление в друзья от игрока " + args[1] + " не найден!");
					return true;
				}
				
				PlayerAPI friendAPI = PlayerAPI.getPlayerAPI(target);
				
				playerAPI.addFriend(target.getName());
				friendAPI.addFriend(player.getName());
				DatabaseManager.getDatabaseManager().save(playerAPI);
				DatabaseManager.getDatabaseManager().save(friendAPI);
				
				if (target.isOnline()) {
					SexyMessage.send((Player)target, player.getName() + " принял ваш запрос на добавление в друзья.");
				}
				
				SexyMessage.send(player, "Вы приняли запрос на добавление в друзья от &a" + args[1] + ".");
				return true;
			}
			case "cancel": {
				if (args.length < 2) {
					SexyMessage.send(player, "§cИспользуйте правильно команду: /friends cancel §8[§cИгрок§8]");
					return true;
				}
				
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
				
				boolean isRequest = FriendRequest.friendRequests.removeIf(request ->
					request.getReceiver().equals(player.getName()) && request.getPlayer().equals(target.getName())
				);
				
				if (!isRequest) {
					SexyMessage.send(player, "&cЗапрос на добавление в друзья от игрока " + args[1] + " не найден!");
					return true;
				}
				
				FriendRequest.cancelFriendRequest(player, target.getPlayer());
				SexyMessage.send(player, "&cВы отменили запрос на добавление в друзья от игрока " + args[1] + ".");
				return true;
			}
			default: {
				this.sendHelper(player);
				return true;
			}
		}
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> list = Lists.newArrayList();
		
		if (args.length == 1) {
			List<String> available = Arrays.asList("list", "add", "remove", "accept", "cancel");
			for (String key : available) {
				if (key.startsWith(args[0])) {
					list.add(key);
				}
			}
		}
		else if (args.length == 2) {
			list.addAll(Bukkit.getOnlinePlayers().stream()
			.map(Player::getName)
			.filter(name -> name.startsWith(args[1]))
			.collect(Collectors.toList()));
		}
		return list;
	}
	
	private void sendHelper(Player player) {
		SexyMessage.send(player, "Основные команды:");
		SexyMessage.send(player, "&a/friends list - &fузнать список друзей.");
		SexyMessage.send(player, "&a/friends add §8[§cИгрок§8] &a- &fдобавить друга.");
		SexyMessage.send(player, "&a/friends remove §8[§cИгрок§8] &a- &fудалить друга.");
		SexyMessage.send(player, "&a/friends accept §8[§cИгрок§8] &a- &fпринять запрос.");
		SexyMessage.send(player, "&a/friends cancel §8[§cИгрок§8] &a- &fотменить запрос.");
	}
}