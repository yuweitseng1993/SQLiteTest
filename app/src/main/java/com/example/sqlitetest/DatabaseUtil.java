package com.example.sqlitetest;

import android.provider.BaseColumns;

public class DatabaseUtil {
    public static final String databaseName = "tododb";
    public static final int databaseVersion = 1;

    public class TaskTable implements BaseColumns {
        public static final String tableName = "task";
        public static final String taskColumn = "name";
        public static final String categoryColumn = "category";

    }
}
