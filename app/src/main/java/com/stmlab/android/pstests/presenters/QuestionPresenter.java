package com.stmlab.android.pstests.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.stmlab.android.pstests.interfaces.Presenter;
import com.stmlab.android.pstests.models.QuestionModel;
import com.stmlab.android.pstests.provider.TestProvider;
import com.stmlab.android.pstests.views.QuestionView;

@InjectViewState
public class QuestionPresenter extends MvpPresenter<QuestionView> implements Presenter {

    public void loadQuestion(long testId, int numberQuestion) {
       new TestProvider(this).loadQuestion(testId,numberQuestion);
    }


    public void setupQuestion(QuestionModel question) {
        getViewState().setQuestion(question.getQuestionText());
    }
}
