package de.construkter.glitzoriumSMP.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Objects;

public class LobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Dieser Befehl kann nur von einem Spieler ausgeführt werden.");
            return false;
        }

        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF("lobby");

            player.sendPluginMessage(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("varoPlugin")), "BungeeCord", b.toByteArray());
        } catch (Exception e) {
            player.sendMessage("Es gab einen Fehler beim Ausführen des Befehls.");
            Bukkit.getLogger().severe("Fehler: " + e.getMessage());
        }
        return true;
    }
}
