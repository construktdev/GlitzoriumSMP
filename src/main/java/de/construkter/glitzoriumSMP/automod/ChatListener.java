package de.construkter.glitzoriumSMP.automod;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.HelpOP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class ChatListener implements Listener {
    public static HashMap<Player, Integer> warnings = new HashMap<>();
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        for (String word : BadWords.getBadWords()) {
            if (message.toLowerCase().contains(word)) {
                GlitzoriumSMP.sendMessage("AutMod", "**" + event.getPlayer().getName() + "** hat ein verbotenes Wort gesagt: " + word);
                event.setCancelled(true);
                event.getPlayer().sendMessage(BadWords.getWarnMessage(word));
                if (warnings.containsKey(event.getPlayer())) {
                    warnings.put(event.getPlayer(), warnings.get(event.getPlayer()) + 1);
                } else {
                    warnings.put(event.getPlayer(), 1);
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
