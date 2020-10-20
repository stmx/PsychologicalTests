package com.stmlab.android.pstests.helpers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.activities.MainActivity;
import com.stmlab.android.pstests.fragments.QuestionFragment;

public class Navigation {
    Activity mActivity;
    FragmentManager mFragmentManager;

    public Navigation(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = activity.getSupportFragmentManager();
    }

    public void navigateTo(Fragment fragment) {
        mFragmentManager.
                beginTransaction().
                replace(R.id.fragment_container, fragment).
                addToBackStack("fragments").
                commit();
    }
    public void navigateTo(Fragment fragment, View transitionView, String transitionName) {
        mFragmentManager.
                beginTransaction().
                addSharedElement(transitionView, transitionName).
                replace(R.id.fragment_container, fragment).
                addToBackStack("fragments").
                commit();
    }
    public void navigateTo(Fragment fragment, int containerId) {
        mFragmentManager.
                beginTransaction().
                replace(containerId, fragment).
                commit();
    }


    public void nextQuestion(long testId, int numberQuestion) {
        Fragment questionFragment = QuestionFragment.newInstance(testId, numberQuestion);
        mFragmentManager.
                beginTransaction().
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                replace(R.id.question_container, questionFragment).
                addToBackStack("questions").
                commit();
    }
}
