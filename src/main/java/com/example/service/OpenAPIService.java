package com.example.service;

import com.cedarsoftware.util.io.JsonWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OpenAPIService {

    public String getApiRequest() throws IOException {

        URL getUrl = new URL("https://cat-fact.herokuapp.com/facts/random");
//        Propublica:Andy String apiKey = "O1ZdWmc8x27g8x05YHkc0VYKHfCBYTTTuvDAt4Kn";

        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();


        // Set request method
        connection.setRequestMethod("GET");

//        connection.setRequestProperty("x-api-key",apiKey);


        // Getting response code
        int responseCode = connection.getResponseCode();

        // If responseCode is 200 means we get data successfully
        if (responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder jsonResponseData = new StringBuilder();


            String readLine = null;
            // Append response line by line
            while ((readLine = in.readLine()) != null){
                    jsonResponseData.append(readLine);
                }

            in.close();
            // Print result in string format
            return JsonWriter.formatJson(jsonResponseData.toString());
        } else {
            return String.valueOf(responseCode);
        }
    }

}