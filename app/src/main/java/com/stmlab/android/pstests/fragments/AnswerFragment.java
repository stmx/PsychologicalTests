package com.stmlab.android.pstests.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.activities.MainActivity;
import com.stmlab.android.pstests.adapters.AnswerAdapter;
import com.stmlab.android.pstests.helpers.Navigation;
import com.stmlab.android.pstests.models.AnswerModel;
import com.stmlab.android.pstests.presenters.AnswerPresenter;
import com.stmlab.android.pstests.views.AnswerView;

import java.util.ArrayList;

import javax.inject.Inject;

public class AnswerFragment extends MvpAppCompatFragment  implements AnswerView {
    public static final String TEST_ID_ARG_TAG = "com.stmlab.android.pstests.fragments.AnswerFragment.TestId";
    @Inject
    Navigation mNavigation;

    @InjectPresenter
    AnswerPresenter mAnswerPresenter;

    RecyclerView mRecyclerView;
    AnswerAdapter mAnswerAdapter;
    TextView mTextViewCurrentQuestion;

    public static AnswerFragment newInstance(long testId) {
        Bundle args = new Bundle();
        args.putLong(TEST_ID_ARG_TAG, testId);
        AnswerFragment fragment = new AnswerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer, container, false);
        MainActivity.getNavigationComponent().inject(this);
        mRecyclerView = view.findViewById(R.id.recyclerViewAnswer);
        mTextViewCurrentQuestion = view.findViewById(R.id.textViewCurrentNumberQuestion);
        mAnswerAdapter = new AnswerAdapter();
        mAnswerAdapter.setOnItemClickListener((AnswerModel answer)->{
            mRecyclerView.smoothScrollToPosition(0);
            mAnswerPresenter.setAnswer(answer);
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long testId = getArguments().getLong(TEST_ID_ARG_TAG);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mAnswerAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mAnswerPresenter.loadAnswer(testId);
        Log.d("UserAnswerMapModel", "AnswerFragment.isInRestoreState "+mAnswerPresenter.isInRestoreState(this));
    }

    @Override
    public void setupAnswers(ArrayList<AnswerModel> answerList) {
        mAnswerAdapter.setupAnswer(answerList);
    }

    @Override
    public void showCurrentNumberQuestion(int currentNumberQuestion) {
        mTextViewCurrentQuestion.setText(String.valueOf(currentNumberQuestion));
    }

    @Override
    public void showNextQuestion(long testId, int numberQuestion) {
        mNavigation.nextQuestion(testId, numberQuestion);
    }

    @Override
    public void showResult() {
        mNavigation.navigateTo(ResultFragment.newInstance());
    }


}
