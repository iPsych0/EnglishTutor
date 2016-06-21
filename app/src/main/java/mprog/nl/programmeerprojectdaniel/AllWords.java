package mprog.nl.programmeerprojectdaniel;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllWords extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    DBHelper dbHelper;
    Bundle extras;
    ListView allWordsLV;
    String chosenList;
    ArrayList<String> wordListDutch;
    ArrayList<String> wordListEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_words);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();
        extras = intent.getExtras();
        allWordsLV = (ListView)findViewById(R.id.wordList);
        chosenList = extras.getString("chosenlist");
        wordListDutch = extras.getStringArrayList("dutchwordlist");
        wordListEnglish = extras.getStringArrayList("englishwordlist");
        dbHelper = new DBHelper(this, null, null, 1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        setAdapter();
    }

    public void setAdapter(){
        intent = getIntent();
        extras = intent.getExtras();
            WordListAdapter adapter = new WordListAdapter(this, wordListDutch, wordListEnglish);
            allWordsLV.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.all_words, menu);
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
