package ru.fakeduck_king.utils;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.tehkode.permissions.PermissionUser;
import org.bukkit.entity.Player;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("deprecation")
public class SexySortDonation {
    public static void sort(List<Player> players) {
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player player, Player player2) {
                PermissionUser permissionUser = PermissionsEx.getUser(player);
                PermissionUser permissionUser2 = PermissionsEx.getUser(player2);
                int donation1 = SexySortDonation.get(permissionUser);
                int donation2 = SexySortDonation.get(permissionUser2);

                if (donation1 != donation2) {
                    return Integer.compare(donation2, donation1);
                } else {
                    return player.getName().compareToIgnoreCase(player2.getName());
                }
            }
        });
    }
    
	private static int get(PermissionUser permissionUser) {
		String[] groups = permissionUser.getGroupsNames();
		for (String group : groups) {
			if (group.equalsIgnoreCase("DEFAULT")) {
				continue;
			}
			if (group.equalsIgnoreCase("COAL")) {
				return 1;
			}
			if (group.equalsIgnoreCase("IRON")) {
				return 2;
			}
			if (group.equalsIgnoreCase("GOLD")) {
				return 3;
			}
			if (group.equalsIgnoreCase("EMERALD")) {
				return 4;
			}
			if (group.equalsIgnoreCase("DIAMOND")) {
				return 5;
			}
			if (group.equalsIgnoreCase("FLUX")) {
				return 6;
			}
			if (group.equalsIgnoreCase("HELPER")) {
				return 7;
			}
			if (group.equalsIgnoreCase("MODERATOR")) {
				return 8;
			}
			if (group.equalsIgnoreCase("CURATORMODERATOR")) {
				return 9;
			}
			if (group.equalsIgnoreCase("BUILDER")) {
				return 10;
			}
			if (group.equalsIgnoreCase("CURATORBUILDER")) {
				return 11;
			}
			if (group.equalsIgnoreCase("ADMIN")) {
				return 12;
			}
			if (group.equalsIgnoreCase("DEVELOPER")) {
				return 13;
			}
		}
		return 0;
	}
}