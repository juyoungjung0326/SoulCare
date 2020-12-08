package com.example.soulcare;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class VitalSignsDatabase extends SQLiteOpenHelper{
    public static final String COLUMN_ID = "ID";
    public static final String VITAL_SIGNS_TABLE = "VITAL_SIGNS_TABLE";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_TEMPERATURE = "TEMPERATURE";
    public static final String COLUMN_PULSE = "PULSE";
    public static final String COLUMN_BLOOD_PRESSURE = "BLOOD_PRESSURE";
    public static final String COLUMN_GLUCOSE = "GLUCOSE";
    public static final String COLUMN_CHOLESTEROL = "CHOLESTEROl";

    public VitalSignsDatabase(@Nullable Context context)
    {
        super(context, "vitalsigns.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableStatement = "CREATE TABLE " + VITAL_SIGNS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DATE + " TEXT, " + COLUMN_TEMPERATURE + " INTEGER, " + COLUMN_PULSE + " INTEGER, "
                + COLUMN_BLOOD_PRESSURE + " TEXT, " + COLUMN_GLUCOSE + " TEXT, " + COLUMN_CHOLESTEROL + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addForm(com.example.soulcare.VitalSigns vitalSign)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, vitalSign.getDate_field());
        cv.put(COLUMN_TEMPERATURE, vitalSign.getTemperature());
        cv.put(COLUMN_PULSE, vitalSign.getPulse());
        cv.put(COLUMN_BLOOD_PRESSURE, vitalSign.getBloodPressure());
        cv.put(COLUMN_GLUCOSE, vitalSign.getGlucoseLevel());
        cv.put(COLUMN_CHOLESTEROL, vitalSign.getCholesterol());

        long insert = db.insert(VITAL_SIGNS_TABLE,null,cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean deleteRow(com.example.soulcare.VitalSigns vitalSign)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + VITAL_SIGNS_TABLE +
                " WHERE " + COLUMN_ID + " = " + vitalSign.getId();

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

    public List<com.example.soulcare.VitalSigns> getData()
    {
        List<com.example.soulcare.VitalSigns> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + VITAL_SIGNS_TABLE + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            do{
                int formID = cursor.getInt(0);
                String formDate = cursor.getString(1);
                int formTemp = cursor.getInt(2);
                int formPulse = cursor.getInt(3);
                String formPres = cursor.getString(4);
                String formGlucose = cursor.getString(5);
                String formChol = cursor.getString(6);

                com.example.soulcare.VitalSigns newForm = new com.example.soulcare.VitalSigns(formID,formDate,formTemp,formPulse,formPres,formGlucose,formChol);

                returnList.add(newForm);
            }while(cursor.moveToNext());
        }
        else
        {

        }

        cursor.close();
        db.close();
        return returnList;
    }

}
