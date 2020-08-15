package fr.lessagasmp3.android.task;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import fr.lessagasmp3.android.entity.RssMessage;
import fr.lessagasmp3.android.persistence.repository.RssMessageRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetRssMessages extends AsyncTask<String, Void, String> {

    public static final String TAG = "GetRssMessages";

    private RssMessageRepository mRepository;
    private String url = "";
    private OkHttpClient client = new OkHttpClient();

    public GetRssMessages(String url, Application application) {
        this.url = url;
        mRepository = new RssMessageRepository(application);
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
                Gson gson = new Gson();
                RssMessage[] entries = gson.fromJson(stringJson, RssMessage[].class);
                Log.i(TAG, "Sync successfull : " + entries.length + " rss messages downloaded");
                mRepository.deleteAll();
                for (RssMessage entry : entries) {
                    mRepository.insert(entry);
                }
            }
        } catch(IOException e) {
            if(e.getMessage() != null)
                Log.e(TAG, e.getMessage());
            return null;
        }
        return stringJson;
    }


}
