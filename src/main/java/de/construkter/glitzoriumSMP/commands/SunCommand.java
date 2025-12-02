package de.construkter.glitzoriumSMP.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SunCommand extends CommandForward implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("glitzorium.admin")) {
            forward("weather", "clear");
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
        return false;
    }
}
