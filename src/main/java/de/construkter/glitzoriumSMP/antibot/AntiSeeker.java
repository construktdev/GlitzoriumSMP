package de.construkter.glitzoriumSMP.antibot;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Objects;

public class AntiSeeker implements Listener {
    String[] seekersIp = {"154.213."};
    String[] seekersName = {"server", "seeker", "finder"};

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        for (String adress : seekersIp) {
            if (Objects.requireNonNull(event.getPlayer().getAddress()).toString().startsWith( adress)) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Deine IP Adresse ist aus Verdacht auf einen Bot/Seeker gesperrt.");
                GlitzoriumSMP.sendMessage("Bot Protection", "IP Adresse: " + event.getPlayer().getAddress().toString() + " (" + event.getPlayer().getName() + ") wurde gesperrt.\nGrund: Verdacht auf Bot/Seeker");
            }
        }
        for (String name : seekersName) {
            if (event.getPlayer().getName().toLowerCase().contains(name)) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Dein Name ist aus Verdacht auf einen Bot/Seeker gesperrt.");
                GlitzoriumSMP.sendMessage("Bot Protection", "Name: " + event.getPlayer().getName() + " wurde gesperrt.\nGrund: Verdacht auf Bot/Seeker");
            }
        }
    }
}
