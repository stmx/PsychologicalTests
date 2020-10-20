package com.stmlab.android.pstests.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.stmlab.android.pstests.fragments.AnswerFragment;
import com.stmlab.android.pstests.fragments.QuestionFragment;
import com.stmlab.android.pstests.views.QuestionAnswerView;

@InjectViewState
public class QuestionAnswerPresenter extends MvpPresenter<QuestionAnswerView> {
    QuestionFragment questionFragment;
    AnswerFragment answerFragment;

    public QuestionAnswerPresenter() {
        Log.d("Moxy", "QuestionAnswerPresenter.constructor");
    }

    public void setTestId(long testId) {
        questionFragment = QuestionFragment.newInstance(testId,0);
        answerFragment = AnswerFragment.newInstance(testId);
        Log.d("Moxy", "QuestionAnswerPresenter.constructor");
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().showFragments(questionFragment,answerFragment);
        Log.d("Moxy", "QuestionAnswerPresenter.onFirstViewAttach");
    }
}
