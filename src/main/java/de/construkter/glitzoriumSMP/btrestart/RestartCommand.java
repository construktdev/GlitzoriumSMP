package de.construkter.glitzoriumSMP.btrestart;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import de.construkter.glitzoriumSMP.utils.PterodactylAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class RestartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("glitzorium.admin")) {
            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (GlitzoriumSMP.pteroKey.equals("null")) {
            commandSender.sendMessage(ChatColor.RED + "Pterodactyl API key is not set! Cannot restart the server.");
            return true;
        }

        if (strings.length < 2) {
            commandSender.sendMessage(ChatColor.RED + "Usage: /restart <delay> <reason>\n" + ChatColor.GRAY + "Delay wird in Sekunden angegeben.");
            return true;
        }

        try {
            int delay = Integer.parseInt(strings[0]);
            String reason = String.join(" ", java.util.Arrays.copyOfRange(strings, 1, strings.length));

            String cmd = """
                    tellraw @a ["",{"text":"[SERVER] ","color":"dark_red"},{"text":"s ","obfuscated":true,"color":"gold"},{"text":"Restart in :delay Sekunden ","color":"gold"},{"text":"s","obfuscated":true,"color":"gold"},{"text":"\\n"},{"text":"[SERVER] ","color":"dark_red"},{"text":"s ","obfuscated":true,"color":"aqua"},{"text":"Grund: :reason ","color":"aqua"},{"text":"s","obfuscated":true,"color":"aqua"}]
                    """.replace(":delay", String.valueOf(delay)).replace(":reason", reason);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);

            new BukkitRunnable() {
                @Override
                public void run() {
                    PterodactylAPI api = new PterodactylAPI(GlitzoriumSMP.pteroKey, "https://panel.glitzorium.construkter.dev");
                    api.sendSignal("dd25f011", "restart");
                }
            }.runTaskLater(GlitzoriumSMP.getInstance(), delay * 20L);
        } catch (NumberFormatException e) {
            commandSender.sendMessage(ChatColor.RED + "Invalid delay value. Please provide a valid number.");
        }
        return true;
    }
}
