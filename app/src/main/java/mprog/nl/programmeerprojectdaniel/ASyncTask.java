package mprog.nl.programmeerprojectdaniel;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 * Programmeer Project
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Parses over the JSON given from the query string results
 */
public class ASyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    Dictionary dictionary;

    // Constructor
    public ASyncTask(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.context = this.dictionary.getApplicationContext();
    }


    // Loading the query
    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Loading data...", Toast.LENGTH_SHORT).show();
    }

    // Parses the query
    @Override
    protected String doInBackground(String... params) {
        // Gets the data
        return HTTPRequestHelper.downloadFromServer(params);
    }

    // Updates the values
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    // Retrieves the XML from the query request
    @Override
    protected void onPostExecute(String result_dictionary) {
        super.onPostExecute(result_dictionary);
        // If the JSON result is empty (meaning: word not in the dictionary) inform the user
        if (result_dictionary.equals("{\"head\":{},\"def\":[]}")) {
            Toast.makeText(context, "Word not found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, result_dictionary, Toast.LENGTH_LONG).show();
            try {
                //Create JSON object from the query result
                JSONObject resultObject = new JSONObject(result_dictionary);

                // Specify JSON to the array "def"
                JSONArray defArray = resultObject.getJSONArray("def");

                // Parse over the array of "def" which contains the translation & word type fields
                for (int i = 0; i < defArray.length(); i++) {
                    System.out.println("defArray is: " + defArray);
                    System.out.println("resultObject is: " + resultObject);
                    JSONObject translationObject = defArray.getJSONObject(i);
                    String translation = translationObject.getString("text");
                    String wordType = translationObject.getString("pos");
                    System.out.println("String translation is: " + translation);
                    System.out.println("String wordType is: " + wordType);
                    this.dictionary.translationData(translation, wordType);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}