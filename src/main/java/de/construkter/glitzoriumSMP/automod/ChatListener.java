package de.construkter.glitzoriumSMP.automod;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.helpop.commands.MuteCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.List;

public class ChatListener implements Listener {
    public static HashMap<Player, Integer> warnings = new HashMap<>();
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        List<Player> mutedPlayers = HelpOP.mutedPlayers;
        boolean allowed = true;
        event.setCancelled(true);

        String message = event.getMessage();
                if (ChatFilter.containsBadLanguage(message)) {
                    GlitzoriumSMP.sendMessage("AutoMod", "**" + event.getPlayer().getName() + "** hat ein verbotenes Wort gesagt");
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "[AutoMod] " + ChatColor.DARK_RED + "Du hast ein verbotenes Wort verwendet!");
                    if (warnings.containsKey(event.getPlayer())) {
                        warnings.put(event.getPlayer(), warnings.get(event.getPlayer()) + 1);
                    } else {
                        warnings.put(event.getPlayer(), 1);
                    }
                    allowed = false;
                }

        if (!mutedPlayers.contains(event.getPlayer()) && allowed) {
            Bukkit.broadcastMessage("<" + event.getPlayer().getName() + "> " + event.getMessage());
        }

        HelpOP helpOP = new HelpOP();
        if (warnings.containsKey(event.getPlayer()) && warnings.get(event.getPlayer()) == 3 && !allowed) {
            helpOP.kick(event.getPlayer(), event.getPlayer(), "Du hast 3 mal ein verbotenes Wort gesagt.");
        } else if (warnings.containsKey(event.getPlayer()) && warnings.get(event.getPlayer()) == 5 && !allowed) {
            helpOP.ban(event.getPlayer(), event.getPlayer(), "Du hast 5 mal ein verbotenes Wort gesagt.\nBitte achte auf deine Sprache.");
        }
    }
}
