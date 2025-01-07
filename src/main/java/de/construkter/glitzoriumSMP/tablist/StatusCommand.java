package de.construkter.glitzoriumSMP.tablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class StatusCommand implements CommandExecutor, TabCompleter {

    private static final List<String> STATUS_OPTIONS = Arrays.asList(
            "ghg", "bambus", "redstone", "live", "afk", "pvp", "builder", "troll", "polizei", "skibidi", "sigma", "reset"
    );

    public static Map<Player, String> playerStatus = new HashMap<>();

    private static final List<Player> STATUS_FORCE = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("You must be a player to use this command!");
            return true;
        }
        if (commandSender.hasPermission("smp.status") && (!STATUS_OPTIONS.contains(strings[0]) || strings[0].equals("reset"))) {
            if (strings.length != 2) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /status <status> <player>");
                return true;
            }
            Player target = Bukkit.getPlayer(strings[1]);
            if (target == null) {
                commandSender.sendMessage(ChatColor.RED + "Player not found!");
                return true;
            }
            if (strings[0].equalsIgnoreCase("clown")) {
                Prefix.setPLayerPrefix(target, "&7[&cC&fl&co&fw&cn&7] ");
                commandSender.sendMessage(ChatColor.GREEN + "Erfolgreich! Nutze /status reset " + target.getName() + " um den Status ihm zu entfernen!");
                STATUS_FORCE.add(target);
                return true;
            } else if (strings[0].equalsIgnoreCase("reset")) {
                Prefix.setPLayerPrefix(target, "");
                STATUS_FORCE.remove(target);
                return true;
            }
        } else {
            if (strings.length != 1) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /status <status>");
                return true;
            }
        }
        if (STATUS_FORCE.contains(player)) {
            commandSender.sendMessage(ChatColor.RED + "Du hast einen geforcten Status, und kannst diesen erst ändern wenn ein Admin deinen Status resettet");
            return true;
        }
        switch (strings[0].toLowerCase()) {
            case "ghg":
                Prefix.setPLayerPrefix(player, "&7[&5GHG&7] ");
                playerStatus.put(player, "&7[&5GHG&7] ");
                break;
            case "bambus":
                Prefix.setPLayerPrefix(player, "&7[&2Bambus&7] ");
                playerStatus.put(player, "&7[&2Bambus&7] ");
                break;
            case "redstone":
                Prefix.setPLayerPrefix(player, "&7[&4Redstone&7] ");
                playerStatus.put(player, "&7[&4Redstone&7] ");
                break;
            case "live":
                Prefix.setPLayerPrefix(player, "&7[&eLive&7] ");
                playerStatus.put(player, "&7[&eLive&7] ");
                break;
            case "afk":
                Prefix.setPLayerPrefix(player, "&7[&8AFK&7] ");
                commandSender.sendMessage(ChatColor.RED + "Bitte beachte das dieser Tag bald nur noch mit /afk genutzt werden kann!");
                playerStatus.put(player, "&7[&8AFK&7] ");
                break;
            case "pvp":
                Prefix.setPLayerPrefix(player, "&7[&aPvP&7] ");
                playerStatus.put(player, "&7[&aPvP&7] ");
                break;
            case "builder":
                Prefix.setPLayerPrefix(player, "&7[&3Builder&7] ");
                playerStatus.put(player, "&7[&3Builder&7] ");
                break;
            case "troll":
                Prefix.setPLayerPrefix(player, "&7[&6Troll&7] ");
                playerStatus.put(player, "&7[&6Troll&7] ");
                break;
            case "polizei":
                Prefix.setPLayerPrefix(player, "&7[&9Polizei&7] ");
                playerStatus.put(player, "&7[&9Polizei&7] ");
                break;
            case "skibidi":
                Prefix.setPLayerPrefix(player, "&7[&6Skibidi&7] ");
                playerStatus.put(player, "&7[&6Skibidi&7] ");
                break;
            case "sigma":
                Prefix.setPLayerPrefix(player, "&7[&aSigma&7] ");
                playerStatus.put(player, "&7[&aSigma&7] ");
                break;
            case "reset":
                Prefix.setPLayerPrefix(player, "");
                playerStatus.put(player, "");
                break;
            default:
                commandSender.sendMessage("Dieser Tag existiert nicht!");
                break;
        }
        if (strings[0].equals("list")) return true;
        if (!STATUS_OPTIONS.contains( strings[0].toLowerCase())) return true;
        player.sendMessage(ChatColor.DARK_AQUA + "Dein Status ist nun " + ChatColor.GOLD + strings[0]);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            String partial = args[0].toLowerCase();
            List<String> suggestions = new ArrayList<>();
            for (String option : STATUS_OPTIONS) {
                if (option.startsWith(partial)) {
                    suggestions.add(option);
                }
            }
            return suggestions;
        }
        return null;
    }
}
