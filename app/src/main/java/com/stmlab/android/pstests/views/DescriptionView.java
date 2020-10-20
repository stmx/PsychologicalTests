package com.stmlab.android.pstests.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.stmlab.android.pstests.fragments.QuestionAnswerFragment;

public interface DescriptionView extends MvpView {

    void showTitle(String title);
    void showDescription(String description);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showFragment(QuestionAnswerFragment questionAnswerFragment);

}
