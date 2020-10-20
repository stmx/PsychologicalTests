package com.stmlab.android.pstests.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.res.AssetManager;

import com.stmlab.android.pstests.room.AppDatabaseRoom;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    AppDatabaseRoom provideDatabase() {
        return Room.databaseBuilder(mApplication, AppDatabaseRoom.class, "my_db").allowMainThreadQueries().build();
    }

    @Provides
    AssetManager provideAssetManager() {
        return mApplication.getAssets();
    }

}
