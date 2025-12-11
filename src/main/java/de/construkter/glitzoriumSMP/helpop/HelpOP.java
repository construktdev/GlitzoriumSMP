package de.construkter.glitzoriumSMP.helpop;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.antibot.UUIDManager;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelpOP {

    public static List<Player> mutedPlayers = new ArrayList<>();
    public static UUIDManager banManager;

    static {
        try {
            banManager = new UUIDManager("tempbans.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void warn(Player target, Player moderator, String reason) {
        Bukkit.getScheduler().runTask(GlitzoriumSMP.getInstance(), () -> {
            GlitzoriumSMP.sendMessage("Warn","**" + target.getName() + "** wurde von **" + moderator.getName() + "** gewarnt!\n Grund: " + reason);
            target.sendMessage(Prefix.PluginPrefix() + ChatColor.RED + "Du wurdest gewarnt! Grund: " + ChatColor.DARK_RED + reason);
            target.sendMessage(Prefix.helpOP() + ChatColor.RED + "Falls du denkst, dass dies ein Fehler ist, besuche https://glitzorium.de/support/");
        });
    }

    public void kick(Player target, Player moderator, String reason) {
        Bukkit.getScheduler().runTask(GlitzoriumSMP.getInstance(), () -> {
            if (moderator == null) {
                String moderatorName = "AutoMod";
                GlitzoriumSMP.sendMessage("Kick","**" + target.getName() + "** wurde von **" + moderatorName + "** gekickt!\n Grund: " + reason);
                target.kickPlayer(ChatColor.RED + "Du wurdest gekickt! Grund: " + ChatColor.DARK_RED + reason);
                return;
            }
            GlitzoriumSMP.sendMessage("Kick","**" + target.getName() + "** wurde von **" + moderator.getName() + "** gekickt!\n Grund: " + reason);
            target.kickPlayer(ChatColor.RED + "Du wurdest gekickt! Grund: " + ChatColor.DARK_RED + reason);
        });
    }

    public void ban(Player target, Player moderator, String reason) {
        Bukkit.getScheduler().runTask(GlitzoriumSMP.getInstance(), () -> {
            if (moderator == null) {
                String moderatorName = "AutoMod";
                GlitzoriumSMP.sendMessage("Bann","**" + target.getName() + "** wurde von **" + moderatorName + "** gebannt!\n Grund: " + reason);
            } else {
                GlitzoriumSMP.sendMessage("Bann","**" + target.getName() + "** wurde von **" + moderator.getName() + "** gebannt!\n Grund: " + reason);
            }
            target.kickPlayer(ChatColor.RED + "Du wurdest gebannt! Grund: " + ChatColor.DARK_RED + reason);
            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, moderator.getName());
        });
    }

    public void tempBan(Player target, Player moderator, String reason, long duration) {
        long days = duration / (24 * 60 * 60);
        long hours = (duration % (24 * 60 * 60)) / (60 * 60);
        long minutes = (duration % (60 * 60)) / 60;

        Bukkit.getScheduler().runTask(GlitzoriumSMP.getInstance(), () -> {
           if (moderator == null) {
               String moderatorName = "AutoMod";
               GlitzoriumSMP.sendMessage("Bann","**" + target.getName() + "** wurde von **" + moderatorName + "** gebannt!\n Grund: " + reason + "\nDuration: " + duration);
           } else {
               GlitzoriumSMP.sendMessage("Bann","**" + target.getName() + "** wurde von **" + moderator.getName() + "** gebannt!\n Grund: " + reason);
           }
           target.kickPlayer(ChatColor.RED + "Du wurdest gebannt! Grund: " + ChatColor.DARK_RED + reason + "\nZeit: " + days + "D " + hours + "H " + minutes + "M");
           assert moderator != null;
           Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, moderator.getName());
        });
    }

    public void mute(Player target, Player moderator, String reason) {
        Bukkit.getScheduler().runTask(GlitzoriumSMP.getInstance(), () -> {
            mutedPlayers.add(target);
            GlitzoriumSMP.sendMessage("Mute","**" + target.getName() + "** wurde von **" + moderator.getName() + "** gemuted!\n Grund: " + reason + "\n\nNote: Spieler müssen manuell mit /unmute <player> entmuted werden!");
            target.sendMessage(Prefix.PluginPrefix() + ChatColor.RED + "Du wurdest gemuted! Grund: " + ChatColor.DARK_RED + reason);
            target.sendMessage(Prefix.helpOP() + ChatColor.RED + "Falls du denkst, dass dies ein Fehler ist, besuche https://glitzorium.de/support/");
        });
    }
}
