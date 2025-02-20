package de.construkter.glitzoriumSMP.shop.items;

import de.construkter.glitzoriumSMP.shop.Shop;
import de.construkter.glitzoriumSMP.shop.ShopItem;
import de.construkter.glitzoriumSMP.utils.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class RocketItemShop implements ShopItem {
    @Override
    public ItemStack getDisplayItem() {
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.DARK_GREEN + "64 Raketen");
            meta.setLore(Collections.singletonList(ChatColor.AQUA + "Preis: 2 Diamanten"));
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public int getPrice() {
        return 2;
    }

    @Override
    public ItemStack getReward() {
        return new ItemStack(Material.FIREWORK_ROCKET, 64);
    }

    @Override
    public boolean purchase(Player player) {
        if (Shop.removeDiamonds(player, getPrice())) {
            player.getInventory().addItem(getReward());
            player.sendMessage(Prefix.SHOP + ChatColor.GREEN + "Du hast Raketen gekauft!");
            return true;
        }
        player.sendMessage(Prefix.SHOP + ChatColor.RED + "Du hast zu wenig Diamanten!");
        return false;
    }
}
