package me.api.data;

import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import api.*;

public class FriendRequest {
	public static List<FriendRequest> friendRequests = Lists.newArrayList();
	private String player;
	private String receiver;
	
	public FriendRequest(String player, String receiver) {
		this.player = player;
		this.receiver = receiver;
	}
	
	public List<FriendRequest> getFriendRequests() {
		return FriendRequest.friendRequests;
	}
	
	public String getPlayer() {	
		return this.player;
	}
	
	public String getReceiver() {
		return this.receiver;
	}
	
	public static void addFriendRequest(Player player, Player receiver) {
	    boolean isRequest = FriendRequest.friendRequests.stream().anyMatch(friendRequest ->
	    	(friendRequest.getPlayer().equals(player.getName()) && friendRequest.getReceiver().equals(receiver.getName())) 
	    	|| (friendRequest.getPlayer().equals(receiver.getName()) && friendRequest.getReceiver().equals(player.getName()))
	    );
	    
	    if (isRequest) {
	    	SexyMessage.send(player, "&cЗапрос на добавление в друзья уже отправлен!");
	    	return;
	    }
		
	    FriendRequest friendRequest = new FriendRequest(player.getName(), receiver.getName());
	    FriendRequest.friendRequests.add(friendRequest);
		
		SexyMessage.send(player, "Вы отправили запрос на добавление в друзья игроку &a" + receiver.getName() + ".");
		
		SexyMessage.send(receiver, "&a" + player.getName() + " &fхочет добавить вас в друзья.");
		SexyMessage.send(receiver, "Чтобы принять запрос, используйте: &a/friends accept " + player.getName());
		SexyMessage.send(receiver, "Чтобы отменить запрос, используйте: &c/friends cancel " + player.getName());
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(API.getInstance(), () -> {
			if (FriendRequest.friendRequests.contains(friendRequest)) {
				FriendRequest.friendRequests.remove(friendRequest);
				SexyMessage.send(player, "&cИстекло время ожидания запроса на добавление в друзья к " + receiver.getName() + ".");
				SexyMessage.send(receiver, "&cИстекло время ожидания запроса на добавление в друзья к " + receiver.getName() + ".");
			}
		}, 1200L);
	}
	
	public static void cancelFriendRequest(Player player, Player receiver) {
		FriendRequest.friendRequests.removeIf(friendRequest ->
			(friendRequest.getPlayer().equals(player.getName()) && friendRequest.getReceiver().equals(receiver.getName())) 
			|| (friendRequest.getPlayer().equals(receiver.getName()) && friendRequest.getReceiver().equals(player.getName()))
		);
	}
}