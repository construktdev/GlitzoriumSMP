package de.construkter.glitzoriumSMP.tablist;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeathCounter implements Listener, CommandExecutor {

    private static final File deathsFile =
            new File("plugins/GlitzoriumSMP/deaths.txt");

    private static final Map<String, Integer> deaths = new HashMap<>();
    private static Scoreboard scoreboard;
    private static Objective objective;

    /* ===================== SETUP ===================== */

    public static void setupDeathBoard() {
        // Ordner erstellen
        deathsFile.getParentFile().mkdirs();

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        objective = scoreboard.registerNewObjective(
                "deaths", "dummy", "§cDeaths"
        );
        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        loadDeaths();
        updateScoreboard();
    }

    /* ===================== EVENTS ===================== */

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        deaths.putIfAbsent(player.getName(), 0);
        player.setScoreboard(scoreboard);
        updateScoreboard();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        deaths.put(player.getName(),
                deaths.getOrDefault(player.getName(), 0) + 1
        );
        saveDeaths();
        updateScoreboard();
    }

    /* ===================== FILE IO ===================== */

    private static void loadDeaths() {
        deaths.clear();

        if (!deathsFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(deathsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    deaths.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void saveDeaths() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(deathsFile))) {
            for (Map.Entry<String, Integer> entry : deaths.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ===================== SCOREBOARD ===================== */

    private static void updateScoreboard() {
        if (objective == null) return;

        scoreboard.getEntries().forEach(scoreboard::resetScores);

        for (Map.Entry<String, Integer> entry : deaths.entrySet()) {
            Score score = objective.getScore(entry.getKey());
            score.setScore(entry.getValue());
        }
    }

    /* ===================== COMMANDS ===================== */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("glitzorium.deaths.admin")) {
            sender.sendMessage("§cKeine Berechtigung.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("setdeaths")) {
            if (args.length != 2) return false;

            try {
                int value = Integer.parseInt(args[1]);
                deaths.put(args[0], value);
                saveDeaths();
                updateScoreboard();
                sender.sendMessage("§aDeaths von §e" + args[0] + " §agesetzt.");
            } catch (NumberFormatException e) {
                sender.sendMessage("§cBitte eine Zahl angeben!");
            }
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("adddeath")) {
            if (args.length != 1) return false;

            deaths.put(args[0],
                    deaths.getOrDefault(args[0], 0) + 1
            );
            saveDeaths();
            updateScoreboard();
            sender.sendMessage("§aDeath hinzugefügt.");
            return true;
        }

        return false;
    }
}
