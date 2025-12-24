package de.construkter.glitzoriumSMP.helpop.discord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OnlineCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("list")) {

            List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Spieler Liste (Online)")
                    .setDescription(onlinePlayers.stream().map(Player::getDisplayName).reduce((a, b) -> a + "\n" + b).orElse("Keine Spieler")
                    + "\n\n**Gesamt Spieler:** " + onlinePlayers.size())
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now());

            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
        }
    }
}
