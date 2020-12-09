package com.example.soulcare;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class InfoDatabase extends SQLiteOpenHelper{

    public static final String Info_TABLE = "INFO_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";
    public static final String COLUMN_NEXT_OF_KIN = "NEXT_OF_KIN";
    public static final String PASSWORD = "PASSWORD";

    public InfoDatabase(@Nullable Context context)
    {
        super(context,"info.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableStatement = "CREATE TABLE " + Info_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT, " + COLUMN_NEXT_OF_KIN + " BOOLEAN)";
        db.execSQL(createTableStatement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public boolean insertContact(Info_Class infoClass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, infoClass.getContact_name());
        cv.put(COLUMN_ID, infoClass.getId());
        cv.put(PASSWORD, infoClass.getPass());
        cv.put(COLUMN_PHONE_NUMBER, infoClass.getContact_number());
        cv.put(COLUMN_NEXT_OF_KIN,infoClass.getNextOfKin());

        long insert = db.insert(Info_TABLE,null,cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public List<Info_Class> getContacts()
    {
        List<Info_Class> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + Info_TABLE + " ORDER BY " + COLUMN_NAME;
        SQLiteDatabase db_list = this.getReadableDatabase();

        Cursor cursor = db_list.rawQuery(queryString,null);

        if(cursor.moveToFirst())
        {
            do {
                int contactID = cursor.getInt(0);
                String contactName = cursor.getString(1);
                String contactNumber = cursor.getString(3);
                boolean nextOfKin = cursor.getInt(4) == 1 ? true: false;
                String password = cursor.getString(2);
                Info_Class newPerson = new Info_Class(contactID, password, contactName,contactNumber, nextOfKin);
                returnList.add(newPerson);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db_list.close();
        return returnList;
    }


}
