package de.construkter.glitzoriumSMP.utils;

import org.bukkit.Bukkit;

public class Utils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting for sleep");
        }
    }
    public static void log(String message) {
        Bukkit.getLogger().info(message);
    }
}
