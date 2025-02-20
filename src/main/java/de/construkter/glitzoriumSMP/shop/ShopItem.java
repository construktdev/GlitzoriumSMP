package de.construkter.glitzoriumSMP.shop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ShopItem {
    ItemStack getDisplayItem();
    int getPrice();
    ItemStack getReward();
    boolean purchase(Player player);
}
