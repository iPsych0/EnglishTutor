package mprog.nl.programmeerprojectdaniel;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 * Programmeer Project
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * Activity that stores the listname and passes it to the AddWords activity
 */
public class NewList extends AppCompatActivity {

    // Declaring the EditText and Button with the DBHelper class
    Button addListButton;
    EditText addListName;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        // Initialising the EditText, Button and DBHelper class
        dbHelper = new DBHelper(this, null, null, 1);
        addListName = (EditText)findViewById(R.id.addListName);
        addListButton = (Button) findViewById(R.id.addListButton);

        // onClick listener for the add button
        addListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // Retrieves the list name given by the user
                String listNameInput = addListName.getText().toString();

                // Ensures valid characters are submitted
                if(!listNameInput.matches("[a-zA-Z1-9\\s]+")){
                    addListName.setText("");
                    Toast.makeText(NewList.this, "Please enter valid characters", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Ensures list name doesn't already exist to prevent duplicate lists
                    if (dbHelper.checkLists().contains(listNameInput)) {
                        Toast.makeText(NewList.this, "This list already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        // Inform user that list entry is valid and has been created
                        Toast.makeText(NewList.this, "List: " + listNameInput + " is created.", Toast.LENGTH_SHORT).show();
                        Intent addWordsIntent = new Intent(view.getContext(), AddWords.class);

                        // Pass the list name on to the AddWords activity
                        Bundle bundle = new Bundle();
                        bundle.putString("name", listNameInput);

                        addWordsIntent.putExtras(bundle);
                        startActivity(addWordsIntent);
                        finish();
                    }
                }
                }
            });
        }
    }
