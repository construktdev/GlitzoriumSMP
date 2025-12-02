package de.construkter.glitzoriumSMP.commandLimiter;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;


public class PreProcess implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        int limit = GlitzoriumSMP.commandLimits.getAmount(player);

        if (event.getMessage().split(" ")[0].equalsIgnoreCase("/co") ||
                event.getMessage().split(" ")[0].equalsIgnoreCase("/coreprotect") ||
                event.getMessage().split(" ")[0].equalsIgnoreCase("/co-glitzox:co") ||
                event.getMessage().split(" ")[0].equalsIgnoreCase("/co-glitzox:coreprotect") ||
                event.getMessage().split(" ")[0].equalsIgnoreCase("/co-glitzox:core") ||
                event.getMessage().split(" ")[0].equalsIgnoreCase("/core")){
            if (limit >= 6) {
                player.sendMessage(ChatColor.RED + "Du hast dein tägliches CoreProtect Limit erreicht!");
                event.setCancelled(true);
            } else {
                GlitzoriumSMP.commandLimits.add(player, 1);
            }
        }
    }
}
