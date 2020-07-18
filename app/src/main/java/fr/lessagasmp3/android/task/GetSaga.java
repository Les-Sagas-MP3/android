package fr.lessagasmp3.android.task;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import fr.lessagasmp3.android.common.DateCommon;
import fr.lessagasmp3.android.contentprovider.SagaProvider;
import fr.lessagasmp3.android.database.SagaTable;
import fr.lessagasmp3.android.model.Saga;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetSaga extends AsyncTask<String, Void, String> {

    private ContentResolver contentResolver;
    private String url = "";
    private Exception exception = null;
    private OkHttpClient client = new OkHttpClient();

    public GetSaga(String url, ContentResolver contentResolver) {
        this.url = url;
        this.contentResolver = contentResolver;
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
        Saga[] sagas = gson.fromJson(stringJson, Saga[].class);
        Logger.i("Sync successfull : %d sagas downloaded", sagas.length);

        for (Saga saga : sagas) {
            ContentValues values = new ContentValues();
            values.put(SagaTable.COLUMN_ID, saga.getId());
            values.put(SagaTable.COLUMN_CREATED_AT, DateCommon.DATE_FORMAT.format(saga.getCreatedAt()));
            values.put(SagaTable.COLUMN_CREATED_BY, saga.getCreatedBy());
            values.put(SagaTable.COLUMN_UPDATED_AT, DateCommon.DATE_FORMAT.format(saga.getUpdatedAt()));
            values.put(SagaTable.COLUMN_UPDATED_BY, saga.getUpdatedBy());
            values.put(SagaTable.COLUMN_TITLE, saga.getTitle());
            values.put(SagaTable.COLUMN_URL, saga.getUrl());
            values.put(SagaTable.COLUMN_URL_WIKI, saga.getUrlWiki());
            values.put(SagaTable.COLUMN_LEVEL_ART, saga.getLevelArt());
            values.put(SagaTable.COLUMN_LEVEL_TECH, saga.getLevelTech());
            values.put(SagaTable.COLUMN_NB_REVIEWS, saga.getNbReviews());
            values.put(SagaTable.COLUMN_URL_REVIEWS, saga.getUrlReviews());
            values.put(SagaTable.COLUMN_NB_BRAVOS, saga.getNbBravos());
            Uri uri = Uri.parse(SagaProvider.CONTENT_URI + "/" + saga.getId());
            this.contentResolver.delete(uri, null, null);
            this.contentResolver.insert(SagaProvider.CONTENT_URI, values);
        }
    }

}

