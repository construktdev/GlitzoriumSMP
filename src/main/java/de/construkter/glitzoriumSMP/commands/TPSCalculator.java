package de.construkter.glitzoriumSMP.commands;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TPSCalculator {

    private static final int UPDATE_INTERVAL = 20; // Ticks (1 Sekunde)
    private static long lastTime = System.nanoTime();
    private static double tps = 20.0;

    public static void onTick() {
        long now = System.nanoTime();
        long diff = now - lastTime;

        lastTime = now;

        double seconds = diff / 1_000_000_000.0;
        double currentTPS = UPDATE_INTERVAL / seconds;

        tps = Math.min(20.0, currentTPS);
    }

    public static double getTPS() {
        return tps;
    }

    public static double getMSPT() {
        return 1000.0 / tps;
    }

    public static void start(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                TPSCalculator.onTick();
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
