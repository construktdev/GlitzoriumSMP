package de.construkter.glitzoriumSMP.release;

import de.construkter.glitzoriumSMP.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ConfirmStart extends Utils implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("glitzorium.smp.start")) {
            Bukkit.broadcastMessage(ChatColor.BLUE + "[VIP | LetsHugo] " + ChatColor.GOLD + " Ich wünsche euch viel Spaß beim spielen! Euer Hugo");
            sleep(2000);
            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "LetsHugo hat den " + ChatColor.GOLD + "" + ChatColor.BOLD + "GlitzoriumSMP " + ChatColor.DARK_AQUA + "verlassen!");
            sleep(1000);
            Objects.requireNonNull(commandSender.getServer().getWorld("world")).getWorldBorder().setSize(30, TimeUnit.SECONDS, 5);
            Objects.requireNonNull(commandSender.getServer().getWorld("world")).getWorldBorder().setSize(20000);
            PrepareStartCommand.isStarted = false;
            commandSender.sendMessage(ChatColor.GREEN + "Fully started!");
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return true;
    }
}