package fr.lessagasmp3.android.database;

import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;

public class SagaTable {

    public static final String TABLE_SAGAS = "sagas";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_UPDATED_BY = "updated_by";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_URL_WIKI = "url_wiki";
    public static final String COLUMN_LEVEL_ART = "level_art";
    public static final String COLUMN_LEVEL_TECH = "level_tech";
    public static final String COLUMN_NB_REVIEWS = "nb_reviews";
    public static final String COLUMN_URL_REVIEWS = "url_reviews";
    public static final String COLUMN_NB_BRAVOS = "nb_bravos";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID,
            COLUMN_CREATED_AT,
            COLUMN_CREATED_BY,
            COLUMN_UPDATED_AT,
            COLUMN_UPDATED_BY,
            COLUMN_TITLE,
            COLUMN_URL,
            COLUMN_URL_WIKI,
            COLUMN_LEVEL_ART,
            COLUMN_LEVEL_TECH,
            COLUMN_NB_REVIEWS,
            COLUMN_URL_REVIEWS,
            COLUMN_NB_BRAVOS
    };

    private static final String DATABASE_CREATE = "create table "
            + TABLE_SAGAS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CREATED_AT + " text, "
            + COLUMN_CREATED_BY + " text, "
            + COLUMN_UPDATED_AT + " text, "
            + COLUMN_UPDATED_BY + " text, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_URL + " text, "
            + COLUMN_URL_WIKI + " text not null, "
            + COLUMN_LEVEL_ART + " integer not null, "
            + COLUMN_LEVEL_TECH + " integer not null, "
            + COLUMN_NB_REVIEWS + " integer not null, "
            + COLUMN_URL_REVIEWS + " text not null, "
            + COLUMN_NB_BRAVOS + " integer"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Logger.w(SagaTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_SAGAS);
        onCreate(database);
    }

}
