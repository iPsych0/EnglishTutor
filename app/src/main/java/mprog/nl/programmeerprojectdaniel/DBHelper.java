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

    Context context;
    String name;
    int version;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wordlist.db";
    public static final String TABLE_WORDS =  "wordListTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LISTNAME = "_listName";
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
                COLUMN_LISTNAME + " TEXT " +
                COLUMN_DUTCH + " TEXT " +
                COLUMN_ENGLISH + " TEXT " + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    /*
    public void addList(String listName){
        SQLiteDatabase db = getWritableDatabase();
        String query = "CREATE TABLE " + listName + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LISTNAME + " TEXT " +
                COLUMN_DUTCH + " TEXT " +
                COLUMN_ENGLISH + " TEXT " + ");";
        db.execSQL(query);
    }
*/
    public void addWords(String dutchWord, String englishWord, String listName){
        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO " + TABLE_WORDS + "(" +
                COLUMN_ID + ", " +
                COLUMN_LISTNAME + ", " +
                COLUMN_DUTCH + ", " +
                COLUMN_ENGLISH + ") " +
                " VALUES " + "(" + dutchWord + ", " +
                englishWord + ", " + listName + ");";
        db.execSQL(query);
        db.close();
    }

    public void deleteWords(String words, String listName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORDS + " WHERE " + COLUMN_DUTCH + "=\"" + words + "\";");
        db.execSQL("DELETE FROM " + TABLE_WORDS + " WHERE " + COLUMN_ENGLISH + "=\"" + words + "\";");
        db.execSQL("DELETE FROM " + TABLE_WORDS + " WHERE " + COLUMN_LISTNAME + "=\"" + words + "\";");
        db.close();
    }

    public ArrayList<String> getListLists(){
        // Make arraylist to store listname strings
        ArrayList<String> listsArrayList = new ArrayList<>();

        // Get database and get all listname items
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_WORDS + " WHERE " + COLUMN_LISTNAME;

        // Set cursor on returned listname items
        Cursor c = db.rawQuery(query, null);

        // move through listname items and add each to arraylist listarraylist
        if (c.moveToFirst()){
            do{
                if(listsArrayList != null){
                    listsArrayList.add(c.getString(1));
                }
                else{
                    listsArrayList.add("TEST");
                }
            } while(c.moveToNext());
        }
        db.close();
        c.close();

        return listsArrayList;
    }

    public ArrayList<Words> getWordLists(String listName){
        ArrayList<Words> wordsArrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_WORDS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        // Set cursor to first
        if (c.moveToFirst()) {
            // Loop through database
            do {
                // Create a new word row
                Words wordRow = new Words();
                wordRow.set_id(c.getInt(0));
                wordRow.set_listName(c.getString(1));
                wordRow.set_dutchWord(c.getString(2));
                wordRow.set_englishWord(c.getString(3));
                // Add word row
                wordsArrayList.add(wordRow);
            } while (c.moveToNext()) ;
        }
        // Close database and cursor and return the ArrayList of rows
        db.close();
        c.close();

        return wordsArrayList;
    }

    public String checkLists(String listName) {
        SQLiteDatabase db = getWritableDatabase();
        // Query for the max current listId
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_WORDS + " WHERE " + COLUMN_LISTNAME, null);

        // Move cursor over the query
        if (c.moveToFirst()){
            do{
                // Retrieves the highest listId integer and increments by 1 for the next word list
                listName = c.getString(1);
            } while (c.moveToNext());
        }
        // Close files to save memory and returns the highest listId
        db.close();
        c.close();

        return listName;
    }
}
