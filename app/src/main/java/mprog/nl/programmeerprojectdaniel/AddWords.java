package mprog.nl.programmeerprojectdaniel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddWords extends AppCompatActivity {

    Button doneButton;
    Button addButton;
    EditText dutchWord;
    EditText englishWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        dutchWord = (EditText)findViewById(R.id.addDutch);
        englishWord = (EditText)findViewById(R.id.addEnglish);

        // TODO get buttons working
        /*
        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String dutchInput = dutchWord.getText().toString();
                String englishInput = englishWord.getText().toString();
                DBHelper DbHelper = new DBHelper(this);
                DbHelper.addWords(dutchInput);
                DbHelper.addWords(englishInput);
                dutchWord.setText("");
                englishWord.setText("");
            }
        });
*/
        doneButton = (Button)findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent practise = new Intent(view.getContext(), Practise.class);
                practise.putExtra("practise", view.getId());
                startActivity(practise);
                finish();
            }
        });
    }
}
