package de.construkter.glitzoriumSMP.commands;

import net.dv8tion.jda.api.utils.data.DataArray;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        int ping = player.getPing();


        player.sendMessage(ChatColor.DARK_AQUA + "Ping: " + ChatColor.GOLD + ping + "ms");
        return true;
    }
}
