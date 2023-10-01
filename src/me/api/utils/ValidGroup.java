package me.api.utils;

import ru.fakeduck_king.messages.*;

public enum ValidGroup {
	DEVELOPER(0),
	ADMIN(0),
	YOUTUBE(0),
	CURATORBUILDER(0),
	BUILDER(0),
	CURATORMODERATOR(0),
	MODERATOR(0),
	HELPER(0),
	FLUX(Prefix.FLUX_COST),
	DIAMOND(Prefix.DIAMOND_COST),
	EMERALD(Prefix.EMERALD_COST),
	GOLD(Prefix.GOLD_COST),
	IRON(Prefix.IRON_COST),
	COAL(Prefix.COAL_COST),
	DEFAULT(0);

	private final int cost;

	private ValidGroup(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return this.cost;
	}
}