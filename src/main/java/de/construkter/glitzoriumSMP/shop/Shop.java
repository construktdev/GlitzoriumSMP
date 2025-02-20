package de.construkter.glitzoriumSMP.shop;

import de.construkter.glitzoriumSMP.shop.items.BeaconShopItem;
import de.construkter.glitzoriumSMP.shop.items.EmeraldBlockShopItem;
import de.construkter.glitzoriumSMP.shop.items.NetheriteItemShop;
import de.construkter.glitzoriumSMP.shop.items.TotemShopItem;
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

import java.util.Arrays;
import java.util.List;

public class Shop implements Listener {
    private final List<ShopItem> shopItems = Arrays.asList(
            new TotemShopItem(),
            new EmeraldBlockShopItem(),
            new BeaconShopItem(),
            new NetheriteItemShop()
    );

    public void openShop(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27, "Ingame Shop");

        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        if (placeholderMeta != null) {
            placeholderMeta.setDisplayName(" ");
            placeholder.setItemMeta(placeholderMeta);
        }

        ItemStack placeholder_bar = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta placeholder_barMeta = placeholder_bar.getItemMeta();
        if (placeholder_barMeta != null) {
            placeholder_barMeta.setDisplayName(" ");
            placeholder_bar.setItemMeta(placeholder_barMeta);
        }

        ItemStack nextPage = new ItemStack(Material.ARROW);
        ItemMeta nextPageMeta = nextPage.getItemMeta();
        if (nextPageMeta != null) {
            nextPageMeta.setDisplayName(ChatColor.GRAY + "Nächste Seite");
            nextPageMeta.setLore(List.of(ChatColor.RED + "Coming Soon..."));
            nextPage.setItemMeta(nextPageMeta);
        }

        inventory.setItem(22, nextPage);

        // Shop-Items in GUI setzen (ab Slot 9)
        int slot = 9;
        for (ShopItem item : shopItems) {
            if (slot >= 18) break;
            inventory.setItem(slot, item.getDisplayItem());
            slot++;
        }

        // Platzhalter setzen
        for (int i = 0; i < 27; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, (i < 18) ? placeholder : placeholder_bar);
            }
        }

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Ingame Shop")) return;
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player player) || event.getCurrentItem() == null) return;

        for (ShopItem item : shopItems) {
            if (item.getDisplayItem().getType() == event.getCurrentItem().getType()) {
                item.purchase(player);
                break;
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
