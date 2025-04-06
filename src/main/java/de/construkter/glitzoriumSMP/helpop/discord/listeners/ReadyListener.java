package de.construkter.glitzoriumSMP.helpop.discord.listeners;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import java.awt.*;
import java.time.Instant;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("✅ • HelpOP started");
        eb.setDescription("**" + event.getJDA().getSelfUser().getName() + "** is ready!\nServer: " + Bukkit.getServer().getIp() + ":" + Bukkit.getServer().getPort());
        eb.setColor(Color.GREEN);
        eb.setFooter("HelpOP by Glitzorium", event.getJDA().getSelfUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());
        TextChannel log = event.getJDA().getTextChannelById(1310679790226903089L);
        assert log != null;
        log.sendMessageEmbeds(eb.build()).queue();
        GlitzoriumSMP.avatar = event.getJDA().getSelfUser().getAvatarUrl();
        Bukkit.getLogger().info("HelpOP started as " + event.getJDA().getSelfUser().getName());
    }
}
