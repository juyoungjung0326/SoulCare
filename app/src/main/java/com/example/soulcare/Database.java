package com.example.soulcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {
    public static final String database_name = "user";
    public static final String table_name = "user_data";
    public static final String col_1 = "ID";
    public static final String col_2 = "Medicine_name";
    public static final String col_3 = "contact_phone";
    public static final String col_4 = "Time";
    public static final String record_table = "record";
    public static final String t_1 = "id";
    public static final String t_2 = "Type";
    public static final String t_3 = "Visit";
    public static final String t_4 = "Service";
    public Database(Context context) {
        super(context, database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String a = "CREATE TABLE " + table_name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + " Medicine_name TEXT, contact_phone TEXT, Time TEXT)";
        String b = "CREATE TABLE " + record_table + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + " Type TEXT, Visit TEXT, Service TEXT)";
        db.execSQL(a);
        db.execSQL(b);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + record_table);
        onCreate(db);
    }

    public boolean insertData(String medicine, String phone, String time ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues1 = new ContentValues();
        contentvalues1.put(col_2, medicine);
        contentvalues1.put(col_3, phone);
        contentvalues1.put(col_4, time);
        long result = db.insert(table_name, null, contentvalues1);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean insertRecord(String medicine, String phone, String time ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(t_2, medicine);
        contentvalues.put(t_3, phone);
        contentvalues.put(t_4, time);
        long result = db.insert(record_table, null, contentvalues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + table_name, null);
        return data;
    }
    public Cursor getRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + record_table, null);
        return data;
    }

    public Cursor getID(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + col_1 + " FROM " + table_name + " WHERE " + col_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTime(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + col_4 + " FROM " + table_name + " WHERE " + col_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getPhone(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + col_3 + " FROM " + table_name + " WHERE " + col_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public  void updateName(String n, int i, String o){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + table_name + " SET " + col_2 + " = '" + n + "'WHERE " + col_1 + " = '" + i + "'" + " AND " + col_2 + " = '"
                + o + "'";
        db.execSQL(query);
    }
    public  void deleteName(int i, String o){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + table_name + " WHERE " + col_1 + " = '" + i + "'" + " AND " + col_2 + " = '"
                + o + "'";
        //String query1 = " DELETE FROM " + table_name + " WHERE " + col_1 + " = -1";
        //db.execSQL(query1);
        db.execSQL(query);
    }

}

