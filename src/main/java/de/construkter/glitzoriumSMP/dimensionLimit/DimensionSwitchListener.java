package de.construkter.glitzoriumSMP.dimensionLimit;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DimensionSwitchListener implements Listener {

    @EventHandler
    public void onDimensionChange(PlayerPortalEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL || event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
                if (!GlitzoriumSMP.netherEnabled) {
                    event.setCancelled(true);
                }
            }
        } else if (event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL || event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
                if (!GlitzoriumSMP.endEnabled) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
