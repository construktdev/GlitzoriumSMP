package de.construkter.glitzoriumSMP.utils;

import org.bukkit.ChatColor;

public class Prefix {
    public static String coinsPrefix(){
        return ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "" + ChatColor.BOLD + "Coin-System" + ChatColor.DARK_AQUA + "] ";
    }

    public static String PluginPrefix(){
        return ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "" + ChatColor.BOLD + "glitzorium.de" + ChatColor.DARK_AQUA + "] ";
    }

    public static String errorPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "[" + ChatColor.RED + "!" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "] ";
    }

    public static String adminMessage() {
        return errorPrefix() + ChatColor.RED + "" + ChatColor.BOLD + "[" + ChatColor.RED + "Official Admin Message" + ChatColor.RED + "" + ChatColor.BOLD + "] ";
    }

    public static String warn(){
        return errorPrefix() + ChatColor.RED + "" + ChatColor.BOLD + "[" + ChatColor.RED + "Warn" + ChatColor.RED + "" + ChatColor.BOLD + "] " + ChatColor.RED;
    }

    public static String logger(){
        return ChatColor.DARK_AQUA + "[Logger] " + ChatColor.RESET;
    }

    public static String helpOP(){
        return ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "" + ChatColor.BOLD + "Help-OP" + ChatColor.DARK_AQUA + "] ";

    }
}
