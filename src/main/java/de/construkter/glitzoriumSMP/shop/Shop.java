package de.construkter.glitzoriumSMP.shop;

import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop implements Listener {
    public void openShop(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27, "Ingame Shop");

        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta totemMeta = totem.getItemMeta();
        if (totemMeta != null) {
            totemMeta.setDisplayName(ChatColor.GOLD + "Totems");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.AQUA + ChatColor.BOLD.toString() + "2 Diamanten");
            totemMeta.setLore(lore);
            totem.setItemMeta(totemMeta);
        }

        ItemStack emerald_block = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta emeraldMeta = emerald_block.getItemMeta();
        if (emeraldMeta != null) {
            emeraldMeta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Blöcke");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.AQUA + ChatColor.BOLD.toString() + "1 Diamant");
            emeraldMeta.setLore(lore);
            emerald_block.setItemMeta(emeraldMeta);
        }

        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        if (placeholderMeta != null) {
            placeholderMeta.setDisplayName("");
            placeholderMeta.setHideTooltip(true);
            placeholder.setItemMeta(placeholderMeta);
        }

        ItemStack placeholder_bar = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta placeholder_barMeta = placeholder_bar.getItemMeta();
        if (placeholder_barMeta != null) {
            placeholder_barMeta.setDisplayName("");
            placeholder_barMeta.setHideTooltip(true);
            placeholder_bar.setItemMeta(placeholder_barMeta);
        }

        inventory.setItem(9, totem);
        inventory.setItem(10, emerald_block);
        for (int i = 1; i < 18; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, placeholder);
            }
        }
        for (int i = 19; i < 27; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, placeholder_bar);
            }
        }

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Ingame Shop")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || !(event.getWhoClicked() instanceof Player player)) {
                return;
            }

            Material material = event.getCurrentItem().getType();
            if (material == Material.TOTEM_OF_UNDYING) {
                int diamondsNeeded = 2;
                int diamondsAvailable = 0;

                for (ItemStack item : player.getInventory().getContents()) {
                    if (item != null && item.getType() == Material.DIAMOND) {
                        diamondsAvailable += item.getAmount();
                    }
                }

                if (diamondsAvailable < diamondsNeeded) {
                    player.sendMessage(Prefix.SHOP + ChatColor.RED + "Du hast zu wenig Diamanten!");
                    return;
                }

                if (removeDiamonds(player, diamondsNeeded)) {
                    player.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING));
                    player.sendMessage(Prefix.SHOP + ChatColor.GREEN + "Du hast ein Totem gekauft!");
                }
            } else if (material == Material.EMERALD_BLOCK) {
                int diamondsNeeded = 1;
                int diamondsAvailable = 0;

                for (ItemStack item : player.getInventory().getContents()) {
                    if (item != null && item.getType() == Material.DIAMOND) {
                        diamondsAvailable += item.getAmount();
                    }
                }

                if (diamondsAvailable < diamondsNeeded) {
                    player.sendMessage(ChatColor.RED + "Du hast zu wenig Diamanten!");
                    return;
                }

                if (removeDiamonds(player, diamondsNeeded)) {
                    player.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK));
                    player.sendMessage(Prefix.SHOP + ChatColor.GREEN + "Du hast ein Smaragdblock gekauft!");
                }
            }
        }
    }

    public static boolean removeDiamonds(Player player, int amount) {
        int diamondsToRemove = amount;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.DIAMOND) {
                int stackAmount = item.getAmount();
                if (stackAmount > diamondsToRemove) {
                    item.setAmount(stackAmount - diamondsToRemove);
                    return true;
                } else {
                    player.getInventory().remove(item);
                    diamondsToRemove -= stackAmount;
                    if (diamondsToRemove == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
