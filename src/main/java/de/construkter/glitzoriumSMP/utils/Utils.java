package de.construkter.glitzoriumSMP.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
