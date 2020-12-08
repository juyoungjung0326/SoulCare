package com.example.soulcare;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class ContactDatabase extends SQLiteOpenHelper{

    public static final String CONTACTS_TABLE = "CONTACTS_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";

    public ContactDatabase(@Nullable Context context)
    {
        super(context,"contact.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableStatement = "CREATE TABLE " + CONTACTS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT)";
        db.execSQL(createTableStatement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public boolean insertContact(Contact_Class contactClass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, contactClass.getContact_name());
        cv.put(COLUMN_PHONE_NUMBER, contactClass.getContact_number());

        long insert = db.insert(CONTACTS_TABLE,null,cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public List<Contact_Class> getContacts()
    {
        List<Contact_Class> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CONTACTS_TABLE + " ORDER BY " + COLUMN_NAME;
        SQLiteDatabase db_list = this.getReadableDatabase();

        Cursor cursor = db_list.rawQuery(queryString,null);

        if(cursor.moveToFirst())
        {
            do {
                int contactID = cursor.getInt(0);
                String contactName = cursor.getString(1);
                String contactNumber = cursor.getString(2);

                Contact_Class newContact = new Contact_Class(contactID,contactName,contactNumber);
                returnList.add(newContact);

            }while(cursor.moveToNext());
        }
        else
        {

        }

        cursor.close();
        db_list.close();
        return returnList;
    }

    public boolean deleteRow(Contact_Class contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CONTACTS_TABLE + " WHERE " + COLUMN_ID +
                " = " + contact.getId();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
