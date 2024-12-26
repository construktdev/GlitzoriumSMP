package de.construkter.glitzoriumSMP.antibot;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;

public class AntiRaid implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) throws IOException {
        UUIDManager manager = new UUIDManager("uuids.txt");
        Player player = event.getPlayer();
        String savedUUID = manager.getUUID(player.getName());
        if (savedUUID == null) {
            manager.addUUID(player.getName(), player.getUniqueId().toString());
        } else {
            if (!(savedUUID.equals(player.getUniqueId().toString()))) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Deine UUID stimmt nicht mit der zuvor gespeicherten überein. \n Wenn du denkst dass dies ein Fehler ist, melde dich bei Construkter");
                GlitzoriumSMP.sendMessage("UUID Logger", event.getPlayer().getName() + " wurde gekickt, da seine UUID nicht übereinstimmt.");
            }
        }
    }
}
