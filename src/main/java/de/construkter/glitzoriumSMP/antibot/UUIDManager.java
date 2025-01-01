package de.construkter.glitzoriumSMP.antibot;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UUIDManager {

    private final File file;

    public UUIDManager(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void addUUID(String name, String uuid) throws IOException {
        Map<String, String> data = loadFromFile();
        data.put(name, uuid);
        saveToFile(data);
    }

    public String getUUID(String name) throws IOException {
        Map<String, String> data = loadFromFile();
        return data.getOrDefault(name, null);
    }

    private Map<String, String> loadFromFile() throws IOException {
        Map<String, String> data = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    data.put(parts[0], parts[1]);
                }
            }
        }
        return data;
    }

    private void saveToFile(Map<String, String> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        }
    }
}
