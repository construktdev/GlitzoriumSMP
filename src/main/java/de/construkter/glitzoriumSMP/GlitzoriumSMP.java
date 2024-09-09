package de.construkter.glitzoriumSMP;

import de.construkter.glitzoriumSMP.commands.LobbyCommand;
import de.construkter.glitzoriumSMP.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class GlitzoriumSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCommand());
        Objects.requireNonNull(getCommand("hub")).setExecutor(new LobbyCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}