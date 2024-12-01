package de.construkter.glitzoriumSMP.tablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class Prefix {
    public static void setPLayerPrefix(Player player, String prefix) {
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        Team team = scoreboard.getTeam(player.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
        }
        team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix));
        team.addEntry(player.getName());

        player.setScoreboard(scoreboard);
    }
}