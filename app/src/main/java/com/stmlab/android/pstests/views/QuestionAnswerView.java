package com.stmlab.android.pstests.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.stmlab.android.pstests.fragments.AnswerFragment;
import com.stmlab.android.pstests.fragments.QuestionFragment;
import com.stmlab.android.pstests.fragments.TestFragment;

public interface QuestionAnswerView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showFragments(QuestionFragment answerFragment, AnswerFragment questionFragment);
}
