package com.example.android_pc.dailylocalnotification;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class DemoJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        switch (tag) {
            case ShowNotificationJob.TAG:
                return new ShowNotificationJob();
            default:
                return null;
        }
    }
}
