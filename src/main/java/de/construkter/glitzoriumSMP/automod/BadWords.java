package de.construkter.glitzoriumSMP.automod;

import org.bukkit.ChatColor;

public class BadWords {
    public static String[] getBadWords() {
        return new String[] {
                "arsch", "bitch", "ficken", "shit", "dick", "asshole", "cunt",
                "motherfucker", "bastard", "fuck", "cock", "slut", "whore",
                "douchebag", "piss", "twat", "prick", "wanker", "fag",
                "jerk", "cocksucker", "faggot", "tits", "nigga", "nigger", "hoe",
                "retard", "skank", "bimbo", "harlot", "nigare", "niggerman", "nigroman",
                "niger", "hure", "huhre", "huree", "hureee", "hureeee", "hureeeee",
                "hail", "heil", "hitler"
        };
    }

    public static String getWarnMessage(String word) {
        return ChatColor.RED + "[AutoMod] " + ChatColor.DARK_RED + "Du hast ein verbotenes Wort verwendet! Das Wort " + ChatColor.RED + word + ChatColor.DARK_RED + " ist verboten!";
    }
}
