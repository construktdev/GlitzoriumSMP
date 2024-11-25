package de.construkter.glitzoriumSMP;

import de.construkter.glitzoriumSMP.bedrock.ChatCommand;
import de.construkter.glitzoriumSMP.commands.*;
import de.construkter.glitzoriumSMP.listeners.JoinListener;
import de.construkter.glitzoriumSMP.release.ConfirmStart;
import de.construkter.glitzoriumSMP.release.EventManager;
import de.construkter.glitzoriumSMP.release.PrepareStartCommand;
import de.construkter.glitzoriumSMP.release.StartCommand;
import de.construkter.glitzoriumSMP.whitelist.AddWhitelist;
import de.construkter.glitzoriumSMP.whitelist.RemoveWhitelist;
import de.construkter.glitzoriumSMP.whitelist.WhitelistManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class GlitzoriumSMP extends JavaPlugin {

    @Override
    public void onEnable() {
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
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        PrepareStartCommand.isStarted = true; // Default is true, will be turned false when the prepare command is executed
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}