package de.construkter.glitzoriumSMP.automod;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.helpop.commands.MuteCommand;
import org.bukkit.Bukkit;
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
        // Anti Message Report
        List<Player> mutedPlayers = HelpOP.mutedPlayers;
        event.setCancelled(true);
        if (!mutedPlayers.contains(event.getPlayer())) {
            Bukkit.broadcastMessage("<" + event.getPlayer().getName() + "> " + event.getMessage());
        }
        // lol das wars schon wie einfach XD
        String message = event.getMessage();
        for (String word : BadWords.getBadWords()) {
            String[] words = message.split(" ");
            for (String word2 : words) {
                if (word2.toLowerCase().startsWith(word)) {
                    GlitzoriumSMP.sendMessage("AutoMod", "**" + event.getPlayer().getName() + "** hat ein verbotenes Wort gesagt: " + word);
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(BadWords.getWarnMessage(word));
                    if (warnings.containsKey(event.getPlayer())) {
                        warnings.put(event.getPlayer(), warnings.get(event.getPlayer()) + 1);
                    } else {
                        warnings.put(event.getPlayer(), 1);
                    }
                }
            }

        }

        HelpOP helpOP = new HelpOP();
        if (warnings.containsKey(event.getPlayer()) && warnings.get(event.getPlayer()) == 3) {
            helpOP.kick(event.getPlayer(), event.getPlayer(), "Du hast 3 mal ein verbotenes Wort gesagt. Automated by Glitzorium HelpOP");
        } else if (warnings.containsKey(event.getPlayer()) && warnings.get(event.getPlayer()) == 5) {
            helpOP.ban(event.getPlayer(), event.getPlayer(), "Du hast 5 mal ein verbotenes Wort gesagt. Automated by Glitzorium HelpOP");
        }
    }
}
