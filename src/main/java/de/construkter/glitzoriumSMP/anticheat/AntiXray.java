package de.construkter.glitzoriumSMP.anticheat;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class AntiXray implements Listener {

    static Map<Player, Integer> diamondsFound;

    @EventHandler
    public void onBlockMinedEvent(BlockBreakEvent event) {
        diamondsFound = new HashMap<>();
        if (event.getBlock().getType() == Material.DIAMOND_ORE || event.getBlock().getType() == Material.DEEPSLATE_DIAMOND_ORE) {
            diamondsFound.put(event.getPlayer(), diamondsFound.get(event.getPlayer()) + 1);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!diamondsFound.containsKey(player)) {
            diamondsFound.put(player, 0);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        diamondsFound.remove(player);
    }

    public static void checkDiamondsFound() {
        new BukkitRunnable() {
            public void run() {
                for (Map.Entry<Player, Integer> entry : diamondsFound.entrySet()) {
                    Player p = entry.getKey();
                    int value = entry.getValue();
                    HelpOP helpOP = new HelpOP();
                    if (value >= 13) {
                        helpOP.warn(p, (Player) Bukkit.getConsoleSender(), "xRay Verdacht (AntiCheat)");
                        GlitzoriumSMP.sendMessage("AntiCheat xRay", p.getName()  + " hat " + value + " diamonds in 500 Ticks gefunden!");
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission("smp.admin")) {
                                player.sendMessage(Prefix.helpOP() + ChatColor.DARK_AQUA + p.getName() + " wurde für xRay verdächtigt!");
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(GlitzoriumSMP.getInstance(), 0, 500);
    }
}
