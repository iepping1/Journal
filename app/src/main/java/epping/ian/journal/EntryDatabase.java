package epping.ian.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class EntryDatabase extends SQLiteOpenHelper {

    // initialize database
    private EntryDatabase(@Nullable Context context, @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    private static EntryDatabase instance;

    // create new database if one doesn't exist yet
    public static EntryDatabase getInstance(Context context){
        if (instance == null){
            instance = new EntryDatabase(context, "db", null, 2);
        }
        return instance;
    }

    @Override
    // create table
    public void onCreate(SQLiteDatabase db) {
        String executable = "CREATE TABLE IF NOT EXISTS journals(_id INTEGER PRIMARY KEY AUTOINCREMENT, Title String," +
                            "Content String, Mood String, Time DATETIME default current_timestamp)";
        db.execSQL(executable);
    }

    @Override
    // clean old table to make room for new
    public void onUpgrade(SQLiteDatabase db, int oldData, int newData) {
        String executable = "DROP TABLE IF EXISTS " + "journals";
        db.execSQL(executable);
        onCreate(db);
    }

    // select all table entries
    public Cursor selectall(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM journals", null);
        return cursor;
    }

    // insert journal into database
    public void insert(JournalEntry entry){
        SQLiteDatabase thisDb = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", entry.getTitle());
        contentValues.put("content", entry.getContent());
        contentValues.put("mood", entry.getMood());

        thisDb.insert("journals", null, contentValues);
    }

    //delete journal from database
    public void delete(long id) {
        SQLiteDatabase thisDb = this.getWritableDatabase();
        thisDb.delete("journals", "_id" + '=' + id, null);
    }
}
