package fr.lessagasmp3.android.task;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import fr.lessagasmp3.android.common.DateCommon;
import fr.lessagasmp3.android.contentprovider.RssMessageProvider;
import fr.lessagasmp3.android.database.RssMessageTable;
import fr.lessagasmp3.android.model.RssMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetRssMessages extends AsyncTask<String, Void, String> {

    private ContentResolver contentResolver;
    private String url = "";
    private Exception exception = null;
    private OkHttpClient client = new OkHttpClient();

    public GetRssMessages(String url, ContentResolver contentResolver) {
        this.url = url;
        this.contentResolver = contentResolver;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    protected String doInBackground(String... urls) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response;
        ResponseBody body;
        String stringJson = "";
        try {
            response = client.newCall(request).execute();
            body = response.body();
            if(body != null) {
                stringJson = body.string();
            }
        } catch(IOException e) {
            this.exception = e;
            if(e.getMessage() != null)
                Logger.e(e.getMessage());
            return null;
        }
        return stringJson;
    }

    protected void onPostExecute(String stringJson) {
        if(this.exception != null && this.exception.getMessage() != null) {
            Logger.i(this.exception.getMessage());
        }
        Gson gson = new Gson();
        RssMessage[] entries = gson.fromJson(stringJson, RssMessage[].class);
        Logger.i("Sync successfull : %d rss messages downloaded", entries.length);

        for (RssMessage entry : entries) {
            ContentValues values = new ContentValues();
            values.put(RssMessageTable.COLUMN_ID, entry.getId());
            values.put(RssMessageTable.COLUMN_CREATED_AT, DateCommon.DATE_FORMAT.format(entry.getCreatedAt()));
            values.put(RssMessageTable.COLUMN_CREATED_BY, entry.getCreatedBy());
            values.put(RssMessageTable.COLUMN_UPDATED_AT, DateCommon.DATE_FORMAT.format(entry.getUpdatedAt()));
            values.put(RssMessageTable.COLUMN_UPDATED_BY, entry.getUpdatedBy());
            values.put(RssMessageTable.COLUMN_FEED_TITLE, entry.getTitle());
            values.put(RssMessageTable.COLUMN_TITLE, entry.getTitle());
            values.put(RssMessageTable.COLUMN_PUBDATE, entry.getPubdate());
            values.put(RssMessageTable.COLUMN_DESCRIPTION, entry.getDescription());
            values.put(RssMessageTable.COLUMN_LINK, entry.getLink());
            values.put(RssMessageTable.COLUMN_AUTHOR, entry.getAuthor());
            values.put(RssMessageTable.COLUMN_GUID, entry.getGuid());
            Uri uri = Uri.parse(RssMessageProvider.CONTENT_URI + "/" + entry.getId());
            this.contentResolver.delete(uri, null, null);
            this.contentResolver.insert(RssMessageProvider.CONTENT_URI, values);
        }
    }

}
