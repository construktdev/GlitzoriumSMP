package de.construkter.glitzoriumSMP.tablist;

import de.construkter.glitzoriumSMP.antibot.UUIDManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

public class StatusCommand implements CommandExecutor, TabCompleter {

    private static final List<String> STATUS_OPTIONS = Arrays.asList(
            "ghg", "bambus", "redstone", "live", "afk", "pvp", "builder", "troll", "polizei", "skibidi", "sigma", "reset", "schweiz"
    );

    public static Map<Player, String> playerStatus = new HashMap<>();

    private static final List<Player> STATUS_FORCE = new ArrayList<>();

    private static UUIDManager manager;

    static {
        try {
            manager = new UUIDManager("status.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

        Prefix.setPLayerPrefix(player, getPrefix(player, strings[0]));
        playerStatus.put(player, getPrefix(player, strings[0]));

        if (strings[0].equals("list")) return true;
        if (!STATUS_OPTIONS.contains( strings[0].toLowerCase())) return true;
        player.sendMessage(ChatColor.DARK_AQUA + "Dein Status ist nun " + ChatColor.GOLD + strings[0]);
        if (strings[1].equals("save")) {
            try {
                manager.addUUID(player.getName(), getPrefix(player, strings[0]));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.sendMessage(ChatColor.GREEN + "Du hast den Status " + strings[0] + "gespeichert!");
        }
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

    public String getPrefix(Player player, String status) {
        return switch (status.toLowerCase()) {
            case "ghg" -> "&7[&5GHG&7] ";
            case "bambus" -> "&7[&2Bambus&7] ";
            case "redstone" -> "&7[&4Redstone&7] ";
            case "live" -> "&7[&eLive&7] ";
            case "afk" -> "&7[&8Afk&7] ";
            case "pvp" -> "&7[&aPvp&7] ";
            case "builder" -> "&7[&3Builder&7] ";
            case "troll" -> "&7[&6Troll&7] ";
            case "polizei" -> "&7[&9Polizei&7] ";
            case "skibidi" -> "&7[&6Skibidi&7] ";
            case "sigma" -> "&7[&aSigma&7] ";
            case "schweiz" -> "&7[&4Sc&fhwe&4iz&7] ";
            case "reset" -> "";
            default -> {
                player.sendMessage("Dieser Tag existiert nicht!");
                yield "";
            }
        };
    }
}
