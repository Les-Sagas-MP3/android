package fr.lessagasmp3.android.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.io.IOException;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.entity.RssMessage;
import fr.lessagasmp3.android.persistence.repository.RssMessageRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetRssMessagesTask extends AsyncTask<String, Void, String> {

    public static final String TAG = "GetRssMessages";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RssMessageRepository mRepository;
    private String url = "";
    private OkHttpClient client = new OkHttpClient();

    public GetRssMessagesTask(Activity activity, SwipeRefreshLayout mSwipeRefreshLayout) {
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
        this.url = activity.getString(R.string.core_url) + "/api/rss?feedTitle=Nouveautés";
        mRepository = new RssMessageRepository(activity.getApplication());
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
                mRepository.deleteAll();
                for (RssMessage entry : entries) {
                    mRepository.insert(entry);
                }
                Log.i(TAG, "Sync successfull : " + entries.length + " rss messages downloaded");
            }
        } catch(IOException e) {
            if(e.getMessage() != null)
                Log.e(TAG, e.getMessage());
            return null;
        }
        return stringJson;
    }

    protected void onPostExecute(String stringJson) {
        if(mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

}
