package com.stmlab.android.pstests.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.activities.MainActivity;
import com.stmlab.android.pstests.helpers.Navigation;
import com.stmlab.android.pstests.presenters.TestDescriptionPresenter;
import com.stmlab.android.pstests.views.DescriptionView;

import javax.inject.Inject;

public class TestDescriptionFragment extends MvpAppCompatFragment implements DescriptionView{
    public static final String ARG_TAG = "com.stmlab.android.pstests.fragments.TestDescriptionFragment.TestId";

    TextView mTextViewTitle;
    TextView mTextViewDescription;
    Button mButtonStartTest;

    @InjectPresenter
    TestDescriptionPresenter mTestDescriptionPresenter;
    @Inject
    Navigation mNavigation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static TestDescriptionFragment newInstance(long testId) {
        Bundle args = new Bundle();
        args.putLong(TestDescriptionFragment.ARG_TAG, testId);
        TestDescriptionFragment fragment = new TestDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        long testId = getArguments().getLong(ARG_TAG);
        MainActivity.getNavigationComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_test_description, container, false);
        findViewsInView(view);
        mTestDescriptionPresenter.loadTestById(testId);
        mButtonStartTest.setOnClickListener((v) -> {
            mTestDescriptionPresenter.onButtonStartClick(testId);
        });
        mTextViewDescription.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    private void findViewsInView(View view) {
        mTextViewTitle = view.findViewById(R.id.textViewTitleTest);
        mButtonStartTest = view.findViewById(R.id.buttonStartTest);
        mTextViewDescription = view.findViewById(R.id.textViewDescription);
    }

    @Override
    public void showTitle(String title) {
        ViewCompat.setTransitionName(mTextViewTitle,title);//Добавить отдельный класс по присвоению sharedName
        mTextViewTitle.setText(title);
    }

    @Override
    public void showDescription(String description) {
        mTextViewDescription.setText(description);
    }

    @Override
    public void showFragment(QuestionAnswerFragment fragment) {
        mNavigation.navigateTo(fragment);
        Log.d("Moxy", "TestDescriptionFragment.showFragment");
    }
}
