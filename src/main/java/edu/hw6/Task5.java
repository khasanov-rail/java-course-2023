package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {

    private Task5() {
    }

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    public static long[] hackerNewsTopStories() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOP_STORIES_URL))
                .build();

            HttpResponse<String> response = HTTP_CLIENT.send(request, BodyHandlers.ofString());
            String jsonResponse = response.body();
            jsonResponse = jsonResponse.substring(1, jsonResponse.length() - 1);
            String[] ids = jsonResponse.split(",");
            long[] longIds = new long[ids.length];
            for (int i = 0; i < ids.length; i++) {
                longIds[i] = Long.parseLong(ids[i].trim());
            }
            return longIds;
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    public static String news(long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(ITEM_URL, id)))
                .build();

            HttpResponse<String> response = HTTP_CLIENT.send(request, BodyHandlers.ofString());
            String jsonResponse = response.body();
            Pattern pattern = Pattern.compile("\"title\":\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(jsonResponse);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return "";
        } catch (IOException | InterruptedException e) {
            return "";
        }
    }
}
