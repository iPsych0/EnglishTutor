package mprog.nl.programmeerprojectdaniel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordLists extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listsLV;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        listsLV = (ListView) findViewById(R.id.wordLists);
        dbHelper = new DBHelper(this, null, null, 1);

        // Fill the ArrayList with all list names
        ArrayList<String> listsArrayList = dbHelper.checkLists();

        // Set the ArrayAdapter to display the ArrayList of lists
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listsArrayList);
        listsLV.setAdapter(arrayAdapter);

        // If item is clicked, user goes to the corresponding Exercises activity
        listsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Determine chosen word list and initialises new intent
                final String chosenList = String.valueOf(adapterView.getItemAtPosition(position));
                final Intent allWordsIntent = new Intent(WordLists.this, AllWords.class);

                // Bundles the word list as string and bundles all words
                ArrayList<String> dutchWordsArray = dbHelper.getDutchWords(chosenList);
                ArrayList<String> englishWordsArray = dbHelper.getEnglishWords(chosenList);
                Bundle bundle = new Bundle();
                bundle.putString("chosenlist", chosenList);
                bundle.putStringArrayList("dutchwordlist", dutchWordsArray);
                bundle.putStringArrayList("englishwordlist", englishWordsArray);

                // Start AllWords activity
                allWordsIntent.putExtras(bundle);
                startActivity(allWordsIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.word_lists, menu);
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
            Toast.makeText(this, "You are already at Your Word Lists", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
