package mprog.nl.programmeerprojectdaniel;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 * Programmeer Project
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * Queries the HTTP requests and parses the query string
 */
public class HTTPRequestHelper {

    // Yandex Dictionary API URL and API key
    private static final String api_endpoint = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup";
    private static final String api_key = "?key=dict.1.1.20160602T095140Z.4092fa3a976c0a40.a0925400b100b04e96fa26c6bec99b76e2ee292e";

    // Method to download from server
    protected static synchronized String downloadFromServer(String...params){
        // Declare return string for dictionary request
        String result_dictionary = "";

        // Chosen language from argument
        String chosenLanguage = params[0];

        // Chosen text from argument
        String chosenWord = params[1];

        // Complete string url for dictionary translations
        String dictionaryUrl = api_endpoint + api_key + "&lang=" + chosenLanguage + "&text=" + chosenWord;

        // Turn string into url
        URL url = null;
        try {
            url = new URL(dictionaryUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Make the connection
        HttpURLConnection connection;
        if(url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();

                // Open connection, set request method
                connection.setRequestMethod("GET");

                // Get response code
                Integer responseCode = connection.getResponseCode();

                // If 200-300, read inputstream
                if (200 <= responseCode && responseCode <= 299) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result_dictionary = result_dictionary + line;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Return the JSON string
        return result_dictionary;
    }
}
