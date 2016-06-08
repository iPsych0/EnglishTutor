package mprog.nl.programmeerprojectdaniel;

/**
 * Created by Jasper school on 31-5-2016.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wordlist.db";
    public static final String TABLE_WORDS = "wordlist";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LISTID = "_listId";
    public static final String COLUMN_DUTCH = "dutchWord";
    public static final String COLUMN_ENGLISH = "englishWord";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_WORDS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LISTID + "INTEGER " +
                COLUMN_DUTCH + " TEXT " +
                COLUMN_ENGLISH + " TEXT " + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    public void addWords(Words words){
        ContentValues values =  new ContentValues();
        values.put(COLUMN_DUTCH, words.get_dutchWord());
        values.put(COLUMN_ENGLISH, words.get_englishWord());
        values.put(COLUMN_LISTID, words.get_listId());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WORDS, null, values);
        db.close();
    }

    public void deleteWords(String words){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORDS + " WHERE " + COLUMN_DUTCH + "=\"" + words + "\";");
        db.execSQL("DELETE FROM " + TABLE_WORDS + " WHERE " + COLUMN_ENGLISH + "=\"" + words + "\";");
    }

    public ArrayList<Words> getWordLists(){
        ArrayList<Words> wordsArrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_WORDS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        // Set cursor to first
        if (c.moveToFirst()) {
            // Loop through database
            do {
                // Create a new list
                Words list = new Words();
                list.set_id(c.getInt(0));
                list.set_dutchWord(c.getString(1));
                list.set_englishWord(c.getString(1));
                // Add list
                wordsArrayList.add(list);
            } while (c.moveToNext()) ;
        }
        // Close database and cursor and return the arraylist of lists
        db.close();
        c.close();
        return wordsArrayList;
    }
}
