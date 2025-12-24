package de.construkter.glitzoriumSMP.commands;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.HelpOPSender;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TPSMonitor implements CommandExecutor {

    HashMap<Player, BukkitRunnable> players = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("glitzorium.admin")) {
            if (!(commandSender instanceof Player player)) {
                commandSender.sendMessage("This command can only be used by players.");
                return true;
            }

            if (!players.containsKey(player)) {

                BukkitRunnable runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        HelpOPSender sender = new HelpOPSender(player.getName());
                        Bukkit.dispatchCommand(sender, "tps");
                        String tps = sender.getOutput();
                        player.sendMessage(tps);
                    }
                };

                runnable.runTaskTimer(GlitzoriumSMP.getInstance(), 0L, 20 * 10L);
                players.put(player, runnable);
            } else {
                players.get(player).cancel();
                players.remove(player);
                player.sendMessage("§aTPS Monitor deaktiviert");
            }
        } else {
            commandSender.sendMessage("§cYou don't have permission to use this command!");
        }
        return true;
    }
}
