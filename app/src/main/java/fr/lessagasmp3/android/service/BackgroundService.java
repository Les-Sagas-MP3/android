package fr.lessagasmp3.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.firebase.FirebaseService;

public class BackgroundService extends BroadcastReceiver {

    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("com.google.android.c2dm.intent.RECEIVE")) {
            Log.d(TAG, "I'm in!!!");

            for (String key : intent.getExtras().keySet()) {
                Object value = intent.getExtras().get(key);
                Log.e("FirebaseDataReceiver", "Key: " + key + " Value: " + value);
            }

            Intent serviceIntent = new Intent(context, FirebaseService.class);
            context.startService(serviceIntent);

            Intent i = new Intent(context, PingService.class);
            i.putExtra("core_url", context.getString(R.string.core_url));
            context.startService(i);
        }
    }
}