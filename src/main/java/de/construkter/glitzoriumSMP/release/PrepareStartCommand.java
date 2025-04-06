package de.construkter.glitzoriumSMP.release;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PrepareStartCommand implements CommandExecutor {
    public static boolean isStarted = true;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(ChatColor.DARK_RED + "SMP is already started!");
        return true;
    }
}
