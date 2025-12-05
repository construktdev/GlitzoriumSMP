package de.construkter.glitzoriumSMP.commands;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.Bukkit;
import org.bukkit.boss.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class TpsBarCommand implements CommandExecutor {

    private static final HashMap<UUID, BossBar> bars = new HashMap<>();


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) return true;

        UUID uuid = player.getUniqueId();

        if (!bars.containsKey(uuid)) {
            create(player, GlitzoriumSMP.getInstance());
            player.sendMessage("§cTPS Bar aktiviert");
        } else {
            remove(player);
            player.sendMessage("§aTPS Bar deaktiviert");
        }

        return true;
    }

    public static void create(Player player, Plugin plugin) {

        BossBar bar = Bukkit.createBossBar(
                "TPS",
                BarColor.GREEN,
                BarStyle.SOLID
        );

        bar.addPlayer(player);
        bar.setVisible(true);

        bars.put(player.getUniqueId(), bar);

        new BukkitRunnable() {
            @Override
            public void run() {

                if (!player.isOnline() || !bars.containsKey(player.getUniqueId())) {
                    bar.removeAll();
                    cancel();
                    return;
                }

                double tps = TPSCalculator.getTPS();
                double mspt = TPSCalculator.getMSPT();
                double ping = player.getPing();

                bar.setColor(getColor(tps));
                bar.setProgress(Math.min(tps / 20.0, 1.0));
                bar.setTitle(String.format(
                        "§fTPS: §a%.2f §7| §fMSPT: §b%.2f §7| §fPing: §e%.0fms",
                        tps,
                        mspt,
                        ping
                ));
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public static void remove(Player player) {
        BossBar bar = bars.remove(player.getUniqueId());
        if (bar != null) {
            bar.removePlayer(player);
            bar.setVisible(false);
        }
    }


    private static BarColor getColor(double tps) {
        if (tps >= 18) return BarColor.GREEN;
        if (tps >= 15) return BarColor.YELLOW;
        if (tps >= 10) return BarColor.RED;
        return BarColor.PURPLE;
    }
}

