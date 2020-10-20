package com.stmlab.android.pstests.di;



import com.stmlab.android.pstests.adapters.AnswerAdapter;
import com.stmlab.android.pstests.fragments.AnswerFragment;
import com.stmlab.android.pstests.fragments.QuestionAnswerFragment;
import com.stmlab.android.pstests.fragments.QuestionFragment;
import com.stmlab.android.pstests.fragments.TestDescriptionFragment;
import com.stmlab.android.pstests.fragments.TestFragment;
import com.stmlab.android.pstests.interfaces.Presenter;
import com.stmlab.android.pstests.presenters.TestDescriptionPresenter;
import com.stmlab.android.pstests.presenters.TestPresenter;

import dagger.Component;

@Component(modules = {NavigationModule.class})
public interface NavigationComponent {
    void inject(TestPresenter presenter);
    void inject(TestDescriptionPresenter presenter);

    void inject(QuestionFragment fragment);
    void inject(AnswerFragment fragment);
    void inject(TestFragment fragment);
    void inject(TestDescriptionFragment fragment);
    void inject(QuestionAnswerFragment fragment);

    void inject(AnswerAdapter adapter);
}
