package com.stmlab.android.pstests.presenters;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.stmlab.android.pstests.activities.MainActivity;
import com.stmlab.android.pstests.fragments.TestDescriptionFragment;
import com.stmlab.android.pstests.helpers.Navigation;
import com.stmlab.android.pstests.provider.TestProvider;
import com.stmlab.android.pstests.interfaces.Presenter;
import com.stmlab.android.pstests.models.TestModel;
import com.stmlab.android.pstests.views.TestView;

import java.util.ArrayList;

import javax.inject.Inject;

@InjectViewState
public class TestPresenter extends MvpPresenter<TestView> implements Presenter {

    TestProvider mProvider;

    TestDescriptionFragment mDescriptionFragment;

    public TestPresenter() {
        Log.d("Moxy", "TestPresenter.constructor");
        mProvider = new TestProvider(this);
    }

    public void setupTests(ArrayList<TestModel> testList) {
        Log.d("Moxy", "TestPresenter.setupTests");
        getViewState().setupTestsList(testList);
    }
    public void loadTests() {
        Log.d("Moxy", "TestPresenter.loadTests");
        mProvider.loadTests();
    }

    public void onItemTestClick(TestModel model) {
        Log.d("Moxy", "TestPresenter.onItemTestClick");
        mDescriptionFragment = TestDescriptionFragment.newInstance(model.getTestId());
        getViewState().showFragment(mDescriptionFragment);
    }

    public void viewDetached() {
        Log.d("Moxy", "TestPresenter.viewDetached");
        mProvider.detaching();
    }

}
