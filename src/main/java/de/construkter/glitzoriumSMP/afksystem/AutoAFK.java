package de.construkter.glitzoriumSMP.afksystem;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.antibot.UUIDManager;
import de.construkter.glitzoriumSMP.tablist.Prefix;
import de.construkter.glitzoriumSMP.tablist.StatusCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AutoAFK implements Listener {

    static Map<Player, Long> lastMoves = new HashMap<>();
    static Map<Player, Long> lastInteract = new HashMap<>();
    List<Player> afkPlayers = AFKCommand.afkPlayers;
    UUIDManager manager = new UUIDManager("status.txt");

    public AutoAFK() throws IOException {
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) throws IOException {
        if (afkPlayers.contains(event.getPlayer())) {
            afkPlayers.remove(event.getPlayer());
            event.getPlayer().sendMessage(ChatColor.GRAY + "Du bist nun nicht mehr AFK!");
            if (manager.getUUID(event.getPlayer().getName()) != null) {
                Prefix.setPLayerPrefix(event.getPlayer(), manager.getUUID(event.getPlayer().getName()));
            } else {
                Prefix.setPLayerPrefix(event.getPlayer(), StatusCommand.playerStatus.getOrDefault(event.getPlayer(), ""));
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) throws IOException {
        if (afkPlayers.contains(event.getPlayer())) {
            afkPlayers.remove(event.getPlayer());
            event.getPlayer().sendMessage(ChatColor.GRAY + "Du bist nun nicht mehr AFK!");
            if (manager.getUUID(event.getPlayer().getName()) != null) {
                Prefix.setPLayerPrefix(event.getPlayer(), manager.getUUID(event.getPlayer().getName()));
            } else {
                Prefix.setPLayerPrefix(event.getPlayer(), StatusCommand.playerStatus.getOrDefault(event.getPlayer(), ""));
            }
        }
    }

    public static void startAutoAFK() {
        new BukkitRunnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    long moveDifference = System.currentTimeMillis() - lastMoves.get(player);
                    long interactDifference = System.currentTimeMillis() - lastInteract.get(player);
                    if (moveDifference > 60000 && interactDifference > 60000) {
                        if (AFKCommand.afkPlayers.contains(player)) {return;}
                        AFKCommand.afkPlayers.add(player);
                        player.sendMessage(ChatColor.GRAY + "Du bist nun AFK!");
                         Prefix.setPLayerPrefix(player, "&7[&8AFK&7] ");
                    }
                }
            }
        }.runTaskTimer(GlitzoriumSMP.getInstance(), 0, 600);
    }
}
