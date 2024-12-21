package de.construkter.glitzoriumSMP.helpop.listeners;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventLogger implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        GlitzoriumSMP.sendMessage("Death Logger", e.getDeathMessage());
    }
}
