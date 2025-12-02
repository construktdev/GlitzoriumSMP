package de.construkter.glitzoriumSMP.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("glitzorium.admin")) {
            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }
        if (strings.length != 1) {
            commandSender.sendMessage(ChatColor.RED + "Usage: /ec <player>");
            return true;
        }
        Player target = Bukkit.getPlayer(strings[0]);
        Player admin = (Player) commandSender;

        if (target == null) {
            target = Bukkit.getPlayer(strings[0]);
        }
        if (target == null) {
            commandSender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        admin.openInventory(target.getEnderChest());

        return true;
    }
}
