package mprog.nl.programmeerprojectdaniel;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 * Programmeer Project
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/*
 * Activity that adds the users words into the database, in the corresponding list
 */
public class AddWords extends AppCompatActivity {

    // Declaring the DBHelper file and the XML items
    DBHelper dbHelper;
    Button doneButton;
    Button addButton;
    EditText dutchWordInput;
    EditText englishWordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        // Retrieve the list name from the previous activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String listName = extras.getString("name");

        // Initialising the DBHelper class and the XML items
        dbHelper = new DBHelper(this, null, null, 1);
        dutchWordInput = (EditText) findViewById(R.id.addDutch);
        englishWordInput = (EditText) findViewById(R.id.addEnglish);
        addButton = (Button) findViewById(R.id.addButton);
        doneButton = (Button) findViewById(R.id.doneButton);

        // onClick listener for the add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieves the words from the EditText given by the user
                String dutchWord = dutchWordInput.getText().toString();
                String englishWord = englishWordInput.getText().toString();

                // Execute the addWords function that adds the words and list name to the database
                dbHelper.addWords(dutchWord, englishWord, listName);

                // Inform the user the words and the list have been successfully added
                Toast.makeText(AddWords.this, "Words " + dutchWord + " and " + englishWord + " added to " + listName, Toast.LENGTH_SHORT).show();

                // Clears the text fields for the next word to be entered
                dutchWordInput.setText("");
                englishWordInput.setText("");
            }
        });

        // onClick listener for the done button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creates dialog window for confirmation of completion
                AlertDialog.Builder builder = new AlertDialog.Builder(AddWords.this);
                builder
                        .setMessage("Is your list finished?")
                        .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // If the user is done creating the list, send back to the Practise activity
                                Intent practiseIntent = new Intent(AddWords.this, Practise.class);
                                startActivity(practiseIntent);
                                finish();
                            }
                        })
                        // Nothing is done when "No" is pressed
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });
    }
}
