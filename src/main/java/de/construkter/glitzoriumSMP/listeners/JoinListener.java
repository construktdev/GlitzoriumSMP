package de.construkter.glitzoriumSMP.listeners;


import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.DARK_AQUA + player.getName() + " hat den " + ChatColor.GOLD + "" + ChatColor.BOLD + "GlitzoriumSMP " + ChatColor.DARK_AQUA + "betreten!");
        if (player.hasPermission("construkter.smp.glitzorium.admin")) {
            player.sendMessage(Prefix.PluginPrefix() + Prefix.adminMessage() + ChatColor.DARK_GREEN + "Admin Dashboard: https://panel.construkter.de/smp");
        }
    }
}
