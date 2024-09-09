package de.construkter.glitzoriumSMP.logger;

import de.construkter.glitzoriumSMP.utils.Admins;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class IngameLogger implements Listener {

    String[] admins = Admins.get();
    Player con = Bukkit.getPlayer(admins[0]);
    Player killer = Bukkit.getPlayer(admins[1]);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        con.sendMessage(Prefix.logger() + "Player " + event.getPlayer().getName() + " joined");
        killer.sendMessage(Prefix.logger() + "Player " + event.getPlayer().getName() + " joined");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        con.sendMessage(Prefix.logger() + "Player " + event.getPlayer().getName() + " quit");
        killer.sendMessage(Prefix.logger() + "Player " + event.getPlayer().getName() + " quit");
    }
}