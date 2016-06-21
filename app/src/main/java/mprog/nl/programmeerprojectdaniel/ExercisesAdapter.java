package mprog.nl.programmeerprojectdaniel;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 */

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

/*
 * Custom adapter to read over the ArrayList
 */
public class ExercisesAdapter extends BaseAdapter {

    private Context context;
    private static ArrayList<String> wordsArray;
    private static String[] valueET;

    // Constructor
    public ExercisesAdapter(Context context, ArrayList<String> wordsArray) {
        this.context = context;
        this.wordsArray = wordsArray;
    }

    @Override
    public int getCount() {
        return this.wordsArray.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        if (view == null) {
            // Inflates the rows into the ListView
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_exercises, parent, false);
        }

        // Sets the text of TextView to the word of the chosen language
        String results = wordsArray.get(pos);
        TextView givenWord = (TextView) view.findViewById(R.id.givenWordTV);
        EditText translationInput = (EditText) view.findViewById(R.id.translationInputTV);
        givenWord.setText(results);
        translationInput.setText("");

        final int position = pos;
        valueET = new String[wordsArray.size()];

        // TextChangedListener for EditTexts
        translationInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Wait until user provides input
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Keep track of every character that is being added to the EditText
                valueET[position] = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Wait until user presses submit button
            }
        });
        return view;
    }

    /*
     * Returns the values of all EditTexts
     */
    public static String[] getValueET(){
        return valueET;
    }
}