package de.construkter.glitzoriumSMP.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shop {
    public void openShop(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27, "Shop");

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = diamond.getItemMeta();
        if (diamondMeta != null) {
            diamondMeta.setDisplayName(ChatColor.GOLD + "Diamanten");
            diamondMeta.setHideTooltip(true);
            diamond.setItemMeta(diamondMeta);
        }

        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        if (placeholderMeta != null) {
            placeholderMeta.setDisplayName("");
            placeholderMeta.setHideTooltip(true);
            placeholder.setItemMeta(placeholderMeta);
        }

        inventory.setItem(0, diamond);
        for (int i = 1; i < 27; i++) {
            inventory.setItem(i, placeholder);
        }
    }
}
