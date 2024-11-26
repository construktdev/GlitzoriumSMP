package de.construkter.glitzoriumSMP.helpop;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpOP {

List<Player> admins = GlitzoriumSMP.getAdmins();

    public void warn(Player target, Player moderator, String reason) {
        GlitzoriumSMP.sendMessage("Warn","**" + target.getName() + "** wurde von **" + moderator.getName() + "** gewarnt!\n Grund: " + reason);
        target.sendMessage(Prefix.PluginPrefix() + ChatColor.RED + "Du wurdest gewarnt! Grund: " + ChatColor.DARK_RED + reason);
        target.sendMessage(Prefix.helpOP() + ChatColor.RED + "Falls du denkst, dass dies ein Fehler ist, besuche https://glitzorium.de/support/general/support.php");
        for (Player admin : admins) {
            admin.sendMessage(Prefix.helpOP() + ChatColor.GOLD + moderator.getName() + ChatColor.DARK_AQUA + "");
        }
    }

    public void kick(Player target, Player moderator, String reason) {
        GlitzoriumSMP.sendMessage("Kick","**" + target.getName() + "** wurde von **" + moderator.getName() + "** gekickt!\n Grund: " + reason);
        target.kickPlayer(ChatColor.RED + "Du wurdest gekickt! Grund: " + ChatColor.DARK_RED + reason);
    }

    public void ban(Player player, String reason) {

    }

    public void mute(Player player, String reason) {

    }
}
