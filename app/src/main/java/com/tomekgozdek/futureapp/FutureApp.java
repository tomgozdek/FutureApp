package com.tomekgozdek.futureapp;

import android.app.Application;

import io.realm.Realm;

/**
 * Application class
 *
 * Created by TomekG on 2017-04-02.
 */

public class FutureApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRepository();
    }

    /**
     * Initialize Realm repository at application start.
     */
    private void initRepository() {
        Realm.init(this);
    }
}
