package de.construkter.glitzoriumSMP.whitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddWhitelist implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("glitzorium.whitelist.add")) {
            if (strings.length != 1) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /playeradd <name>");
                return false;
            }
            commandSender.sendMessage(ChatColor.GREEN + WhitelistManager.addPlayer(strings[0]));
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
        return false;
    }
}
