package fr.lessagasmp3.android.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.io.IOException;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.entity.Saga;
import fr.lessagasmp3.android.persistence.repository.SagaRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetSagas extends AsyncTask<String, Void, String> {

    public static final String TAG = "GetSagas";

    private Activity activity;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SagaRepository mRepository;
    private String url = "";
    private OkHttpClient client = new OkHttpClient();
    private int nbSagasDownloaded = 0;

    public GetSagas(Activity activity, SwipeRefreshLayout mSwipeRefreshLayout) {
        this.activity = activity;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
        this.url = activity.getString(R.string.core_url) + "/api/sagas";
        mRepository = new SagaRepository(activity.getApplication());
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
                Saga[] sagas = gson.fromJson(stringJson, Saga[].class);
                mRepository.deleteAll();
                for (Saga saga : sagas) {
                    mRepository.insert(saga);
                }
                nbSagasDownloaded = sagas.length;
                Log.i(TAG, "Sync successfull : " + nbSagasDownloaded + " sagas downloaded");
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

