package epping.ian.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import android.widget.AdapterView;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    ListView list;

    @Override
    // create main layout
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.listview);

        // link database and adapter to listview
        updateData();

        // link listview to listeners
        list.setOnItemClickListener(new ListClickListener());
        list.setOnItemLongClickListener(new OnItemLongClickListener());
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateData();
    }

    private void updateData() {

        // get context from database
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectall();
        EntryAdapter adapter = new EntryAdapter(MainActivity.this, cursor);

        // takes context from the databsase and stores it in list
        list = findViewById(R.id.listview);
        list.setAdapter(adapter);
    }

    // switch to input window when floating button is clicked
    public void FloatButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // switch to detail window when listed journal is clicked
    private class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            // retrieves clicked journal and sets the text
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            String title = cursor.getString(cursor.getColumnIndex("Title"));
            String content = cursor.getString (cursor.getColumnIndex("Content"));
            String mood = cursor.getString(cursor.getColumnIndex("Mood"));
            String timestamp = cursor.getString (cursor.getColumnIndex("Time"));

            JournalEntry clicked_Entry = new JournalEntry(title, content, mood, timestamp);

            intent.putExtra("clicked_Entry", clicked_Entry);

            // access to the new window
            startActivity(intent);
        }
    }

    // respond to prolonged journal clicks
    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {

        // removes clicked journal from list
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            long entry = cursor.getInt(cursor.getColumnIndex("_id"));

            // delete proper element
            db.delete(entry);
            updateData();
            return false;
        }
    }
}