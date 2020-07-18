package fr.lessagasmp3.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SagaDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sagas.db";
    private static final int DATABASE_VERSION = 1;


    public SagaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        SagaTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        SagaTable.onUpgrade(database, oldVersion, newVersion);
    }
}