package com.example.soulcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper3 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dinner.db";
    public static final String TABLE_NAME = "dinner_table";
    public static final String COL0 = "ID";
    public static final String COL1 = "FOOD";
    public static final String COL2 = "SERVING";
    public static final String COL3 = "CALORIES";

    public DatabaseHelper3(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FOOD TEXT, SERVING TEXT, CALORIES TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String food, String serving, String calories){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1,food);
        cv.put(COL2,serving);
        cv.put(COL3,calories);

        long result  = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean updateData(String id, String food, String serving, String cal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL0,id);
        cv.put(COL1,food);
        cv.put(COL2,serving);
        cv.put(COL3,cal);
        db.update(TABLE_NAME, cv, "ID = ?", new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
