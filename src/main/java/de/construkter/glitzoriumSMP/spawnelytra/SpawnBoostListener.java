package de.construkter.glitzoriumSMP.spawnelytra;

import de.construkter.glitzoriumSMP.release.PrepareStartCommand;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.KeybindComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpawnBoostListener implements Listener {

    private final int multiplyValue;
    private final int spawnRadius;
    private final List<Player> flying = new ArrayList<>();
    private final List<Player> boosted = new ArrayList<>();
    public SpawnBoostListener(Plugin plugin) {
        this.multiplyValue = 3;
        this.spawnRadius = 30;
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            Objects.requireNonNull(Bukkit.getWorld("world")).getPlayers().forEach(player -> {
                if (player.getGameMode() != GameMode.SURVIVAL) return;
                player.setAllowFlight(isInSpawnRadius(player));
                if (flying.contains(player) && !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.setGliding(false);
                    boosted.remove(player);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        flying.remove(player);
                    }, 5);
                }
            });
        }, 0, 3);
    }

    @EventHandler
    public void onPlayerDoubleJump(PlayerToggleFlightEvent event) {
        if (!PrepareStartCommand.isStarted) return;
        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        if (!isInSpawnRadius(event.getPlayer())) return;
        event.setCancelled(true);
        event.getPlayer().setGliding(true);
        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new ComponentBuilder("Drücke ")
                        .append(new KeybindComponent("key.swapOffhand"))
                        .append(" um dich zu boosten.")
                        .create());
        flying.add(event.getPlayer());
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntityType() != EntityType.PLAYER && (event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) && flying.contains(event.getEntity())) event.setCancelled(true);
    }

    @EventHandler
    public void onPLayerSwapItems(PlayerSwapHandItemsEvent event) {
        if (!event.getPlayer().isGliding()) return;
        if (!event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) return;
        if (boosted.contains(event.getPlayer())) return;
        if (event.getPlayer().getInventory().contains(Material.ELYTRA)) return;
        event.setCancelled(true);
        boosted.add(event.getPlayer());

        event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(multiplyValue));
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 100, 1);
    }

    @EventHandler
    public void onEntityToggleFlight(EntityToggleGlideEvent event) {
        if (event.getEntityType() == EntityType.PLAYER && flying.contains(event.getEntity())) event.setCancelled(true);
    }

    private boolean isInSpawnRadius(Player player) {
        if (!player.getWorld().getName().equals("world")) return false;
        return player.getWorld().getSpawnLocation().distance(player.getLocation()) <= spawnRadius;
    }
}
