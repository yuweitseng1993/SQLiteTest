package com.example.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TodoDatabase extends SQLiteOpenHelper {


    public TodoDatabase(@Nullable Context context) {
        super(context, DatabaseUtil.databaseName, null, DatabaseUtil.databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseUtil.TaskTable.tableName + " (" + DatabaseUtil.TaskTable._ID + " INTEGER PRIMARY KEY," + DatabaseUtil.TaskTable.taskColumn + " VARCHAR(255)," + DatabaseUtil.TaskTable.categoryColumn + " VARCHAR(255))" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
