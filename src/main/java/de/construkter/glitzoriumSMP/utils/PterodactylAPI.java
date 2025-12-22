package de.construkter.glitzoriumSMP.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PterodactylAPI {
    private final String apiKey;
    private final String apiURL;

    public PterodactylAPI(String apiKey, String panelURL) {
        this.apiKey = apiKey;
        this.apiURL = panelURL;
    }

    public boolean isInit() {
        return apiKey != null && apiURL != null;
    }

    public int sendSignal(String serverID, String signal) {
        if (!isInit()) {
            throw new IllegalStateException("PterodactylAPI is not initialized properly.");
        }

        String jsonBody = """
                {
                 "signal": "%s"
                }
                """.formatted(signal);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL + "/api/client/servers/" + serverID + "/power"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .header("Accept", "Application/vnd.pterodactyl.v1+json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<Void> repsonse = client.send(request, HttpResponse.BodyHandlers.discarding());
            return repsonse.statusCode();
        } catch (IOException e) {
            return 400;
        } catch (InterruptedException e) {
            return 500;
        }
    }
}
