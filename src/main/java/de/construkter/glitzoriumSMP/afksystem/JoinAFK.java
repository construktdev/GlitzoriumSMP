package de.construkter.glitzoriumSMP.afksystem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class JoinAFK implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        List<Player> afkPlayers = AFKCommand.afkPlayers;
        int afkPlayersSize = afkPlayers.size();
        if (afkPlayersSize == 1) {
            //player.sendMessage(ChatColor.DARK_AQUA + "Aktuell ist " + ChatColor.GOLD + afkPlayers.getFirst().getName() + ChatColor.DARK_AQUA + " AFK!");
        } else {
            //player.sendMessage(ChatColor.DARK_AQUA + "Aktuell sind folgende Spieler AFK: ");
            int i = 0;
            for (Player afkPlayer : afkPlayers) {
               // player.sendMessage(ChatColor.DARK_AQUA + String.valueOf(i) + ChatColor.GOLD + afkPlayer.getName());
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (AFKCommand.afkPlayers.contains(player)) {
            AFKCommand.afkPlayers.remove(player);
        }
        if (AutoAFK.lastMoves.containsKey(player)) {
            AutoAFK.lastMoves.remove(player);
        }
        if (AutoAFK.lastInteract.containsKey(player)) {
            AutoAFK.lastInteract.remove(player);
        }
    }
}
