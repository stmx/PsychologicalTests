package com.stmlab.android.pstests.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.stmlab.android.pstests.activities.MainActivity;
import com.stmlab.android.pstests.fragments.QuestionAnswerFragment;
import com.stmlab.android.pstests.helpers.Navigation;
import com.stmlab.android.pstests.provider.TestProvider;
import com.stmlab.android.pstests.interfaces.Presenter;
import com.stmlab.android.pstests.models.TestModel;
import com.stmlab.android.pstests.views.DescriptionView;

import javax.inject.Inject;

@InjectViewState
public class TestDescriptionPresenter extends MvpPresenter<DescriptionView> implements Presenter {

    public TestDescriptionPresenter() {
        Log.d("Moxy", "TestDescriptionPresenter.constructor");
    }

    public void setupAboutTest(TestModel model) {
        getViewState().showTitle(model.getTitle());
        getViewState().showDescription(model.getDescription());
        Log.d("Moxy", "TestDescriptionPresenter.setupAboutTest");
    }

    public void loadTestById(long testId) {
        new TestProvider(this).loadTestById(testId);
        Log.d("Moxy", "TestDescriptionPresenter.loadTestById");
    }

    public void onButtonStartClick(long testId) {
//        QuestionAnswerFragment answerFragment = new QuestionAnswerFragment();
//        mNavigation.navigateTo(answerFragment);
        getViewState().showFragment(QuestionAnswerFragment.newInstance(testId));
        Log.d("Moxy", "TestDescriptionPresenter.onButtonStartClick");
    }
}
