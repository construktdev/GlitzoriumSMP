package de.construkter.glitzoriumSMP.ai;

import io.github.ollama4j.OllamaAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class AICommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "Usage: /ai <prompt>");
        }

        if (strings.length >= 0) {
            commandSender.sendMessage(ChatColor.RED + "Dies ist erst ein Beta Feature und kann noch nicht benutzt werden.");
            return true;
        }

        String prompt = String.join(" ", strings);
        commandSender.sendMessage(ChatColor.DARK_AQUA + "Deine Frage wird nun der KI gestellt...");

        String url = "http://127.0.0.1:11434/api/chat"; // Ollama-Server URL (angepasst, falls nötig)

        try {
            // Baue die HTTP-Anfrage
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("model", "llama3");
            jsonRequest.put("prompt", prompt);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest.toString(), StandardCharsets.UTF_8))
                    .build();

            // HTTP-Client erstellen
            HttpClient client = HttpClient.newHttpClient();

            // Anfrage senden und Antwort erhalten
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Antwort ausgeben
            if (response.statusCode() == 200) {
                System.out.println("Antwort: " + response.body());
            } else {
                System.out.println("Fehler: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
