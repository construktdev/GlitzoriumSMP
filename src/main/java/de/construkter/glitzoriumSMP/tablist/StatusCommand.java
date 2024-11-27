package de.construkter.glitzoriumSMP.tablist;

import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StatusCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)){
            commandSender.sendMessage("You must be a player to use this command!");
            return true;
        }
        if (strings.length != 1) {
            commandSender.sendMessage(ChatColor.RED + "Usage: /status <status>");
            commandSender.sendMessage(Prefix.PluginPrefix() + ChatColor.DARK_AQUA + "Nutze /status list um dir alle möglichen Status Prefixes anzuzeigen");
            return true;
        }
        switch (strings[0].toLowerCase()) {
            case "ghg":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&5GHG&7] ");
                break;
            case "bambus":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&2Bambus&7] ");
                break;
            case "redstone":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&4Redstone&7] ");
                break;
            case "live":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&eLive&7] ");
                break;
            case "afk":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&8AFK&7] ");
                break;
            case "pvp":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&aPvP&7] ");
                break;
            case "builder":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&3Builder&7] ");
                break;
            case "troll":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&6Troll&7] ");
                break;
            case "polizei":
                de.construkter.glitzoriumSMP.tablist.Prefix.setPLayerPrefix(player, "&7[&9Polizei&7] ");
                break;
            case "default":
                commandSender.sendMessage(Prefix.errorPrefix() + "Dieser Tag existiert nicht! Nutze /status list um die verfügbaren dir anzuzeigen");
                break;
            case "list":
                player.sendMessage(Prefix.PluginPrefix() + ChatColor.DARK_AQUA + "Verfügbare Status Prefixe: " + ChatColor.GOLD + "ghg, bambus, redstone, live, afk, pvp, builder, troll, polizei");
                break;
        }
        if (strings[0].equals("list")) return true;
        player.sendMessage(ChatColor.DARK_AQUA + "Dein Status ist nun " + ChatColor.GOLD + strings[0]);
        return true;
    }
}
