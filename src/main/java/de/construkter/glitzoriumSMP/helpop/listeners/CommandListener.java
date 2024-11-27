package de.construkter.glitzoriumSMP.helpop.listeners;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.event.EventHandler;

public class CommandListener implements org.bukkit.event.Listener{
    @EventHandler
    public void onCommand(org.bukkit.event.player.PlayerCommandPreprocessEvent event) {
        GlitzoriumSMP.sendMessage("Command Logger", "**" + event.getPlayer().getName() + "** hat den Command `" + event.getMessage() + "` ausgeführt");
    }
}
