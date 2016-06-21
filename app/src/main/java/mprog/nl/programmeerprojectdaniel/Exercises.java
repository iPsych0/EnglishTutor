package mprog.nl.programmeerprojectdaniel;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 * Programmeer Project
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Exercises extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Initialising all variables
    DBHelper dbHelper;
    ListView wordsListView;
    String chosenList;
    String language;
    ArrayList<String> wordList;
    Intent intent;
    Bundle extras;
    Button submitButton;
    EditText wordInput;
    String[] wordInputArray;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Instantiating DBHelper
        dbHelper = new DBHelper(this, null, null, 1);

        // Giving context to the XML items
        wordsListView = (ListView) findViewById(R.id.wordsListView);
        submitButton = (Button) findViewById(R.id.submitButton);
        wordInput = (EditText) findViewById(R.id.translationInputTV);
        score = (TextView) findViewById(R.id.score);

        // Retrieve the list name, word list and chosen language from Practise activity
        intent = getIntent();
        extras = intent.getExtras();
        chosenList = extras.getString("chosenlist");
        wordList = extras.getStringArrayList("wordlist");
        language = extras.getString("language");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        // Set the adapter to the ListView
        setAdapter();

        // onClick listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On click, execute the wordChecker function
                wordChecker();
            }
        });
    }

    /*
     * setAdapter loads in the TextViews and the EditTexts from custom adapter ExercisesAdapter
     */
    public void setAdapter(){
        intent = getIntent();
        extras = intent.getExtras();
        // If the chosen language is Dutch
        if(language.equals("dutch")) {
            ArrayList<String> wordsArrayDutch = dbHelper.getDutchWords(chosenList);
            // Loads in Dutch words from ExercisesAdapter and uses this to write in the ListView
            ExercisesAdapter adapter = new ExercisesAdapter(this, wordsArrayDutch);
            wordsListView.setAdapter(adapter);
        }
        // Else, chosen language is English
        else{
            ArrayList<String> wordsArrayEnglish = dbHelper.getEnglishWords(chosenList);
            // Loads in English words from ExercisesAdapter and uses this to write in the ListView
            ExercisesAdapter adapter = new ExercisesAdapter(this, wordsArrayEnglish);
            wordsListView.setAdapter(adapter);
        }
    }

    /*
     * Function that checks if the user input matches the corresponding word from the list
     */
    public void wordChecker(){
        // Keeps track of the score
        int scoreCounter = 0;

        // Loads in the Dutch and English words from the list separately
        ArrayList<String> dutchWords = dbHelper.getDutchWords(chosenList);
        ArrayList<String> englishWords = dbHelper.getEnglishWords(chosenList);

        for (int i = 0; i < dutchWords.size(); i++) {
            // getValueET from ExercisesAdapter fills wordInputArray with the values of the EditTexts
            wordInputArray = (ExercisesAdapter.getValueET());
        }

        // If the user chose EN-NL, parse over the Dutch words in the EditTexts
        if(language.equals("dutch")) {
            for (int j = 0; j < englishWords.size(); j++) {
                // Compare user's input to the actual answer
                if (wordInputArray[j].equals(englishWords.get(j))) {
                    System.out.println("Word " + wordInputArray[j] + " is the same as word " + englishWords.get(j));
                    // If the answer is correct, score + 1
                    scoreCounter++;
                } else {
                    System.out.println("Word " + wordInputArray[j] + " is not equal to " + englishWords.get(j));
                    // If the word is incorrect TODO: Set text to "" or red colour
                    // wordInputArray[j] = "";
                }
            }
        }
        // Else, the user chose NL-EN and parse over the English words in the EditTexts
        else{
            for (int k = 0; k < dutchWords.size(); k++) {
                // Compare user's input to the actual answer
                if (wordInputArray[k].equals(dutchWords.get(k))) {
                    System.out.println("Word " + wordInputArray[k] + " is the same as word " + dutchWords.get(k));
                    // If the answer is correct, score + 1
                    scoreCounter++;
                } else {
                    System.out.println("Word " + wordInputArray[k] + " is not equal to " + dutchWords.get(k));
                    // If the word is incorrect TODO: Set text to "" or red colour
                    // wordInputArray[k] = "";
                }
            }
        }
        // Turn scoreCounter into a String that shows the user their final score
        String scoreResult = String.valueOf(scoreCounter) + " out of " + String.valueOf(dutchWords.size());
        score.setText(scoreResult);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exercises, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.homeButton) {
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
        } else if (id == R.id.dictionaryButton) {
            Intent dictionary = new Intent(this, Dictionary.class);
            startActivity(dictionary);
        } else if (id == R.id.practiseButton) {
            Intent practise = new Intent(this, Practise.class);
            startActivity(practise);
        } else if (id == R.id.wordListButton) {
            Intent wordList = new Intent(this, WordLists.class);
            startActivity(wordList);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
