package de.construkter.glitzoriumSMP.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayTimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player  = (Player) commandSender;
        if (strings.length != 0 && Bukkit.getPlayer(strings[0]) != null) {
            player = Bukkit.getPlayer(strings[0]);
        }

        if (player == null) {
            player = (Player) commandSender;
        }
        int ticks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        int totalSeconds = ticks / 20;

        int days = totalSeconds / (24 * 60 * 60);
        int remainingSeconds = totalSeconds % (24 * 60 * 60);
        int hours = remainingSeconds / (60 * 60);

        String formattedTime = days + "d " + hours + "h ";

        player.sendMessage(ChatColor.DARK_AQUA + "Spielzeit von " + player.getName() + ": " + ChatColor.GOLD + formattedTime);
        return true;
    }
}
