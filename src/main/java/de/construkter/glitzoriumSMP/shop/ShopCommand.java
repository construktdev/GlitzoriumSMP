package de.construkter.glitzoriumSMP.shop;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.managers.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) {
            return true;
        }

        FileManager fileManager = new FileManager("config", "");

        if (strings.length > 1 && player.isOp()) {
            player.sendMessage(ChatColor.RED + "Usage: /shop <on?>");
            return true;
        } else if(strings.length > 0 && player.isOp() && strings[0].equalsIgnoreCase("on")) {
            fileManager.getFileConfiguration().set("shop", "true");
            fileManager.saveFile();
            GlitzoriumSMP.ShopEnabled = true;
            return true;
        } else if(strings.length > 0 && player.isOp() && strings[0].equalsIgnoreCase("off")) {
            fileManager.getFileConfiguration().set("shop", "false");
            fileManager.saveFile();
            GlitzoriumSMP.ShopEnabled = false;
            return true;
        } else if (strings.length > 0 && player.isOp() && strings[0].equalsIgnoreCase("status")) {
            Color color = Boolean.parseBoolean(fileManager.getFileConfiguration().getString("shop")) ? Color.GREEN : Color.RED;
            commandSender.sendMessage(ChatColor.GRAY + "Shop Enabled: " + color + fileManager.getFileConfiguration().getString("shop"));
            return true;
        }

        if (!GlitzoriumSMP.ShopEnabled) {
            player.sendMessage(ChatColor.RED + "Der Shop ist noch nicht verfügbar!");
            return true;
        }

        Shop shop = new Shop();
        shop.openShop(player);
        return true;
    }
}
