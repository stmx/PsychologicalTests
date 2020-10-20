package com.stmlab.android.pstests.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.stmlab.android.pstests.fragments.TestDescriptionFragment;
import com.stmlab.android.pstests.models.TestModel;

import java.util.ArrayList;

public interface TestView extends MvpView {
    void setupTestsList(ArrayList<TestModel> testList);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showFragment(TestDescriptionFragment fragment);
}
