package de.construkter.glitzoriumSMP.release;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.common.reflection.qual.GetMethod;

import java.util.Date;
import java.util.Objects;

public class PrepareStartCommand implements CommandExecutor {
    public static boolean isStarted;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("glitzorium.smp.prepare")) {
            if (strings.length != 3) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /prepare <SpawnX> <SpawnY> <SpawnZ>");
                return true;
            }
            PrepareStartCommand.isStarted = true;
            long startTime = System.currentTimeMillis();
            int blockX = Integer.parseInt(strings[0]);
            int blockY = Integer.parseInt(strings[1]);
            int blockZ = Integer.parseInt(strings[2]);
            Objects.requireNonNull(commandSender.getServer().getWorld("world")).getWorldBorder().setCenter(blockX, blockZ);
            Objects.requireNonNull(commandSender.getServer().getWorld("world")).getWorldBorder().setSize(20);
            commandSender.sendMessage(ChatColor.GREEN + "Prepared world border!");
            for (Player p : Objects.requireNonNull(commandSender.getServer().getOnlinePlayers())) {
                p.teleport(new Location(commandSender.getServer().getWorld("world"), blockX, blockY, blockZ));
            }
            for (Player p : Objects.requireNonNull(commandSender.getServer().getOnlinePlayers())) {
                if (!(p.hasPermission("glitzorium.smp.prepare.gamemode"))) {
                    p.setGameMode(GameMode.SURVIVAL);
                }
            }
            long endTime = System.currentTimeMillis();
            commandSender.sendMessage(ChatColor.GREEN + "Start prepared in " + ChatColor.GOLD + (endTime - startTime) + "ms");
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return false;
    }
}
