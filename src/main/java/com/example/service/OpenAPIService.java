package com.example.service;



import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@Service
public class OpenAPIService {

    private static String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(bufferedReader);
            return new JSONObject(jsonText);
        }
    }

    public String getApiRequest() throws IOException, JSONException {

        URL getUrl = new URL("https://cat-fact.herokuapp.com/facts/random");
//        Propublica:Andy String apiKey = "O1ZdWmc8x27g8x05YHkc0VYKHfCBYTTTuvDAt4Kn";

        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();


        // Set request method
        connection.setRequestMethod("GET");

        //  connection.setRequestProperty("x-api-key",apiKey);

        // Getting response code
        int responseCode = connection.getResponseCode();

        // If responseCode is 200 means we get data successfully
        if (responseCode == HttpURLConnection.HTTP_OK) {

        JSONObject json = readJsonFromUrl(getUrl.toString());
        String factReturn = (String) json.get("text");

            // Print result in string format
            return factReturn;
        } else {
            return String.valueOf(responseCode);
        }
    }

}

