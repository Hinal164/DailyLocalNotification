package com.example.android_pc.dailylocalnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.support.PersistableBundleCompat;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ShowNotificationJob extends Job {
     static final String TAG = "show_notification_job_tag";
    private static final String CHANNEL_ID = "channelId";


    @NonNull
    @Override
    protected Result onRunJob(Params params) {


        PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
                new Intent(getContext(), MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Android Job Demo")
                .setContentText("Notification from Android Job Demo App.")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder.setChannelId(CHANNEL_ID);
        }

        NotificationManager nManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NotificationDemo", NotificationManager.IMPORTANCE_DEFAULT);
            nManager.createNotificationChannel(channel);
        }

        nManager.notify(1, mBuilder.build());

        return Result.SUCCESS;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void schedulePeriodic() {
        new JobRequest.Builder(ShowNotificationJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(10))
               // .setPeriodic(TimeUnit.MINUTES.toMillis(1))
                .setUpdateCurrent(true)
                .setPersisted(true)
                .build()
                .schedule();

        /* for (int i = 0; i < 10; i++) {
            JobRequest.Builder builder = new JobRequest.Builder("tag")
                    .setPeriodic(TimeUnit.MINUTES.toMillis(15))
                    .setRequiresCharging(random())
                    .setRequiresDeviceIdle(random())
                    .setRequiredNetworkType(random() ? JobRequest.NetworkType.ANY : JobRequest.NetworkType.CONNECTED)
                    .setRequirementsEnforced(random());

            if (random()) {
                PersistableBundleCompat extras = new PersistableBundleCompat();
                extras.putString("key", "Hello world");
                builder.setExtras(extras);
            }

            builder.build().schedule();
        }*/


    }
    private static boolean random() {
        return Math.random() > 0.5;
    }

}

