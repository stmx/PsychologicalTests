package com.stmlab.android.pstests.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.stmlab.android.pstests.models.AnswerModel;

import java.util.ArrayList;

public interface AnswerView extends MvpView {

    void setupAnswers(ArrayList<AnswerModel> answerList);

    void showCurrentNumberQuestion(int currentNumberQuestion);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showNextQuestion(long testId, int numberQuestion);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showResult();
}
