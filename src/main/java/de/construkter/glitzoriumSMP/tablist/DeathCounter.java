package de.construkter.glitzoriumSMP.tablist;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DeathCounter implements Listener, CommandExecutor {
    private static final File deathsFile = new File("plugins/GlitzoriumSMP/deaths.txt");
    private static final Map<String, Integer> deaths = new HashMap<>();

    public static void setupDeathBoard() {
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        Objective objective = scoreboard.getObjective("deaths");
        if (objective == null) {
            objective = scoreboard.registerNewObjective("deaths", "dummy");
        }
        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        loadDeaths();
        updateScoreboard();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        player.setScoreboard(scoreboard);
    }

    private static void loadDeaths() {
        deaths.clear();
        if (!deathsFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(deathsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    deaths.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDeaths() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(deathsFile))) {
            for (Map.Entry<String, Integer> entry : deaths.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateScoreboard() {
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        Objective objective = scoreboard.getObjective("deaths");
        if (objective == null) return;

        for (Map.Entry<String, Integer> entry : deaths.entrySet()) {
            Score score = objective.getScore(entry.getKey());
            score.setScore(entry.getValue());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return false;
        String player = args[0];

        switch (command.getName().toLowerCase()) {
            case "setdeaths":
                deaths.put(player, Integer.parseInt(args[1]));
                break;
            case "adddeath":
                deaths.put(player, deaths.getOrDefault(player, 0) + 1);
                break;
            case "refreshdeaths":
                loadDeaths();
                break;
            default:
                return false;
        }
        saveDeaths();
        updateScoreboard();
        sender.sendMessage("§aDeaths updated for " + player);
        return true;
    }
}
