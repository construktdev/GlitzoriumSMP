package de.construkter.glitzoriumSMP.whitelist;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WhitelistManager implements Listener {

    static String WHITELIST_FILE = "glitzorium-whitelist.txt";

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (!GlitzoriumSMP.whitelist) {
            return;
        }
        if (!(getWhitelist(event.getPlayer()))) {
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "Du bist nicht auf der Whitelist.");
        }
    }

    public static String addPlayer(String player) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(WHITELIST_FILE))) {
            writer.println(player);
        } catch (IOException e) {
            return "There was an error while writing to the whitelist file";
        }
        return "Added player " + player + " to whitelist";
    }

    public static String removePlayer(String player) {
        String[] remove = {player};
        try (BufferedReader reader = new BufferedReader(new FileReader(WHITELIST_FILE));
             PrintWriter writer = new PrintWriter(new FileWriter(WHITELIST_FILE + ".temp"))) {

            String line;
            boolean writeLine = true;

            while ((line = reader.readLine()) != null) {
                for (String s : remove) {
                    if (line.equalsIgnoreCase(s)) {
                        writeLine = false;
                        break;
                    }
                }

                if (writeLine) {
                    writer.println(line);
                } else {
                    writeLine = true;
                }
            }

            writer.close();
            reader.close();

            File originalFile = new File(WHITELIST_FILE);
            File tempFile = new File(WHITELIST_FILE + ".temp");

            if (originalFile.delete()) {
                System.out.println("Original file deleted.");
            } else {
                System.out.println("Failed to delete original file.");
            }

            if (tempFile.renameTo(originalFile)) {
                System.out.println("Temp file renamed to original file.");
            } else {
                System.out.println("Failed to rename temp file.");
            }

        } catch (IOException e) {
            return "There was an error while writing to the whitelist file";
        }
        return "Removed player " + player + " from the whitelist";
    }

    public static boolean getWhitelist(Player player) {
        try (BufferedReader reader = new BufferedReader(new FileReader(WHITELIST_FILE))) {
            List<String> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                players.add(line);
            }
            return players.contains(player.getName());
        } catch (IOException e) {
            return false;
        }
    }
}
