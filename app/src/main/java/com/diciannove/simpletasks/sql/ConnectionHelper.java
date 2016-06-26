package com.diciannove.simpletasks.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diciannove.simpletasks.SimpleTask;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by agarcia on 6/25/2016.
 */
public class ConnectionHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cupboardTest.db";
    private static final int DATABASE_VERSION = 2;

    public ConnectionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        cupboard().register(SimpleTask.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this will ensure that all tables are created
        cupboard().withDatabase(db).createTables();
        // add indexes and other database tweaks in this method if you want

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this will upgrade tables, adding columns and new tables.
        // Note that existing columns will not be converted
        cupboard().withDatabase(db).upgradeTables();
        // do migration work if you have an alteration to make to your schema here

        if (newVersion == 2) {
            ContentValues cv = new ContentValues();
            cv.put("priority", 1);
            cupboard().withDatabase(db).update(SimpleTask.class, cv);
        }
    }

}