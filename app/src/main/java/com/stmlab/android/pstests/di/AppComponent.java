package com.stmlab.android.pstests.di;

import com.stmlab.android.pstests.provider.TestProvider;
import com.stmlab.android.pstests.helpers.DownloadJSON2Database;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(TestProvider testProvider);
    void inject(DownloadJSON2Database downloadJSON2Database);

}
