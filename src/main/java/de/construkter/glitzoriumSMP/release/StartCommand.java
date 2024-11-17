package de.construkter.glitzoriumSMP.release;

import de.construkter.glitzoriumSMP.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand extends Utils implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("glitzorium.smp.start")) {
            commandSender.sendMessage(ChatColor.GREEN + "Glitzorium SMP got started successfully!");
            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "LetsHugo hat den " + ChatColor.GOLD + "" + ChatColor.BOLD + "GlitzoriumSMP " + ChatColor.DARK_AQUA + "betreten!");
            sleep(1000);
            Bukkit.broadcastMessage(ChatColor.BLUE + "[VIP | LetsHugo] " + ChatColor.GOLD + " Was *beatbox* geht ab meine Freunde und herzlich Willkommen zu GlitzoriumSMP 2.0!");
            sleep(3000);
            commandSender.sendMessage("Du kannst den Start mit '/start-confirm' fortführen");
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return false;
    }
}
