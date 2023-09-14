package ru.fakeduck_king.timer;

import ru.fakeduck_king.messages.SexyActionBar;
import org.bukkit.scheduler.BukkitRunnable;
import ru.fakeduck_king.messages.SexyTitle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.Location;

public abstract class SexyTimer {
	private Player player;
	private int second;
	
	public SexyTimer(Player player, int second) {
		this.player = player;
		this.second = second;
	}
	
	public int getSecond() {
		return this.second;
	}
	
	public void run(Plugin plugin) {
		Location location = player.getLocation();
		SexyTitle.send(player, "&aТЕЛЕПОРТИРУЕМ...", "&fстойте на месте");
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if (second != 0) {
					SexyActionBar.send(player, "&aосталось: " + second);
				}
				
				Location playerLocation = player.getLocation();
				
				if (!playerLocation.equals(location)) {
					SexyTitle.send(player, "&cПРОВАЛ", "&fстой на месте!");
					this.cancel();
					return;
				}
				
				if (second <= 0) {
					SexyTitle.send(player, "&aУСПЕШНО", "Вы были телепортированы");
					handle();
					this.cancel();
				}
				else {
					second--;
				}
			}
		}.runTaskTimer(plugin, 0L, 20L);
	}
	
	protected abstract void handle();
}