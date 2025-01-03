package de.construkter.glitzoriumSMP.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GamemodeCommand extends CommandForward implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("smp.command.forward")) {
            if (strings.length != 2) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /gamemode <0/1/2/3> <player>");
            }
            switch (strings[0]) {
                case "0":
                    forward("gamemode", "survival " + strings[1]);
                    break;
                case "1":
                    forward("gamemode", "creative " + strings[1]);
                    break;
                case "2":
                    forward("gamemode", "adventure " + strings[1]);
                    break;
                case "3":
                    forward("gamemode", "spectator " + strings[1]);
                    break;
                default:
                    commandSender.sendMessage(ChatColor.RED + "Unknown gamemode " + ChatColor.GOLD + strings[0]);
                    break;
            }
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
        return true;
    }
}
