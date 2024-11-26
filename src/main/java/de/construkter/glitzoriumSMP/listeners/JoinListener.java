package de.construkter.glitzoriumSMP.listeners;


import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Arrays;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.hasPermission("smp.admin")) {
                GlitzoriumSMP.admins.add(player);
            }
        }
        Bukkit.getLogger().info(Arrays.toString(GlitzoriumSMP.admins.toArray()));
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.DARK_AQUA + player.getName() + " hat den " + ChatColor.GOLD + "" + ChatColor.BOLD + "GlitzoriumSMP " + ChatColor.DARK_AQUA + "betreten!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.DARK_AQUA + player.getName() + " hat den " + ChatColor.GOLD + "" + ChatColor.BOLD + "GlitzoriumSMP " + ChatColor.DARK_AQUA + "verlassen!");
    }
}
