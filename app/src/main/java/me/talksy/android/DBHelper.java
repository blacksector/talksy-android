package me.talksy.android;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    // Database settings
    public static final String DATABASE_NAME = "talksyContacts.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "profilePic";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS contacts(id integer primary key, name text, phone text, email text, profilePic text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact  (String name, String phone, String email, String profilePic) {

        // Setup connection - writable
        SQLiteDatabase db = this.getWritableDatabase();

        // Set Values
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("profilePic", profilePic);

        // Insert into database
        db.insert("contacts", null, contentValues);

        // return success
        return true;
    }

    public Cursor getData(int id){
        // Setup connection - read only
        SQLiteDatabase db = this.getReadableDatabase();

        // Query database
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );

        // Return all results - Type Cursor
        return res;
    }

    public int numberOfRows() {
        // Setup connection - read only
        SQLiteDatabase db = this.getReadableDatabase();

        // Query database for number of entries
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);

        // Return number of rows - Type int
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String profilePic) {
        // Setup connection - Writable
        SQLiteDatabase db = this.getWritableDatabase();

        // Set updated values
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("profilePic", profilePic);

        // Update all values and save
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );

        // return true as success
        return true;
    }

    public Integer deleteContact (Integer id) {
        // Setup connection - writable
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete entry at X position - return 1 if success, return 0 if failed.
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {

        // Setup a array list to hold all the contact information
        ArrayList<String> array_list = new ArrayList<String>();

        // Setup connection - read only
        SQLiteDatabase db = this.getReadableDatabase();

        // Get raw data from table - everything
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        // add the contacts one by one to array_list
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        // and finally, return the array_list. Type ArrayList
        return array_list;
    }
}