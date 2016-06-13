package mprog.nl.programmeerprojectdaniel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class AddWords extends AppCompatActivity {

    DBHelper dbHelper;
    Button doneButton;
    Button addButton;
    EditText dutchWordInput;
    EditText englishWordInput;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String listName = extras.getString("name");
        // TODO add adapter to listview!

        System.out.println(listName);

        dbHelper = new DBHelper(this, null, null, 1);

        dutchWordInput = (EditText) findViewById(R.id.addDutch);
        englishWordInput = (EditText) findViewById(R.id.addEnglish);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dutchWord = dutchWordInput.getText().toString();
                String englishWord = englishWordInput.getText().toString();

                //TODO add Toast
                dbHelper.addWords(dutchWord, englishWord, listName);
                Toast.makeText(AddWords.this, "Words " + dutchWord + " and " + englishWord + " added to " + listName, Toast.LENGTH_SHORT).show();
                dutchWordInput.setText("");
                englishWordInput.setText("");
                System.out.println(dbHelper.checkLists());

            }
        });
        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent practiseIntent = new Intent(view.getContext(), Practise.class);
                startActivity(practiseIntent);
                finish();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddWords Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://mprog.nl.programmeerprojectdaniel/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddWords Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://mprog.nl.programmeerprojectdaniel/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
