package com.stmlab.android.pstests.views;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.stmlab.android.pstests.fragments.TestFragment;

public interface MainView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showFragment(TestFragment fragment);
}
