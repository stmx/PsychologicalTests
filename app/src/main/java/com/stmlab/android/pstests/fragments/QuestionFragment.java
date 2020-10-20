package com.stmlab.android.pstests.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.presenters.QuestionPresenter;
import com.stmlab.android.pstests.views.QuestionView;

public class QuestionFragment extends MvpAppCompatFragment implements QuestionView {
    public static final String TEST_ID_ARG_TAG = "com.stmlab.android.pstests.fragments.QuestionFragment.TestId";
    public static final String NUMBER_QUESTION_ARG_TAG = "com.stmlab.android.pstests.fragments.QuestionFragment.NumberQuestion";
    TextView mTextView;

    @InjectPresenter
    QuestionPresenter mQuestionPresenter;

    public static QuestionFragment newInstance(long testId,int numberQuestion) {
        Bundle args = new Bundle();
        args.putLong(TEST_ID_ARG_TAG, testId);
        args.putInt(NUMBER_QUESTION_ARG_TAG,numberQuestion);
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        long testId = getArguments().getLong(TEST_ID_ARG_TAG);
        int numberQuestion = getArguments().getInt(NUMBER_QUESTION_ARG_TAG);
        mTextView = view.findViewById(R.id.textViewQuestion);
        mQuestionPresenter.loadQuestion(testId, numberQuestion);
        return view;
    }

    @Override
    public void setQuestion(String text) {
        mTextView.setText(text);
    }
}
