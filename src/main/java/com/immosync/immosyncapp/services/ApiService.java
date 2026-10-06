package com.immosync.immosyncapp.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private final String API_KEY = "AIzaSyC0ASXcmYMVfjcN3XtQY4JtN8ExzLpczVk";

    public double getDistanceKm(String villeChantier, String villeEntrepreneur) {
        try {
            String vA = villeChantier.trim().replace(" ", "%20");
            String vB = villeEntrepreneur.trim().replace(" ", "%20");

            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
                    + vA + "&destinations=" + vB + "&key=" + API_KEY;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());
            if (!json.getString("status").equals("OK")) {
                System.out.println("Google API Error Status: " + json.getString("status"));
                if (json.has("error_message")) System.out.println("Message: " + json.getString("error_message"));
                return -1;
            }
            if (json.getJSONArray("rows").length() > 0) {
                JSONObject element = json.getJSONArray("rows")
                        .getJSONObject(0)
                        .getJSONArray("elements")
                        .getJSONObject(0);

                String elementStatus = element.getString("status");

                if (elementStatus.equals("OK")) {
                    int distanceMetres = element.getJSONObject("distance").getInt("value");
                    return distanceMetres / 1000.0;
                } else {
                    System.out.println("Itinéraire impossible entre " + villeChantier + " et " + villeEntrepreneur + " (Status: " + elementStatus + ")");
                }
            }

            return -1;

        } catch (Exception e) {
            System.err.println("Erreur technique ApiService: " + e.getMessage());
            return -1;
        }
    }
}