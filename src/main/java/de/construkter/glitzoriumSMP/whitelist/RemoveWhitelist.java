package de.construkter.glitzoriumSMP.whitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RemoveWhitelist implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("glitzorium.whitelist.remove")) {
            if (strings.length != 1) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /playerremove <name>");
                return false;
            }
            commandSender.sendMessage(ChatColor.GREEN + WhitelistManager.removePlayer(strings[0]));
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
        return false;
    }
}
