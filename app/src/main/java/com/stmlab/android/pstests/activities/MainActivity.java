package com.stmlab.android.pstests.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.di.DaggerNavigationComponent;
import com.stmlab.android.pstests.di.NavigationComponent;
import com.stmlab.android.pstests.di.NavigationModule;
import com.stmlab.android.pstests.fragments.TestFragment;
import com.stmlab.android.pstests.helpers.Navigation;
import com.stmlab.android.pstests.presenters.MainPresenter;
import com.stmlab.android.pstests.views.MainView;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    @InjectPresenter
    MainPresenter mPresenter;

    FragmentManager mFragmentManager;
    private static NavigationComponent sNavigationComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new DownloadJSON2Database().startDownload();
        mFragmentManager =getSupportFragmentManager();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sNavigationComponent = DaggerNavigationComponent.builder().navigationModule(new NavigationModule(new Navigation(MainActivity.this))).build();
    }
    public static NavigationComponent getNavigationComponent() {
        return sNavigationComponent;
    }


    @Override
    public void showFragment(TestFragment fragment) {
//        fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
//        if ( fragment == null ) {
            mFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }
    }

    @Override
    public void onBackPressed() {
//        getSupportFragmentManager().popBackStack();
        super.onBackPressed();
    }
}