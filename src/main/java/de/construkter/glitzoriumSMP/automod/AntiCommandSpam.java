package de.construkter.glitzoriumSMP.automod;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class AntiCommandSpam implements Listener {

    public static HashMap<Player, Integer> commandExecutions;

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (commandExecutions == null) {
            commandExecutions = new HashMap<>();
        }
        Player player = event.getPlayer();
        if (commandExecutions.containsKey(player)) {
            commandExecutions.put(player, commandExecutions.get(player) + 1);
        } else {
            commandExecutions.put(player, 1);
        }
    }

    public static void check(GlitzoriumSMP plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Map.Entry<Player, Integer> entry : commandExecutions.entrySet()) {
                    // Bukkit.getLogger().info(entry.getKey().getName() + ": " + entry.getValue());
                    if (entry.getValue() >= 4) {
                        punish(entry.getKey(), entry.getValue());
                    }
                    commandExecutions.put(entry.getKey(), 0);
                }
            }
        }.runTaskTimer(plugin, 0, 100);
    }

    private static void punish(Player player, int amount) {
        player.kickPlayer(ChatColor.RED + "Du spammst Commands!\nDu hast " + amount + " Commands zu viel ausgeführt!");
        GlitzoriumSMP.sendMessage("AntiCommandSpam", player.getName() + " hat " + amount + " Commands zu viel ausgeführt!");
    }
}
