package com.stmlab.android.pstests;

import android.app.Application;

import com.stmlab.android.pstests.di.AppComponent;
import com.stmlab.android.pstests.di.AppModule;
import com.stmlab.android.pstests.di.DaggerAppComponent;

public class MyApplication extends Application {
    private static AppComponent sAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

}
