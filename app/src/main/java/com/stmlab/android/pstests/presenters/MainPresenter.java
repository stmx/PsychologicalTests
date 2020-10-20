package com.stmlab.android.pstests.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.stmlab.android.pstests.fragments.TestFragment;
import com.stmlab.android.pstests.views.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    TestFragment mFragment;
    public MainPresenter() {
        mFragment = new TestFragment();
        Log.d("Moxy", "MainPresenter.constructor");
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().showFragment(mFragment);
        Log.d("Moxy", "MainPresenter.onFirstViewAttach");
    }
}
