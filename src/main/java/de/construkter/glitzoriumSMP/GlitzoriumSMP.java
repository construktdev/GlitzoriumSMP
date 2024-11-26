package de.construkter.glitzoriumSMP;

import de.construkter.glitzoriumSMP.bedrock.ChatCommand;
import de.construkter.glitzoriumSMP.commands.*;
import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.helpop.commands.WarnCommand;
import de.construkter.glitzoriumSMP.helpop.discord.listeners.ReadyListener;
import de.construkter.glitzoriumSMP.helpop.managers.FileManager;
import de.construkter.glitzoriumSMP.listeners.JoinListener;
import de.construkter.glitzoriumSMP.release.ConfirmStart;
import de.construkter.glitzoriumSMP.release.EventManager;
import de.construkter.glitzoriumSMP.release.PrepareStartCommand;
import de.construkter.glitzoriumSMP.release.StartCommand;
import de.construkter.glitzoriumSMP.whitelist.AddWhitelist;
import de.construkter.glitzoriumSMP.whitelist.RemoveWhitelist;
import de.construkter.glitzoriumSMP.whitelist.WhitelistManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public final class GlitzoriumSMP extends JavaPlugin {

    private static GlitzoriumSMP instance;
    private static ShardManager jda;
    public static String avatar;
    private static final HelpOP helpop = new HelpOP();
    private static List<Player> admins;

    @Override
    public void onEnable() {
        instance = this;
        for (Player player : getServer().getOnlinePlayers()) {
            if (player.hasPermission("smp.admin")) {
                admins.add(player);
            }
        }
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new WhitelistManager(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new EventManager(), this);
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCommand());
        Objects.requireNonNull(getCommand("hub")).setExecutor(new LobbyCommand());
        Objects.requireNonNull(getCommand("playeradd")).setExecutor(new AddWhitelist());
        Objects.requireNonNull(getCommand("playerremove")).setExecutor(new RemoveWhitelist());
        Objects.requireNonNull(getCommand("prepare")).setExecutor(new PrepareStartCommand());
        Objects.requireNonNull(getCommand("start")).setExecutor(new StartCommand());
        Objects.requireNonNull(getCommand("start-confirm")).setExecutor(new ConfirmStart());
        Objects.requireNonNull(getCommand("day")).setExecutor(new DayCommand());
        Objects.requireNonNull(getCommand("night")).setExecutor(new NightCommand());
        Objects.requireNonNull(getCommand("sun")).setExecutor(new SunCommand());
        Objects.requireNonNull(getCommand("gm")).setExecutor(new GamemodeCommand());
        Objects.requireNonNull(getCommand("chat")).setExecutor(new ChatCommand());
        Objects.requireNonNull(getCommand("warn")).setExecutor(new WarnCommand());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        PrepareStartCommand.isStarted = true; // Default is true, will be turned false when the prepare command is executed
        FileManager fileManager = new FileManager("config", "");
        if (fileManager.getFileConfiguration().getString("token") == null) {
            getLogger().info("");
            getLogger().info("---------------------------------------------");
            getLogger().info("[!!!] No token was set in the config.yml file.");
            getLogger().info("---------------------------------------------");
            getLogger().info("");
            fileManager.getFileConfiguration().set("token", "token-here");
        } else {
            String token = fileManager.getFileConfiguration().getString("token");
            jda = DefaultShardManagerBuilder.createDefault(token)
                    .setActivity(Activity.playing("GlitzoriumSMP"))
                    .addEventListeners(new ReadyListener())
                    .build();
            if (fileManager.getFileConfiguration().getString("logChannel") == null) {
                getLogger().info("");
                getLogger().info("---------------------------------------------");
                getLogger().info("[!!!] No Logging Channel was set in the config.yml file.");
                getLogger().info("---------------------------------------------");
                getLogger().info("");
            }
        }
    }

    @Override
    public void onDisable() {

    }

    public static GlitzoriumSMP getInstance() {
        return instance;
    }

    public static ShardManager getJda() {
        return jda;
    }

    public static HelpOP getHelpop() {
        return helpop;
    }

    public static List<Player> getAdmins() {
        return admins;
    }

    public static void sendMessage(String type, String message) {
        TextChannel log = jda.getTextChannelById(1310679790226903089L);
        assert log != null;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("⚔️ • HelpOP " + type);
        embedBuilder.setDescription(message);
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setFooter("HelpOP by Glitzorium", avatar);
        embedBuilder.setTimestamp(Instant.now());
        log.sendMessageEmbeds(embedBuilder.build()).queue();
    }
}