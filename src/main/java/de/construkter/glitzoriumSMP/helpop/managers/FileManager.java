package de.construkter.glitzoriumSMP.helpop.managers;

import de.construkter.glitzoriumSMP.GlitzoriumSMP;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileManager {
    public final String fileName;
    public final String filePath;
    public final File file;
    public final FileConfiguration fileConfiguration;

    public FileManager(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.file = new File(GlitzoriumSMP.getInstance().getDataFolder() + filePath, fileName + ".yml");
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getFileConfiguration() {return this.fileConfiguration;}

    public void saveFile() {
        try {
            this.fileConfiguration.save(this.file);
        } catch (Exception e) {
           System.out.println("Error saving file: " + e.getMessage());
        }
    }
}