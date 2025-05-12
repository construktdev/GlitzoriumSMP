package de.construkter.glitzoriumSMP;

import de.construkter.glitzoriumSMP.afksystem.AFKCommand;
import de.construkter.glitzoriumSMP.afksystem.AutoAFK;
import de.construkter.glitzoriumSMP.antibot.AntiRaid;
import de.construkter.glitzoriumSMP.automod.AntiCommandSpam;
import de.construkter.glitzoriumSMP.bedrock.ChatCommand;
import de.construkter.glitzoriumSMP.commands.*;
import de.construkter.glitzoriumSMP.helpop.HelpOP;
import de.construkter.glitzoriumSMP.helpop.commands.*;
import de.construkter.glitzoriumSMP.helpop.discord.listeners.ReadyListener;
import de.construkter.glitzoriumSMP.helpop.listeners.ChatListener;
import de.construkter.glitzoriumSMP.helpop.listeners.EventLogger;
import de.construkter.glitzoriumSMP.helpop.managers.FileManager;
import de.construkter.glitzoriumSMP.listeners.JoinListener;
import de.construkter.glitzoriumSMP.release.EventManager;
import de.construkter.glitzoriumSMP.release.PrepareStartCommand;
import de.construkter.glitzoriumSMP.shop.Shop;
import de.construkter.glitzoriumSMP.shop.ShopCommand;
import de.construkter.glitzoriumSMP.tablist.StatusCommand;
import de.construkter.glitzoriumSMP.tablist.DeathCounter;
import de.construkter.glitzoriumSMP.whitelist.AddWhitelist;
import de.construkter.glitzoriumSMP.whitelist.RemoveWhitelist;
import de.construkter.glitzoriumSMP.whitelist.WhitelistManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class GlitzoriumSMP extends JavaPlugin {

    private static GlitzoriumSMP instance;
    private static ShardManager jda;
    public static String avatar;
    private static final HelpOP helpop = new HelpOP();
    public static final List<Player> admins = new ArrayList<>();
    public static boolean whitelist = true;
    private static TextChannel log;

    @Override
    public void onEnable() {
        String logo = """
            \n
            ╔═╗┬  ┬┌┬┐┌─┐┌─┐┬─┐┬┬ ┬┌┬┐╔═╗╔╦╗╔═╗
            ║ ╦│  │ │ ┌─┘│ │├┬┘││ ││││╚═╗║║║╠═╝
            ╚═╝┴─┘┴ ┴ └─┘└─┘┴└─┴└─┘┴ ┴╚═╝╩ ╩╩ \s""";
        getLogger().info(logo);
        getLogger().info("\nGlitzorium SMP is starting...");
        getLogger().info("\n");
        instance = this;
        getServer().getPluginManager().registerEvents(new WhitelistManager(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new EventManager(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new DeathCounter(), this);
        getServer().getPluginManager().registerEvents(new de.construkter.glitzoriumSMP.automod.ChatListener(), this);
        getServer().getPluginManager().registerEvents(new AntiRaid(), this);
        getServer().getPluginManager().registerEvents(new EventLogger(), this);
        getServer().getPluginManager().registerEvents(new AutoAFK(), this);
        getServer().getPluginManager().registerEvents(new Shop(), this);
        getServer().getPluginManager().registerEvents(new AntiCommandSpam(), this);
        registerCommand("lobby", new LobbyCommand());
        registerCommand("hub", new LobbyCommand());
        registerCommand("playeradd", new AddWhitelist());
        registerCommand("playerremove", new RemoveWhitelist());
        registerCommand("day", new DayCommand());
        registerCommand("night", new NightCommand());
        registerCommand("sun", new SunCommand());
        registerCommand("gm", new GamemodeCommand());
        registerCommand("chat", new ChatCommand());
        registerCommand("warn", new WarnCommand());
        registerCommand("kick", new KickCommand());
        registerCommand("ban", new BanCommand());
        registerCommand("mute", new MuteCommand());
        registerCommand("unmute", new UnmuteCommand());
        registerCommand("status", new StatusCommand());
        registerCommand("troll", new TrollCommand());
        registerCommand("plist", new WhosOnline());
        registerCommand("ping", new PingCommand());
        registerCommand("playtime", new PlayTimeCommand());
        registerCommand("afk", new AFKCommand());
        registerCommand("ec", new EcCommand());
        registerCommand("invsee", new InventoryCommand());
        registerCommand("link", new LinkCommand());
        registerCommand("shop", new ShopCommand());
        registerCommand("setdeaths", new DeathCounter());
        registerCommand("adddeaths", new DeathCounter());
        registerCommand("refreshdeaths", new DeathCounter());

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        PrepareStartCommand.isStarted = true;
        FileManager fileManager = new FileManager("config", "");
        if (fileManager.getFileConfiguration().getString("token") == null) {
            getLogger().info(" ");
            getLogger().info("---------------------------------------------");
            getLogger().info("[!!!] No token was set in the config.yml file.");
            getLogger().info("---------------------------------------------");
            getLogger().info(" ");
            fileManager.getFileConfiguration().set("token", "token-here");
        } else {
            String token = fileManager.getFileConfiguration().getString("token");
            jda = DefaultShardManagerBuilder.createDefault(token)
                    .setActivity(Activity.playing("glitzorium.de | 1.7.10 - 1.21.5"))
                    .addEventListeners(new ReadyListener())
                    .build();
        }
        DeathCounter.setupDeathBoard();
        AntiCommandSpam.commandExecutions = new HashMap<>();
        AntiCommandSpam.check(getInstance());
        log = jda.getTextChannelById(1310679790226903089L);
    }

    private void registerCommand(String name, CommandExecutor executor) {
        PluginCommand command = getCommand(name);
        if (command != null) command.setExecutor(executor);
        else getLogger().warning("Command '" + name + "' is not defined in plugin.yml!");
    }

    @Override
    public void onDisable() {
        sendMessage("Power Off", "Der Server wurde gestoppt!");
        saveConfig();
    }

    public static GlitzoriumSMP getInstance() {
        return instance;
    }

    public static HelpOP getHelpop() {
        return helpop;
    }

    public static void sendMessage(String type, String message) {

        assert log != null;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        if (type.equals("Power Off")) {
            embedBuilder.setTitle("❌ • HelpOP " + type);
        } else {
            embedBuilder.setTitle("⚔️ • HelpOP " + type);
        }
        embedBuilder.setDescription(message);
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setFooter("HelpOP by Glitzorium", avatar);
        embedBuilder.setTimestamp(Instant.now());
        log.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static void logChatMessage(String message, Player player) {
        assert log != null;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("📝 • HelpOP Chat Logger");
        embedBuilder.setDescription("**" + player.getName() + "** sendete die Nachricht: `" + message + "`");
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setFooter("HelpOP by Glitzorium", avatar);
        embedBuilder.setTimestamp(Instant.now());
        log.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static void logPLayer(boolean joined, Player player) {
        assert log != null;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("📝 • HelpOP Join/Leave Logger");
        if (joined) {
            embedBuilder.setDescription("**" + player.getName() + "** hat den Server betreten!\nHost: *Debug-mode disabled*");
        } else {
            embedBuilder.setDescription("**" + player.getName() + "** hat den Server verlassen!");
        }
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setFooter("HelpOP by Glitzorium", avatar);
        embedBuilder.setTimestamp(Instant.now());
        log.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static void logShopPurchase(String item, Player player) {
        assert log != null;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("💵 • HelpOP Shop Logger");
        embedBuilder.setTitle("**" + player.getName() + "** hat `" + item + "` gekauft.");
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setFooter("HelpOP by Glitzorium", avatar);
        embedBuilder.setTimestamp(Instant.now());
        log.sendMessageEmbeds(embedBuilder.build()).queue();
    }
}