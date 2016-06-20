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
        } else try {
            //Create JSON object from the query result
            JSONObject resultObject = new JSONObject(result_dictionary);

            // Specify JSON to the array "def"
            JSONArray defArray = resultObject.getJSONArray("def");

            // Parse over the array of "def" which contains the translation & word type fields
            for (int i = 0; i < defArray.length(); i++) {
                // Load in all JSON objects in "def"
                JSONObject translationObject = defArray.getJSONObject(i);

                // Create an Array of the "tr" tag which contains the translated fields
                JSONArray trArray = translationObject.getJSONArray("tr");

                // The first object in "tr" contains the translation fields
                JSONObject trObject = trArray.getJSONObject(0);

                // Get the string values from the objects for translation and word type
                String translation = trObject.getString("text");
                String wordType = trObject.getString("pos");

                // Pass along the strings to the Dictionary activity
                dictionary.translationData(translation, wordType);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}