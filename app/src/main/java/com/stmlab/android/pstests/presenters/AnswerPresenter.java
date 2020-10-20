package com.stmlab.android.pstests.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.stmlab.android.pstests.interfaces.Presenter;
import com.stmlab.android.pstests.models.AnswerModel;
import com.stmlab.android.pstests.models.UserAnswerMapModel;
import com.stmlab.android.pstests.provider.TestProvider;
import com.stmlab.android.pstests.views.AnswerView;

import java.util.ArrayList;
import java.util.HashMap;

@InjectViewState
public class AnswerPresenter extends MvpPresenter<AnswerView> implements Presenter {
    private HashMap<Integer, Integer> mAnswerMap;
    private long mTestId;
    private int mCurrentQuestion;
    private int mQuestionListSize = Integer.MAX_VALUE;
    private UserAnswerMapModel mModel = new UserAnswerMapModel();

    public AnswerPresenter() {
        mAnswerMap = new HashMap<>();
        mCurrentQuestion = 0;
        mTestId = 0;
        Log.d("Moxy", "AnswerPresenter.constructor");
        getViewState().showCurrentNumberQuestion(mCurrentQuestion);
    }
    public void setupAnswers(ArrayList<AnswerModel> answerList) {
        getViewState().setupAnswers(answerList);
    }

    public void loadAnswer(long testId) {
        mTestId = testId;
        new TestProvider(this).loadAnswer(testId);
    }

    public void setAnswer(AnswerModel answer) {
        if ( mQuestionListSize == Integer.MAX_VALUE ) {
            mQuestionListSize = new TestProvider(this).loadQuestionListSize(mTestId);
        }
        mAnswerMap.put(mCurrentQuestion, answer.getValue());
        mModel.addAnswer(mCurrentQuestion,answer.getValue());
        ++mCurrentQuestion;
        getViewState().showCurrentNumberQuestion(mCurrentQuestion);
        if ( mCurrentQuestion >= mQuestionListSize ) {
            getViewState().showResult();
        } else {
            getViewState().showNextQuestion(mTestId,mCurrentQuestion);
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }
}
