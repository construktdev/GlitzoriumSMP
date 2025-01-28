package de.construkter.glitzoriumSMP.commands;

import de.construkter.glitzoriumSMP.antibot.UUIDManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class RemoveStatus implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        UUIDManager manager;

        try {
            manager = new UUIDManager("status.txt");
            if (manager.getUUID(commandSender.getName()) != null) {
                manager.removeUUID(commandSender.getName());
                commandSender.sendMessage(ChatColor.GREEN + "Du hast deinen Status gelöscht. Nutze /status <status> save um erneut einen zu speichern!");
            } else {
                commandSender.sendMessage(ChatColor.RED + "Nutze zuerst /status <status> save um einen Status zu speichern!");
            }
        } catch (IOException e) {
            commandSender.sendMessage("Es gab einen Fehler");
            Bukkit.getLogger().severe("Es gab einen Fehler: " + e.getMessage());
        }
        return true;
    }
}
