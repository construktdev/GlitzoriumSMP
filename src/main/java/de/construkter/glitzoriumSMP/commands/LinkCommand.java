package de.construkter.glitzoriumSMP.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkCommand implements CommandExecutor {

    private static final Pattern URL_PATTERN = Pattern.compile(
            "(https?:\\\\/\\\\/[^\\\\s]+)"
    );

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String link = strings[0];
        Matcher matcher = URL_PATTERN.matcher(link);

        if (matcher.matches()) {
            String url = matcher.group(1);
            Component clickableMessage = Component.text(ChatColor.GRAY + commandSender.getName() + ": ")
                    .append(Component.text(url, NamedTextColor.GOLD)
                            .clickEvent(ClickEvent.openUrl(url))
                            .hoverEvent(HoverEvent.showText(Component.text("Klicke, um den Link zu öffnen"))));

            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(String.valueOf(clickableMessage)));
        }
        return true;
    }
}
