package mprog.nl.programmeerprojectdaniel;

/**
 * Created by Jasper school on 31-5-2016.
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    String name;
    int version;

    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "wordlist.db";
    public static final String TABLE_WORDS =  "wordListTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LISTNAME = "listName";
    public static final String COLUMN_DUTCH = "dutchWord";
    public static final String COLUMN_ENGLISH = "englishWord";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        this.context = context;
        this.name = DATABASE_NAME;
        this.version = DATABASE_VERSION;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_WORDS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LISTNAME + " TEXT, " +
                COLUMN_DUTCH + " TEXT, " +
                COLUMN_ENGLISH + " TEXT" + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    public void addWords(String dutchWord, String englishWord, String listName){
        ContentValues content = new ContentValues();
        content.put(COLUMN_LISTNAME, listName);
        content.put(COLUMN_DUTCH, dutchWord);
        content.put(COLUMN_ENGLISH, englishWord);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WORDS, null, content);
        db.close();
    }

    public void deleteLists(String listName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORDS + " WHERE " + COLUMN_LISTNAME + "=\"" + listName + "\";");
        db.close();
    }

    public ArrayList<String> getWordLists(String listName){
        ArrayList<String> wordsArrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_WORDS + " WHERE " + COLUMN_LISTNAME + "='" + listName + "';";

        Cursor c = db.rawQuery(query, null);
        // Set cursor to first
        if (c.moveToFirst()) {
            // Loop through database
            do {
                wordsArrayList.add(c.getString(2));
                wordsArrayList.add(c.getString(3));
            } while (c.moveToNext()) ;
        }
        // Close database and cursor and return the ArrayList of rows
        db.close();
        c.close();

        return wordsArrayList;
    }

    public ArrayList<String> checkLists() {
        ArrayList<String> listNameList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT DISTINCT " + COLUMN_LISTNAME + " FROM " + TABLE_WORDS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        // Move cursor over the query
        if (c.moveToFirst()){
            do{
                // Retrieves the listnames
                listNameList.add(c.getString(0));
            } while (c.moveToNext());
        }
        // Close files to save memory and returns the list names
        db.close();
        c.close();

        return listNameList;
    }
}
