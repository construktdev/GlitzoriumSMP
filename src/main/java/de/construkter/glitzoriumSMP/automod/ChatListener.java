package de.construkter.glitzoriumSMP.automod;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        for (String word : BadWords.getBadWords()) {
            if (message.toLowerCase().contains(word)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(BadWords.getWarnMessage(word));
            }
        }
    }
}
