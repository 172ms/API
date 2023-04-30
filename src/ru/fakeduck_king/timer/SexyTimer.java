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
	private int time;
	
	public SexyTimer(int second) {
		this.second = second;
	}
	
	public SexyTimer(Player player, int second) {
		this.player = player;
		this.second = second;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public void run(Plugin plugin) {
		this.time = this.second;
		Location location = player.getLocation();
		SexyTitle.send(player, "&aТелепортируем...", "стойте на месте");
		new BukkitRunnable() {
			@Override
			public void run() {
				if (time != 0) {
					SexyActionBar.send(player, "осталось: &a" + time);
				}
				Location playerLocation = player.getLocation();
				if (!playerLocation.equals(location)) {
					SexyTitle.send(player, "&cПРОВАЛ", "&fстой на месте!");
					this.cancel();
					return;
				}
				if (time <= 0) {
					SexyTitle.send(player, "&aУСПЕШНО", "вы были телепортированы");
					handle();
					this.cancel();
				}
				else {
					time--;
				}
			}
		}.runTaskTimer(plugin, 0L, 20L);
	}
	
	protected abstract void handle();
}