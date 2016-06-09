package mprog.nl.programmeerprojectdaniel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddWords extends AppCompatActivity {

    DBHelper dbHelper;
    Button doneButton;
    Button addButton;
    EditText dutchWordInput;
    EditText englishWordInput;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String listName = extras.getString("name");
        // TODO add adapter to listview!

        context = this;
        dbHelper = new DBHelper(context, null, null, 1);
        dbHelper.checkLists(listName);

        dutchWordInput = (EditText)findViewById(R.id.addDutch);
        englishWordInput = (EditText)findViewById(R.id.addEnglish);

        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String dutchWord = dutchWordInput.getText().toString();
                String englishWord = englishWordInput.getText().toString();

                dbHelper.addWords(dutchWord, englishWord, listName);
                dutchWordInput.setText("");
                englishWordInput.setText("");
            }
        });
        doneButton = (Button)findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent practiseIntent = new Intent(view.getContext(), Practise.class);
                practiseIntent.putExtra("practise", view.getId());

                Bundle bundle = new Bundle();
                bundle.putString("name", listName);

                practiseIntent.putExtras(bundle);
                startActivity(practiseIntent);
                finish();
            }
        });
    }
}
