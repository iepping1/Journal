package epping.ian.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class InputActivity extends AppCompatActivity {

    @Override

    // create input window
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    // save to database when submit button is clicked
    public void addEntry(View view) {
        EditText title = findViewById(R.id.title);
        EditText content = findViewById(R.id.content);
        TextView mood = findViewById(R.id.mood);

        String titled = title.getText().toString();
        String contented = content.getText().toString();
        String mooded = mood.getText().toString();

        JournalEntry entry = new JournalEntry(titled, contented, mooded, "");

        EntryDatabase.getInstance(getApplicationContext()).insert(entry);
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // change mood when emotion button is clicked
    public void happy(View view){
        TextView mood = findViewById(R.id.mood);
        mood.setText("happy");
    }

    public void neutral(View view){
        TextView mood = findViewById(R.id.mood);
        mood.setText("neutral");
    }

    public void sad(View view){
        TextView mood = findViewById(R.id.mood);
        mood.setText("sad");
    }

    // save mood when rotating phone
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        TextView mood = findViewById(R.id.mood);
        outState.putString("moody", String.valueOf(mood.getText()));
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        TextView mood = findViewById(R.id.mood);
        String moody = savedInstanceState.getString(("moody"));
        mood.setText(moody);
    }
}
