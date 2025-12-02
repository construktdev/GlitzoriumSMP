package de.construkter.glitzoriumSMP.commands;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrollCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("glitzorium.admin")) {
            if (strings.length == 0 || strings.length == 1)  {
                commandSender.sendMessage(ChatColor.RED + "You need to specify a message and a target player.");
                return true;
            }
            StringBuilder args = new StringBuilder();
            for (String string : strings) {
                args.append(string);
            }
            String message = args.toString();
            message = message.replace(strings[0], "");
            message = message.replace("  ", " ");
            Player target = Bukkit.getPlayer(strings[0]);
            if (target == null) {
                commandSender.sendMessage(ChatColor.RED + "Player " + strings[0] + " not found.");
                return true;
            }
            for (int i = 0; i <= 20; i++) {
                target.sendMessage(message);
                target.sendTitle(message, message, 10, 20, 10);
                target.playSound(target.getLocation(), Sound.BLOCK_BELL_USE, 1f, 1f);
                target.playSound(target.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 1f, 1f);
            }
            commandSender.sendMessage(ChatColor.GREEN + "Erfolgreich");
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
        return true;
    }
}
