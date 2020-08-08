package fr.lessagasmp3.android.database;

import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;

public class RssMessageTable {

    public static final String TABLE_NAME = "rssmessages";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_UPDATED_BY = "updated_by";
    public static final String COLUMN_FEED_TITLE = "feed_title";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PUBDATE = "pubdate";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_GUID = "guid";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID,
            COLUMN_CREATED_AT,
            COLUMN_CREATED_BY,
            COLUMN_UPDATED_AT,
            COLUMN_UPDATED_BY,
            COLUMN_FEED_TITLE,
            COLUMN_TITLE,
            COLUMN_PUBDATE,
            COLUMN_DESCRIPTION,
            COLUMN_LINK,
            COLUMN_AUTHOR,
            COLUMN_GUID
    };

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CREATED_AT + " text, "
            + COLUMN_CREATED_BY + " text, "
            + COLUMN_UPDATED_AT + " text, "
            + COLUMN_UPDATED_BY + " text, "
            + COLUMN_FEED_TITLE + " text not null, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_PUBDATE + " text, "
            + COLUMN_DESCRIPTION + " text not null, "
            + COLUMN_LINK + " text not null, "
            + COLUMN_AUTHOR + " text not null, "
            + COLUMN_GUID + " text not null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Logger.w(RssMessageTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

}
