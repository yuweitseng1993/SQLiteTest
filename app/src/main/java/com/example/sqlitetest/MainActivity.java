package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SP_USER_LANGUAGE = "user_language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(
                "MySharedPreference",
                MODE_PRIVATE
        );
        String language = sharedPreferences.getString(SP_USER_LANGUAGE, "missing");

        SharedPreferences.Editor editor = getSharedPreferences(
                "MySharedPreferences",
                MODE_PRIVATE
        ).edit();
        editor.putString(SP_USER_LANGUAGE, "en");
        editor.commit();
    }

    public void saveEntry(String task, String category){
        TodoDatabase database = new TodoDatabase(this);
        SQLiteDatabase saveData = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseUtil.TaskTable.taskColumn, task);
        values.put(DatabaseUtil.TaskTable.categoryColumn, category);

        if(saveData.insert(DatabaseUtil.TaskTable.tableName, null, values) > 0){
            Toast.makeText(this, "Saved values", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Nothing saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void readTask(){
        TodoDatabase todoDatabase = new TodoDatabase(this);
        SQLiteDatabase readableDB = todoDatabase.getReadableDatabase();
        Cursor cursor = readableDB.query(DatabaseUtil.TaskTable.tableName, null, null, null, null, null, null, null);
        List<Pair<String,String>> dataSet = new ArrayList<>();
        Pair<String,String> item;
        while(cursor.moveToNext()){
            String itemTask = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUtil.TaskTable.taskColumn));
            String itemCategory = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUtil.TaskTable.categoryColumn));
            item = new Pair<>(itemTask, itemCategory);
            dataSet.add(item);
        }
    }

    //todo: create the adapter
    //todo: set the adpater to the recyclerview(viewholder, itemlayout)
    //todo: init the data set from the adapter with this dataset
}
