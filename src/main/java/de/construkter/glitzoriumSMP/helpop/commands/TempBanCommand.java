package de.construkter.glitzoriumSMP.helpop.commands;

import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TempBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("gltzorium.smp.tempban")) {
            Player moderator = (Player) commandSender;
            Player target = Bukkit.getPlayer(strings[0]);

            if (strings[0].equalsIgnoreCase("help")) {
                commandSender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Zeitcodes:");
                commandSender.sendMessage(ChatColor.GOLD + "0: " + ChatColor.DARK_AQUA + "1 Minute");
                commandSender.sendMessage(ChatColor.GOLD + "1: " + ChatColor.DARK_AQUA + "10 Minuten");
                commandSender.sendMessage(ChatColor.GOLD + "2: " + ChatColor.DARK_AQUA + "30 Minuten");
                commandSender.sendMessage(ChatColor.GOLD + "3: " + ChatColor.DARK_AQUA + "1 Stunde");
                commandSender.sendMessage(ChatColor.GOLD + "4: " + ChatColor.DARK_AQUA + "3 Stunden");
                commandSender.sendMessage(ChatColor.GOLD + "5: " + ChatColor.DARK_AQUA + "6 Stunden");
                commandSender.sendMessage(ChatColor.GOLD + "6: " + ChatColor.DARK_AQUA + "12 Stunden");
                commandSender.sendMessage(ChatColor.GOLD + "7: " + ChatColor.DARK_AQUA + "1 Tag");
                commandSender.sendMessage(ChatColor.GOLD + "8: " + ChatColor.DARK_AQUA + "3 Tage");
                commandSender.sendMessage(ChatColor.GOLD + "9: " + ChatColor.DARK_AQUA + "1 Woche");
                commandSender.sendMessage(ChatColor.GOLD + "10: " + ChatColor.DARK_AQUA + "2 Wochen");
                commandSender.sendMessage(ChatColor.GOLD + "11: " + ChatColor.DARK_AQUA + "1 Monat");
                return true;
            }

            if (strings.length <= 2) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /tempban <user> <time> <Grund>");
                commandSender.sendMessage(ChatColor.RED + "Nutze /tempban help um ein Liste an Zeitcodes zu sehen");
                return true;
            }

            HelpOP helpOP = new HelpOP();
            long duration = 0;
            switch (strings[1]) {
                case "0":
                    duration = 60;
                case "1":
                    duration = 60;
                case "2":
                    duration = 1800;
                case "3":
                    duration = 3600;
                case "4":
                    duration = 10800;
                case "5":
                    duration = 21600;
                case "6":
                    duration = 43200;
                case "7":
                    duration = 86400;
                case "8":
                    duration = 259200;
                case "9":
                    duration = 604800;
                case "10":
                    duration = 1209600;
                case "11":
                    duration = 259200;


                StringBuilder reason = new StringBuilder();
                for (String string : strings) {
                    reason.append(string).append(" ");
                }
                String reasonString = reason.toString().replace(strings[0], "");
                reasonString = reasonString.replace("  ", " ");
                reasonString = reasonString.replace(strings[1], "");

                helpOP.tempBan(target, moderator, reasonString, duration);

                commandSender.sendMessage(ChatColor.GREEN + "Banned " + ChatColor.GOLD + strings[0] + ChatColor.GREEN + " for " + ChatColor.GOLD + reasonString + ", " + ChatColor.GOLD + duration);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("smp.helpop.ban")) {
                        if (!commandSender.getName().equals(player.getName())) {
                            player.sendMessage(Prefix.helpOP() + ChatColor.DARK_AQUA + commandSender.getName() + " hat " + strings[0] + "für " + ChatColor.GOLD + reasonString + " gebannt.");
                        }
                    }
                }
                return true;
            }
        }
        commandSender.sendMessage(ChatColor.RED + "Du hast dafür keine Berechtigung");
        return true;
    }
}
