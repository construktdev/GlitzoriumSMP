package de.construkter.glitzoriumSMP.helpop.listeners;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        List<Player> mutedPlayers = HelpOP.mutedPlayers;
        if (mutedPlayers.contains(player)) {
            player.sendMessage(Prefix.PluginPrefix() + ChatColor.RED + "Du bist gemuted! Bitte warte bis du entmuted wird.");
            GlitzoriumSMP.sendMessage("Chat", "**" + player.getName() + "** hat trotz mute versucht zu chatten. Nachricht: " + event.getMessage());
            event.setCancelled(true);
            return;
        }
        GlitzoriumSMP.logChatMessage(event.getMessage(), event.getPlayer());
    }
}
