package de.construkter.glitzoriumSMP.commandLimiter;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandLimits {
    private final Map<Player, Integer> playerLimits = new HashMap<>();

    public void add(Player player, int amount) {
        if (playerLimits.containsKey(player)) {
            playerLimits.put(player, playerLimits.get(player) + amount);
        } else {
            playerLimits.put(player, amount);
        }
    }

    public void remove(Player player) {
        playerLimits.remove(player);
    }

    public int getAmount(Player player) {
        return playerLimits.getOrDefault(player, 0);
    }
}
