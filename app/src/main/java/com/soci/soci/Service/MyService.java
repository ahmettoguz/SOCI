package com.soci.soci.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyService extends JobIntentService {
    /**
     * Unique job ID for this service.
     */
    static final int JOB_ID = 1000;
    @SuppressWarnings("deprecation")
    final Handler mHandler = new Handler();


    /*
    enqueueWork method allows us to easily schedule new work. Because every work schedulded for a specific class needs the same unique job id, we pass in our constant JOB_ID.
     */
    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // operations
        String[] messages = {"Welcome! ", "Hello there! ", "Glad to have you! ", "We're delighted to see you! "};

        //int randomInt = (int)Math.floor(Math.random() * (max + 1 – min) + min);
        int randomInt = (int) Math.floor(Math.random() * (3 + 1 - 0) + 0);


        // prepare return data
        Intent broadcastIntent = new Intent();
        broadcastIntent.putExtra("message", messages[randomInt]);

        // set unique broadcast name
        getBaseContext().sendBroadcast(broadcastIntent);
        broadcastIntent.setAction("RANDOM_MESSAGE_GENERATED");

        // return service data with broadcast
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

