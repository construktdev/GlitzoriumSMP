package de.construkter.glitzoriumSMP.release;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class PrepareStartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
//        if (GlitzoriumSMP.isStarted) {
//            commandSender.sendMessage(ChatColor.RED + "The release event has already started.");
//            return true;
//        }
//        if (!(commandSender instanceof Player)) {
//            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
//            return true;
//        }
//
//        if (!commandSender.hasPermission("glitzoriumSMP.release")) {
//            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
//            return true;
//        }
//
//        for (Player player : Bukkit.getOnlinePlayers()) {
//            player.setGameMode(GameMode.SURVIVAL);
//            player.setAllowFlight(false);
//            player.setHealth(20);
//            player.setFoodLevel(20);
//            player.getInventory().clear();
//            player.getInventory().setArmorContents(null);
//            Location spawnLocation = Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation();
//            player.teleport(spawnLocation);
//        }
//
//        new BukkitRunnable() {
//            int countdown = 10;
//
//            @Override
//            public void run() {
//                if (countdown > 0) {
//                    for (Player player : Bukkit.getOnlinePlayers()) {
//                        player.sendTitle(ChatColor.GOLD + String.valueOf(countdown), "", 10, 20, 10);
//                    }
//                    countdown--;
//                } else {
//                    Bukkit.broadcastMessage(ChatColor.GOLD + "Los geht's!");
//                    GlitzoriumSMP.isStarted = true;
//                    Bukkit.getWorld("world").getWorldBorder().setSize(20000000, 10);
//                    cancel();
//                }
//            }
//        }.runTaskTimer(GlitzoriumSMP.getInstance(), 0, 20);
//
//        FileManager fileManager = new FileManager("config", "");
//        fileManager.getFileConfiguration().set("started", "true");
//        fileManager.saveFile();
//        return true;
    }
}
