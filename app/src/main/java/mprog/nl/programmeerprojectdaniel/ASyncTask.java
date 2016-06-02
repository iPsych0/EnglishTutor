package mprog.nl.programmeerprojectdaniel;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 */

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
        //if(result_dictionary.length() == 0){
            Toast.makeText(context, result_dictionary, Toast.LENGTH_SHORT).show();
        //}
    }
}