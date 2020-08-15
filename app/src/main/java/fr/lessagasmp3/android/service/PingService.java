package fr.lessagasmp3.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import fr.lessagasmp3.android.task.PingTask;

public class PingService extends Service {

    public Handler handler = null;
    public static Runnable runnable = null;
    private PingTask mPingTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        runnable = () -> {
            mPingTask = new PingTask(intent);
            mPingTask.execute();
            handler.postDelayed(runnable, 1500000);
        };
        handler.postDelayed(runnable, 1000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPingTask.cancel(true);
    }

}