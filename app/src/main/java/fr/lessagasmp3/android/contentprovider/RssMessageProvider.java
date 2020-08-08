package fr.lessagasmp3.android.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.HashSet;

import fr.lessagasmp3.android.database.DatabaseHelper;
import fr.lessagasmp3.android.database.RssMessageTable;

import static fr.lessagasmp3.android.common.ProviderCommon.RSS_MESSAGE;
import static fr.lessagasmp3.android.common.ProviderCommon.RSS_MESSAGE_ID;

public class RssMessageProvider extends ContentProvider {

    private DatabaseHelper database;

    private static final String AUTHORITY = "fr.lessagasmp3.android.contentprovider.rssmessages";
    private static final String BASE_PATH = RssMessageTable.TABLE_NAME;
    private static final UriMatcher sURIMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, BASE_PATH, RSS_MESSAGE);
        matcher.addURI(AUTHORITY, BASE_PATH + "/#", RSS_MESSAGE_ID);
        return matcher;
    }

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    @Override
    public boolean onCreate() {
        database = new DatabaseHelper(getContext());
        Logger.addLogAdapter(new AndroidLogAdapter());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        checkColumns(projection);
        queryBuilder.setTables(RssMessageTable.TABLE_NAME);
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case RSS_MESSAGE:
                break;
            case RSS_MESSAGE_ID:
                queryBuilder.appendWhere(RssMessageTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                Logger.d("Unknown URI: " + uri);
        }
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case RSS_MESSAGE:
                id = sqlDB.insert(RssMessageTable.TABLE_NAME, null, values);
                break;
            default:
                Logger.d("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case RSS_MESSAGE:
                rowsDeleted = sqlDB.delete(RssMessageTable.TABLE_NAME, selection, selectionArgs);
                break;
            case RSS_MESSAGE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(RssMessageTable.TABLE_NAME,
                            RssMessageTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(RssMessageTable.TABLE_NAME,
                            RssMessageTable.COLUMN_ID + "=" + id + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                Logger.d("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case RSS_MESSAGE:
                rowsUpdated = sqlDB.update(RssMessageTable.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            case RSS_MESSAGE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(RssMessageTable.TABLE_NAME,
                            values,
                            RssMessageTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(RssMessageTable.TABLE_NAME,
                            values,
                            RssMessageTable.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                Logger.d("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(RssMessageTable.ALL_COLUMNS));
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
}