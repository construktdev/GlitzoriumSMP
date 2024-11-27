package de.construkter.glitzoriumSMP.helpop.commands;

import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class UnmuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("smp.helpop.unmute")) {
            List<Player> mutedPlayers = HelpOP.mutedPlayers;
            if (strings.length != 1) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /unmute <player>");
                return true;
            }
            if (mutedPlayers.contains(commandSender.getServer().getPlayer(strings[0]))) {
                mutedPlayers.remove(commandSender.getServer().getPlayer(strings[0]));
                commandSender.sendMessage(ChatColor.GREEN + "Der Spieler " + strings[0] + " wurde entmuted!");
                Objects.requireNonNull(commandSender.getServer().getPlayer(strings[0])).sendMessage(Prefix.PluginPrefix() + ChatColor.GREEN + "Du kannst nun wieder im Chat teilnehmen!");
                return true;
            } else {
                commandSender.sendMessage(ChatColor.RED + "Dieser Spieler ist nicht gemuted!");
                return true;
            }
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return true;
    }
}
