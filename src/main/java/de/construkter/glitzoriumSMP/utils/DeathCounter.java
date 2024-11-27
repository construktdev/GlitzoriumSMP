package de.construkter.glitzoriumSMP.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class DeathCounter implements Listener {
    public static void setupDeathBoard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Objective objective = scoreboard.getObjective("deaths");
        if (objective == null) {
            objective = scoreboard.registerNewObjective("deaths", "deathCount");
        }

        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        player.setScoreboard(scoreboard);
    }
}
