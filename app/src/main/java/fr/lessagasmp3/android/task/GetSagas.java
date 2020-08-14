package fr.lessagasmp3.android.task;

import android.app.Application;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import fr.lessagasmp3.android.entity.Saga;
import fr.lessagasmp3.android.persistence.repository.SagaRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetSagas extends AsyncTask<String, Void, String> {

    private SagaRepository mRepository;
    private String url = "";
    private Exception exception = null;
    private OkHttpClient client = new OkHttpClient();

    public GetSagas(String url, Application application) {
        this.url = url;
        mRepository = new SagaRepository(application);
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
                Gson gson = new Gson();
                Saga[] sagas = gson.fromJson(stringJson, Saga[].class);
                Logger.i("Sync successfull : %d sagas downloaded", sagas.length);
                mRepository.deleteAll();
                for (Saga saga : sagas) {
                    mRepository.insert(saga);
                }
            }
        } catch(IOException e) {
            this.exception = e;
            if(e.getMessage() != null)
                Logger.e(e.getMessage());
            return null;
        }
        return stringJson;
    }

}

