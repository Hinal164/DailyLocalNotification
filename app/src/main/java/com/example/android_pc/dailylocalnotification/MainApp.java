package com.example.android_pc.dailylocalnotification;

import android.app.Application;

import com.evernote.android.job.JobManager;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new DemoJobCreator());
       // JobManager.instance().getConfig().setAllowSmallerIntervalsForMarshmallow(true);
    }
}
