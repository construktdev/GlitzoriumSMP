package de.construkter.glitzoriumSMP.shop;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.helpop.managers.FileManager;
import org.bukkit.ChatColor;
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
        if (strings.length > 1 && player.isOp()) {
            player.sendMessage(ChatColor.RED + "Usage: /shop <on?>");
        } else if(strings[0].equalsIgnoreCase("on")) {
            FileManager fileManager = new FileManager("config", "");
            fileManager.getFileConfiguration().set("shop", true);
            GlitzoriumSMP.ShopEnabled = true;
        } else if(strings[0].equalsIgnoreCase("off")) {
            FileManager fileManager = new FileManager("config", "");
            fileManager.getFileConfiguration().set("shop", false);
            GlitzoriumSMP.ShopEnabled = false;
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
