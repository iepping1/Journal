package epping.ian.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class DetailActivity extends AppCompatActivity {

    // create journal window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        // catch journal
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("clicked_Entry");

        // add proper data
        TextView title = findViewById(R.id.title);
        TextView time = findViewById(R.id.timestamp);
        TextView mood = findViewById(R.id.mood);
        TextView content = findViewById(R.id.content);

        title.setText(entry.getTitle());
        time.setText(entry.getTime());
        mood.setText(entry.getMood());
        content.setText(entry.getContent());
    }

    // move to input window when clicked
    public void FloatButtonClicked(View view){
        Intent intent = new Intent(DetailActivity.this, InputActivity.class);
        startActivity(intent);
    }
}
