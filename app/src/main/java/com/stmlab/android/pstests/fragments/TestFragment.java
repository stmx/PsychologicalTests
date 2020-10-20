package com.stmlab.android.pstests.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.activities.MainActivity;
import com.stmlab.android.pstests.adapters.TestsAdapter;
import com.stmlab.android.pstests.helpers.Navigation;
import com.stmlab.android.pstests.models.TestModel;
import com.stmlab.android.pstests.presenters.TestPresenter;
import com.stmlab.android.pstests.views.TestView;

import java.util.ArrayList;

import javax.inject.Inject;

public class TestFragment extends MvpAppCompatFragment implements TestView {

    @InjectPresenter
    TestPresenter mPresenter;

    @Inject
    Navigation mNavigation;

    RecyclerView mRecyclerView;
    TestsAdapter mTestsAdapter;
    EditText mEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);
        findViewsInView(view);
        MainActivity.getNavigationComponent().inject(this);
        mTestsAdapter = new TestsAdapter();
        mRecyclerView.setAdapter(mTestsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mPresenter.loadTests();
        mTestsAdapter.setOnItemClickListener((model) -> {
            mPresenter.onItemTestClick(model);
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTestsAdapter.filterList(mEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    public void findViewsInView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerViewTests);
        mEditText = view.findViewById(R.id.search_edit);
    }

    @Override
    public void setupTestsList(ArrayList<TestModel> testList) {
        mTestsAdapter.setupTestsList(testList);
    }

    @Override
    public void showFragment(TestDescriptionFragment fragment) {
        mNavigation.navigateTo(fragment);
//        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("fragments").commit();
//        Log.d("Moxy", "TestFragment.showFragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.viewDetached();
    }
}
