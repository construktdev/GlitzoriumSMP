package de.construkter.glitzoriumSMP.helpop.listeners;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class EventLogger implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        GlitzoriumSMP.sendMessage("Death Logger", e.getDeathMessage());
        Bukkit.getLogger().info(e.getEntity().getName() + " died at " + e.getEntity().getLocation() + " by " + e.getEntity().getKiller() + " and dropped " + e.getDroppedExp() + " XP");
    }

    @EventHandler
    public void onGamemodeSwitch(PlayerGameModeChangeEvent event) {
        GlitzoriumSMP.sendMessage("Gamemode Logger", "**" + event.getPlayer().getName() + "** changed gamemode to **" + event.getNewGameMode().name() + "**");
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        GlitzoriumSMP.sendMessage("Command Logger", "**" + e.getPlayer().getName() + "** executed command: " + e.getMessage());
    }
}
