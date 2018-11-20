package epping.ian.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;
import android.widget.ResourceCursorAdapter;


public class EntryAdapter extends ResourceCursorAdapter{

    EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor, 0);
    }

    // create title, time and mood for main listview
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int columnIndexTitle = cursor.getColumnIndex("Title");
        String columnTitle = cursor.getString(columnIndexTitle);

        int columnIndexTime = cursor.getColumnIndex("Time");
        String columnTime = cursor.getString(columnIndexTime);

        int columnIndexMood = cursor.getColumnIndex("Mood");
        String columnMood = cursor.getString(columnIndexMood);

        // get references to control layout entry_row
        TextView titled = view.findViewById(R.id.title);
        TextView moody = view.findViewById(R.id.mood);
        TextView timed = view.findViewById(R.id.time);

        titled.setText(columnTitle);
        timed.setText(columnTime);
        moody.setText(columnMood);

    }
}
