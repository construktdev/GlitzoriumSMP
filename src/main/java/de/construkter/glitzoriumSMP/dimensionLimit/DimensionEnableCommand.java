package de.construkter.glitzoriumSMP.dimensionLimit;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.managers.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DimensionEnableCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("glitzoriumSMP.dimensionLimit.enable")) {
            FileManager fileManager = new FileManager("config", "");

            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("nether")) {
                    fileManager.getFileConfiguration().set("nether", "true");
                    fileManager.saveFile();
                    GlitzoriumSMP.netherEnabled = true;
                    commandSender.sendMessage(ChatColor.GREEN + "Nether Enabled");
                } else if (strings[0].equalsIgnoreCase("end")) {
                    fileManager.getFileConfiguration().set("end", "true");
                    fileManager.saveFile();
                    GlitzoriumSMP.endEnabled = true;
                    commandSender.sendMessage(ChatColor.GREEN + "End Enabled");
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Usage: /enable <end|nether>");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "Usage: /enable <end|nether>");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "Du darfst diesen Befehl nicht nutzen");
        }
        return true;
    }
}
