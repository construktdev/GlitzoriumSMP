package de.construkter.glitzoriumSMP.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WhosOnline implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("smp.admin")) {
            commandSender.sendMessage(ChatColor.GOLD + "------ Online Players ------");
            int i = 1;
            for (Player player : Bukkit.getOnlinePlayers()) {
                commandSender.sendMessage(ChatColor.GOLD + String.valueOf(i) + ": " + ChatColor.RED + player.getName());
                i++;
            }
            commandSender.sendMessage(ChatColor.GOLD + "Total Players: " + Bukkit.getOnlinePlayers().size());
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return true;
    }
}
