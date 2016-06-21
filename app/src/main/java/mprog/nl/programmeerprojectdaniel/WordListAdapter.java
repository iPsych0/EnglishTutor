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
public class WordListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> wordsArrayDutch;
    private ArrayList<String> wordsArrayEnglish;
    String result1;
    String result2;

    // Constructor
    public WordListAdapter(Context context, ArrayList<String> wordsArrayDutch, ArrayList<String> wordsArrayEnglish) {
        this.context = context;
        this.wordsArrayDutch = wordsArrayDutch;
        this.wordsArrayEnglish = wordsArrayEnglish;
    }

    @Override
    public int getCount() {
        return this.wordsArrayDutch.size();
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
            view = inflater.inflate(R.layout.row_wordlists, parent, false);
        }

        // Sets the text of TextView to the word of the chosen language
        result1 = wordsArrayDutch.get(pos);
        result2 = wordsArrayEnglish.get(pos);

        TextView word1 = (TextView) view.findViewById(R.id.word1);
        TextView word2 = (TextView) view.findViewById(R.id.word2);
        word1.setText(result1);
        word2.setText(result2);

        return view;
    }
}