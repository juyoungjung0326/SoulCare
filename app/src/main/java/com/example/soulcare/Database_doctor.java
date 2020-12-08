package com.example.soulcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database_doctor extends SQLiteOpenHelper {
    public static final String database_name = "nn";
    public static final String table_doctor = "dc";
    public static final String col_1 = "ID";
    public static final String col_2 = "Doc_Name";
    public static final String col_3 = "Doc_phone";
    public static final String col_4 = "Doc_Address";
    public static final String col_5 = "City";
    public static final String col_6 = "Zip";
    public static final String col_7 = "State";
    public Database_doctor(Context context) {
        super(context, database_name, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String a = "CREATE TABLE " + table_doctor + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + " Doc_Name TEXT, Doc_phone TEXT, Doc_Address TEXT, City TEXT, Zip TEXT, State TEXT)";
        db.execSQL(a);
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Mustafa Alibhai', '9729481122', '6750 N Macarthur Bulevard', 'Irving', '75039', 'TX')");
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Jordan Knapp', '8103397809', '3339 Esters Road', 'Irving', '75061', 'TX')");
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Alison Nguyen', '8178909090', '3425 Grande Bulevar Blvd', 'Irving', '75062', 'TX')");
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Alicia Harbison', '8178602700', '1119 W Randol Mill RD', 'Arlington', '76012', 'TX')");
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Brent Bunnell', '8174670240', '400 W Arkbook Blvd', 'Arlington', '76014', 'TX')");
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Robert Strzinek', '8172814446', '1725 Chadwik ct', 'Hurst', '76054', 'TX')");
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Peter Sirianni', '9724567890', '560 W pipeline Rd', 'Hurst', '76053', 'TX')");
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Michael Karing', '8178579027', '479 Westpark Way', 'Euless', '76040', 'TX')");
        db.execSQL("INSERT INTO " + table_doctor + "(Doc_Name, Doc_phone, Doc_Address, City, Zip, State ) VALUES ('Anna Kowalska', '9724564545', '350 WestPark Way', 'Euless', '76040', 'TX')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_doctor);
        onCreate(db);
    }

    public void addToDatabase(String a, String b, String c, String d, String e, String f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(col_2, a);
        contentvalues.put(col_3, b);
        contentvalues.put(col_4, c);
        contentvalues.put(col_5, d);
        contentvalues.put(col_6, e);
        contentvalues.put(col_7, f);
        db.insert(table_doctor, null, contentvalues);

    }

    /*public boolean insertData(String medicine, String phone, String time ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(col_2, medicine);
        contentvalues.put(col_3, phone);
        contentvalues.put(col_4, time);
        long result = db.insert(table_name, null, contentvalues);
        if (result == -1)
            return false;
        else
            return true;

    }*/

    public Cursor getList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + table_doctor, null);
        return data;
    }
    public Cursor getListforall(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + table_doctor, null);
        return data;
    }

    public Cursor getID(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + col_1 + " FROM " + table_doctor + " WHERE " + col_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTime(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + col_4 + " FROM " + table_doctor + " WHERE " + col_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getPhone(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + col_3 + " FROM " + table_doctor + " WHERE " + col_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /*public  void updateName(String n, int i, String o){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + table_name + " SET " + col_2 + " = '" + n + "'WHERE " + col_1 + " = '" + i + "'" + " AND " + col_2 + " = '"
                + o + "'";
        db.execSQL(query);
    }
    public  void deleteName(int i, String o){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + table_name + " WHERE " + col_1 + " = '" + i + "'" + " AND " + col_2 + " = '"
                + o + "'";
        db.execSQL(query);
    }*/

}

