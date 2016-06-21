package mprog.nl.programmeerprojectdaniel;

/* Student name: Daniel Oliemans
 * Student number: 11188669
 * Universiteit van Amsterdam
 * Programmeer Project
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import java.util.ArrayList;

/*
 * DBHelper is a manager file that contains functions for the databases used in the Practise,
 * NewList, AddWords and Exercises activities.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Declaring context, database name and database version
    Context context;
    String name;
    int version;

    // Setting the database parameters
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "wordlist.db";
    public static final String TABLE_WORDS =  "wordListTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LISTNAME = "listName";
    public static final String COLUMN_DUTCH = "dutchWord";
    public static final String COLUMN_ENGLISH = "englishWord";

    // DBHelper constructor
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        this.context = context;
        this.name = DATABASE_NAME;
        this.version = DATABASE_VERSION;
    }

    /*
     * onCreate method that creates table on first launch
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_WORDS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LISTNAME + " TEXT, " +
                COLUMN_DUTCH + " TEXT, " +
                COLUMN_ENGLISH + " TEXT" + ");";
        db.execSQL(query);
    }

    /*
     * If the database is updated, drop the previous version(s) of the table and create a new one
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    /*
     * Function to add the name of the list and the words to the database
     */
    public void addWords(String dutchWord, String englishWord, String listName){
        ContentValues content = new ContentValues();
        content.put(COLUMN_LISTNAME, listName);
        content.put(COLUMN_DUTCH, dutchWord);
        content.put(COLUMN_ENGLISH, englishWord);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WORDS, null, content);
        db.close();
    }

    /*
     * Function to delete the rows that belongs to the list that is longClicked on
     */
    public void deleteLists(String listName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORDS + " WHERE " + COLUMN_LISTNAME + "=\"" + listName + "\";");
        db.close();
    }

    /*
     * Function that queries over the Dutch words stored in a list and returns them as an  ArrayList
     */
    public ArrayList<String> getDutchWords(String listName){
        ArrayList<String> wordsArrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_WORDS + " WHERE " + COLUMN_LISTNAME + "='" + listName + "';";

        Cursor c = db.rawQuery(query, null);
        // Set cursor to first
        if (c.moveToFirst()) {
            // Loop through the database and retrieves the Dutch and English words
            do {
                wordsArrayList.add(c.getString(2));
            } while (c.moveToNext()) ;
        }
        // Close database and cursor and return the ArrayList of rows
        db.close();
        c.close();

        return wordsArrayList;
    }

    /*
     * Function that queries over the English words stored in a list and returns them as ArrayList
     */
    public ArrayList<String> getEnglishWords(String listName){
        ArrayList<String> wordsArrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_WORDS + " WHERE " + COLUMN_LISTNAME + "='" + listName + "';";

        Cursor c = db.rawQuery(query, null);
        // Set cursor to first
        if (c.moveToFirst()) {
            // Loop through the database and retrieves the Dutch and English words
            do {
                wordsArrayList.add(c.getString(3));
            } while (c.moveToNext()) ;
        }
        // Close database and cursor and return the ArrayList of rows
        db.close();
        c.close();

        return wordsArrayList;
    }

    /*
     * Function that queries over all unique list names and returns an ArrayList of all unique lists
     */
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
