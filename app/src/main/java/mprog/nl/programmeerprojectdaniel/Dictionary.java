package mprog.nl.programmeerprojectdaniel;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 * Programmeer Project
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
 * Activity that allows users to translate words through the Yandex dictionary API
 */
public class Dictionary extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner languageSpinner;
    EditText translationInput;
    TextView translationText;
    TextView wordTypeText;
    Button translateButton;
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        translateButton = (Button) findViewById(R.id.translateButton);
        languageSpinner = (Spinner) this.findViewById(R.id.languageSpinner);
        translationInput = (EditText) this.findViewById(R.id.translationInput);
        translationText = (TextView) findViewById(R.id.translationResult);
        wordTypeText = (TextView) findViewById(R.id.wordType);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /*
     * This function starts an ASyncTask when the button is pressed
     */
    public void Translate(View view){
        String translation = translationInput.getText().toString();
        if(!translation.isEmpty()) {
            // Use selected option in languageSpinner for the query as parameter
            inputMethodManager.hideSoftInputFromWindow(translateButton.getWindowToken(), 0);
            // Execute the ASyncTask with the word given by the user
            ASyncTask aSyncTask = new ASyncTask(this);
            TextView txtView = (TextView) languageSpinner.getSelectedView();
            String languageSelected = txtView.getText().toString().toLowerCase();
            aSyncTask.execute(languageSelected, translation);
        }
    }

    /*
     * This function retrieves the translation strings, sets the TextViews and informs the user
     */
    public void translationData(String translation, String wordType){
        // Set the TextViews to the translated fields
        translationText.setText(translation);
        wordTypeText.setText(wordType);
        // Remind the user which word they translated for the given results
        Toast.makeText(Dictionary.this, "Word '" + translationInput.getText().toString() + "' translated." , Toast.LENGTH_LONG).show();
        translationInput.setText("");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dictionary, menu);
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
            home.putExtra("home", id);
            startActivity(home);
        } else if (id == R.id.dictionaryButton) {
            Toast.makeText(this, "You are already at Dictionary", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.practiseButton) {
            Intent practise = new Intent(this, Practise.class);
            practise.putExtra("practise", id);
            startActivity(practise);
        } else if (id == R.id.settingsButton) {
            Intent settings = new Intent(this, Settings.class);
            settings.putExtra("settings", id);
            startActivity(settings);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
