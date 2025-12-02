package de.construkter.glitzoriumSMP.commands;

import de.construkter.glitzoriumSMP.dimensionLimit.DimensionEnableCommand;
import de.construkter.glitzoriumSMP.dimensionLimit.DimensionSwitchListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SimulateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("glitzorium.admin")) {
            if (strings.length == 0) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /simulate <end|endpublic>");
                return true;
            }

            if (strings[0].equalsIgnoreCase("end")) {
                DimensionEnableCommand.enableEnd(true);
            } else if (strings[0].equalsIgnoreCase("endpublic")) {
                DimensionEnableCommand.enableEnd(false);
            } else {
                commandSender.sendMessage(ChatColor.RED + "Usage: /simulate <end|endpublic>");
            }

            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
        return true;
    }
}
