package fr.lessagasmp3.android.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Date;

import fr.lessagasmp3.android.common.DateCommon;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PingTask extends AsyncTask<String, Void, String> {

    public static final String TAG = "Ping";

    private String url = "";
    private OkHttpClient client = new OkHttpClient();

    public PingTask(Intent intent) {
        this.url = intent.getStringExtra("core_url") + "/api/health";
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
            if (body != null) {
                stringJson = body.string();
                Log.d(TAG, DateCommon.DATE_FORMAT.format(new Date()) + " Ping successfull");
            }
        } catch (IOException e) {
            if (e.getMessage() != null)
                Log.e(TAG, e.getMessage());
            return null;
        }
        return stringJson;
    }

}
