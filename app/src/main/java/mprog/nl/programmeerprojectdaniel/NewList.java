package mprog.nl.programmeerprojectdaniel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewList extends AppCompatActivity {

    Button addListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        addListButton = (Button) findViewById(R.id.addListButton);
        addListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent addWords = new Intent(view.getContext(), AddWords.class);
                addWords.putExtra("addWords", view.getId());
                startActivity(addWords);
                finish();
            }
        });
    }
}
