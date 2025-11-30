package de.construkter.glitzoriumSMP.release;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventManager implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().hasPermission("glitzorium.admin")) {
            return;
        }
        if (!GlitzoriumSMP.isStarted) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().hasPermission("glitzorium.admin")) {
            return;
        }
        if (!GlitzoriumSMP.isStarted) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!GlitzoriumSMP.isStarted) {
            event.setCancelled(true);
        }
    }
}
