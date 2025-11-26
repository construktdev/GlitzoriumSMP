package de.construkter.glitzoriumSMP.commandLimiter;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ResetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("glitzoriumSMP.reset")) {
            commandSender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return  true;
        }

        if (strings.length != 1) {
            commandSender.sendMessage(ChatColor.RED + "Usage: /reset <Player>");
            return true;
        }

        Player player = commandSender.getServer().getPlayer(strings[0]);
        if (player == null) {
            commandSender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        CommandLimits cl = GlitzoriumSMP.commandLimits;
        cl.remove(player);

        commandSender.sendMessage(ChatColor.GREEN + "Command limits have been reset for " + ChatColor.BOLD + player.getName() + ChatColor.GREEN + ".");
        return true;
    }
}
