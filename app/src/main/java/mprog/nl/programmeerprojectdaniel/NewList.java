package mprog.nl.programmeerprojectdaniel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewList extends AppCompatActivity {

    Button addListButton;
    EditText addListName;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        dbHelper = new DBHelper(this, null, null, 1);

        addListName = (EditText)findViewById(R.id.addListName);
        addListButton = (Button) findViewById(R.id.addListButton);
        addListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // TODO add adapter to listview!
                String listNameInput = addListName.getText().toString();
                //if(!listNameInput.matches("[a-zA-Z1-9\\s]+")){
                  //  addListName.setText("");
                //}
                System.out.println(dbHelper.checkLists());

                if (dbHelper.checkLists().contains(listNameInput)){
                    Toast.makeText(NewList.this, "This list already exists", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NewList.this, "List: " + listNameInput + " is created.", Toast.LENGTH_SHORT).show();
                    Intent addWordsIntent = new Intent(view.getContext(), AddWords.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("name", listNameInput);

                    addWordsIntent.putExtras(bundle);
                    startActivity(addWordsIntent);
                    finish();
                }
                }
            });
        }
    }
