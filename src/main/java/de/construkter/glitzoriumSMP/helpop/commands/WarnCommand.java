package de.construkter.glitzoriumSMP.helpop.commands;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.antibot.UUIDManager;
import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class WarnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("smp.helpop.warn")) {
            if (strings.length == 1 || strings.length == 0) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /warn <player> <reason>");
                return true;
            }
            HelpOP helpop = GlitzoriumSMP.getHelpop();
            StringBuilder reason = new StringBuilder();
            for (String string : strings) {
                reason.append(string).append(" ");
            }
            String reasonString = reason.toString().replace(strings[0], "");
            reasonString = reasonString.replace("  ", " ");
            helpop.warn(Objects.requireNonNull(Bukkit.getPlayer(strings[0])), (Player) commandSender, reasonString);
            commandSender.sendMessage(ChatColor.GREEN + "Warned " + ChatColor.GOLD + strings[0] + ChatColor.GREEN + " for " + ChatColor.GOLD + reasonString);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("smp.helpop.ban")) {
                    if (!commandSender.getName().equals(player.getName())) {
                        player.sendMessage(Prefix.helpOP() + ChatColor.DARK_AQUA + commandSender.getName() + " hat " + strings[0] + "für " + ChatColor.GOLD + reasonString + " gewarnt.");
                    }
                }
            }
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return true;
    }
}
