package de.construkter.glitzoriumSMP.bedrock;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String messageRaw = Arrays.toString(strings);
        String message = messageRaw.replace("[", "").replace("]", "").replace(",", "");
        Bukkit.broadcastMessage("<" + commandSender.getName() + ">" + message);
        return true;
    }
}
