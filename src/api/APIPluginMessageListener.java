package api;

import org.bukkit.plugin.messaging.*;
import com.google.common.io.*;
import org.bukkit.entity.*;

public class APIPluginMessageListener implements PluginMessageListener {
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		
		ByteArrayDataInput byteArrayDataInput = ByteStreams.newDataInput(message);
		String channelName = byteArrayDataInput.readUTF();
		
		if (channelName.equals("Message")) {
			String senderUTF = byteArrayDataInput.readUTF();
			String messageUTF = byteArrayDataInput.readUTF();
			
			player.sendMessage(senderUTF + " написал вам: " + messageUTF);
		}
	}
}