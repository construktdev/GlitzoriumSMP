package de.construkter.glitzoriumSMP.automod;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ClearWarnsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("smp.command.clearwarns")) {
            if (strings.length != 1) {
                commandSender.sendMessage("Usage: /clearwarns <player>");
                return true;
            }
            HashMap<Player, Integer> warnings = ChatListener.warnings;
            Player target = commandSender.getServer().getPlayer(strings[0]);
            if (target == null) {
                commandSender.sendMessage(ChatColor.RED + "Dieser Spieler existiert nicht!");
                return true;
            }
            if (warnings.containsKey(target)) {
                warnings.remove(target);
                commandSender.sendMessage(Prefix.helpOP() + ChatColor.GREEN + "Von dem angegebenen Spieler wurden alle Warnungen entfernt!");
                GlitzoriumSMP.sendMessage("Warnings Clear", "Der Spieler **" + target.getName() + "** hat alle Warnungen von **" + commandSender.getName() + "** entfernt bekommen!");
            }
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return true;
    }
}
