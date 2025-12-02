package de.construkter.glitzoriumSMP.dimensionLimit;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.managers.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
                    enableEnd(false);
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

    public static void enableEnd(boolean test) {
        if (test) {
            Player construkter = GlitzoriumSMP.getInstance().getServer().getPlayer("Construkter");
            if (construkter != null && construkter.isOnline()) {
                construkter.sendMessage(ChatColor.GOLD + "Das End ist nun eröffnet!");
                construkter.sendTitle(ChatColor.GOLD + "End Event", ChatColor.YELLOW + "Das End ist nun verfügbar!", 10, 70, 20);
                construkter.playSound(construkter.getLocation(), Sound.ENTITY_WITHER_DEATH, 10, 1);
            }
        } else {
            GlitzoriumSMP.getInstance().getServer().broadcastMessage(ChatColor.GOLD + "Das End ist nun eröffnet!");
            for (Player player : GlitzoriumSMP.getInstance().getServer().getOnlinePlayers()) {
                player.sendTitle(ChatColor.GOLD + "End Event", ChatColor.YELLOW + "Das End ist nun verfügbar!", 10, 70, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 10, 1);
            }
        }
    }
}
