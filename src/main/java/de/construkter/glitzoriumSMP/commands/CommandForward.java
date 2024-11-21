package de.construkter.glitzoriumSMP.commands;

import org.bukkit.Bukkit;

public class CommandForward {
    public static void forward(String command, String args) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command + " " + args);
    }
}
