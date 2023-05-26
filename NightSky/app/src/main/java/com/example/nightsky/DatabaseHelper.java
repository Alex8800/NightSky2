package com.example.nightsky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "NightSky.db";
    public static final String Star_TableName = "Star_Table";
    public static final String sc0_StarName = "Star_Name";
    public static final String sc1_MMin = "Mag_Min";
    public static final String sc2_MMax = "Mag_Max";
    public static final String sc3_isVar = "is_Var";
    public static final String sc4_periodMaxima = "Period_Max";
    public static final String sc5_periodMinima = "Period_Min";
    public static final String sc6_description = "Other_Info";

    public static final String History_TableName = "History_Table";
    public static final String hc0_StarName = "Star_Name";
    public static final String hc1_M = "Mag";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateStarTable = ("CREATE TABLE " + Star_TableName +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                sc0_StarName + " text, " +
                sc1_MMin + " text, " +
                sc2_MMax + " text, " +
                sc3_isVar + " text, " +
                sc4_periodMaxima + " text, " +
                sc5_periodMinima + " text, " +
                sc6_description + " text) ");
        db.execSQL(CreateStarTable);

        String CreateHistoryTable = ("CREATE TABLE " + History_TableName +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                hc0_StarName + " text, " +
                hc1_M + " text) ");
        db.execSQL(CreateHistoryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Star_TableName);
        db.execSQL("DROP TABLE IF EXISTS " + History_TableName);
        onCreate(db);
    }

    public int lastID(String FromTableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + FromTableName, null);
        res.moveToLast();

        String id = res.getString(0);
        return Integer.parseInt(id);
    }

    public StarData getData_StarTable(String starName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Star_TableName, null);
        res.moveToFirst();

        do {
            String name = res.getString(1);
            if (name.equals(starName))
                return formStarFromCursor(res);
        } while (res.moveToNext());

        return formStarFromCursor(res);
    }

    public Cursor getAllData_History()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + History_TableName, null);
        res.moveToFirst();

        return res;
    }

    public Cursor getAllData_StarTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Star_TableName, null);
        res.moveToFirst();

        return res;
    }


    public boolean addStar(StarData star)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(sc0_StarName, star.starName);
        cv.put(sc1_MMin, star.magMin);
        cv.put(sc2_MMax, star.magMax);
        cv.put(sc3_isVar, star.isVar);
        cv.put(sc4_periodMaxima, star.periodMax);
        cv.put(sc5_periodMinima, star.periodMin);
        cv.put(sc6_description, star.description);

        Log.d("DatabaseHelper ", "add " + star.starName);
        long result = db.insert(Star_TableName, null, cv);

        if(result == -1)
            return false;
        else return true;
    }

    public boolean addHistory(String Name, String Magnitude)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(hc0_StarName, Name);
        cv.put(hc1_M, Magnitude);

        Log.d("Add to history ", "add " + Name);
        long result = db.insert(History_TableName, null, cv);

        if(result == -1)
            return false;
        else return true;
    }





    public StarData formStarFromCursor(Cursor cursor)
    {
        String name = cursor.getString(1),
                mag_min = cursor.getString(2),
                mag_max = cursor.getString(3),
                isVar = cursor.getString(4),
                periodMin = cursor.getString(5),
                periodMax = cursor.getString(6),
                descr = cursor.getString(7);
        StarData starData = new StarData(name, mag_min, mag_max, isVar, periodMin, periodMax, descr);
        return starData;

    }
}
