package de.construkter.glitzoriumSMP.release;

import de.construkter.glitzoriumSMP.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ConfirmStart extends Utils implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("glitzorium.smp.start")) {
            for (Player p : Objects.requireNonNull(commandSender.getServer().getOnlinePlayers())) {
                if (!(p.hasPermission("glitzorium.smp.prepare.gamemode"))) {
                    p.setGameMode(GameMode.SURVIVAL);
                }
            }
            Bukkit.broadcastMessage(ChatColor.BLUE + "[VIP | LetsHugo] " + ChatColor.GOLD + " Ich wünsche euch viel Spaß beim spielen! Euer Hugo");
            sleep(2000);
            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "LetsHugo hat den " + ChatColor.GOLD + "" + ChatColor.BOLD + "GlitzoriumSMP " + ChatColor.DARK_AQUA + "verlassen!");
            sleep(1000);
            Objects.requireNonNull(commandSender.getServer().getWorld("world")).getWorldBorder().setSize(30, TimeUnit.SECONDS, 5);
            Objects.requireNonNull(commandSender.getServer().getWorld("world")).getWorldBorder().setSize(20000);
            commandSender.sendMessage(ChatColor.GREEN + "Fully started!");
            PrepareStartCommand.isStarted = true;
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return true;
    }
}
