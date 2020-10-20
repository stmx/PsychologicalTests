package com.stmlab.android.pstests.di;

import com.stmlab.android.pstests.helpers.Navigation;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {
    private final Navigation mNavigation;

    public NavigationModule(Navigation navigation) {
        mNavigation = navigation;
    }

    @Provides
    public Navigation provideNavigation() {
        return mNavigation;
    }
}
