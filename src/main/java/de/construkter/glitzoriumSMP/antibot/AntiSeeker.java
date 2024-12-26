package de.construkter.glitzoriumSMP.antibot;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Objects;

public class AntiSeeker implements Listener {
    String[] seekersIp = {"8.53.150.111"};
    String[] seekersName = {"server", "seeker", "finder"};

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        for (String adress : seekersIp) {
            if (!Objects.requireNonNull(event.getPlayer().getAddress()).getAddress().getHostAddress().equals(adress)) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Bitte nutze den offiziellen glitzorium.de Proxy");
                GlitzoriumSMP.sendMessage("Proxy Protection", event.getPlayer().getName() + " veruschte über andere Proxies zu joinen");
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
