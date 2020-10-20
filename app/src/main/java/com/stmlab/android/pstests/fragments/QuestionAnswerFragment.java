package com.stmlab.android.pstests.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.activities.MainActivity;
import com.stmlab.android.pstests.helpers.Navigation;
import com.stmlab.android.pstests.presenters.QuestionAnswerPresenter;
import com.stmlab.android.pstests.views.QuestionAnswerView;

import javax.inject.Inject;

public class QuestionAnswerFragment extends MvpAppCompatFragment implements QuestionAnswerView {

    private static final String TEST_ID_ARG_TAG = "com.stmlab.android.pstests.fragments.QuestionAnswerFragment.TestId";
    @Inject
    Navigation mNavigation;

    @InjectPresenter
    QuestionAnswerPresenter mQuestionAnswerPresenter;

    public static QuestionAnswerFragment newInstance(long testId) {
        Bundle args = new Bundle();
        args.putLong(TEST_ID_ARG_TAG, testId);
        QuestionAnswerFragment fragment = new QuestionAnswerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_answer, container, false);
        long testId = getArguments().getLong(TEST_ID_ARG_TAG);
        mQuestionAnswerPresenter.setTestId(testId);
        MainActivity.getNavigationComponent().inject(this);
        return view;
    }

    @Override
    public void showFragments(QuestionFragment questionFragment, AnswerFragment answerFragment) {
        mNavigation.navigateTo(questionFragment,R.id.question_container);
        mNavigation.navigateTo(answerFragment, R.id.answer_container);
    }
}
