package de.construkter.glitzoriumSMP.afksystem;

import de.construkter.glitzoriumSMP.tablist.PrefixManager;
import de.construkter.glitzoriumSMP.tablist.StatusCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AFKCommand implements CommandExecutor {

    public static List<Player> afkPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            if (!afkPlayers.contains(player)) {
                afkPlayers.add(player);
                player.sendMessage(ChatColor.GRAY + "Du bist nun AFK!");
                PrefixManager.setPLayerPrefix(player, "&7[&8AFK&7] ");
            } else {
                PrefixManager.setPLayerPrefix(player, StatusCommand.playerStatus.getOrDefault(player, ""));
                player.sendMessage(ChatColor.GRAY + "Du bist nun nicht mehr AFK! (Du musst deinen Status eventuell neu setzen)");
                afkPlayers.remove(player);
            }
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "Du musst dafür ein Spieler sein!");
        return true;
    }
}
