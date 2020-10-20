package com.stmlab.android.pstests.provider;

import android.util.Log;

import com.stmlab.android.pstests.MyApplication;
import com.stmlab.android.pstests.helpers.TestHelper;
import com.stmlab.android.pstests.interfaces.Presenter;
import com.stmlab.android.pstests.models.AnswerModel;
import com.stmlab.android.pstests.models.QuestionModel;
import com.stmlab.android.pstests.models.TestModel;
import com.stmlab.android.pstests.presenters.AnswerPresenter;
import com.stmlab.android.pstests.presenters.QuestionPresenter;
import com.stmlab.android.pstests.presenters.TestDescriptionPresenter;
import com.stmlab.android.pstests.presenters.TestPresenter;
import com.stmlab.android.pstests.room.AppDatabaseRoom;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class TestProvider {
    private Presenter mPresenter;
    private Disposable testDisposable;
    private Disposable questionDisposable;
    private Disposable answerDisposable;
    @Inject
    AppDatabaseRoom databaseRoom;

    public TestProvider(Presenter presenter) {
        mPresenter = presenter;
        MyApplication.getAppComponent().inject(this);
    }

    public void loadTests() {
        if ( mPresenter instanceof TestPresenter ) {
            if ( TestHelper.INSTANCE.getTestModels() == null || TestHelper.INSTANCE.getTestModels().size() == 0 ) {
//                Log.d("RxJava", "TestHelper.loadTest() download data fromDatabase");
                Flowable<List<TestModel>> sub = databaseRoom.getTestDao().getAllTests();
                downloadTests(sub);
                downloadAnswers(sub);
                downloadQuestions(sub);
            } else {
                ((TestPresenter) mPresenter).setupTests(TestHelper.INSTANCE.getTestModels());
            }
            TestHelper.INSTANCE.getObservable()
                    .subscribe(testModel -> {
                        ((TestPresenter) mPresenter).setupTests(testModel);
                    });
        }
    }

    private void downloadTests(Flowable<List<TestModel>> sub) {
        testDisposable = sub.observeOn(AndroidSchedulers.mainThread()).
                subscribe(testModels -> {
                    TestHelper.INSTANCE.setTestModels((ArrayList<TestModel>) testModels);
                });
    }

    private void downloadAnswers(Flowable<List<TestModel>> sub) {
        answerDisposable = sub.flatMap(Flowable::fromIterable).
                flatMap(test -> databaseRoom.getAnswerDao().getAnswerByTestId(test.getTestId())).
                filter(answerList -> answerList.size() > 0).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(answerList -> {
                    TestHelper.INSTANCE.getTestById(answerList.get(0).getTestOwnerId()).setAnswerList(answerList);
//                            Log.d("RxJava", "Set answer " + answerList.size());
                });
    }

    private void downloadQuestions(Flowable<List<TestModel>> sub) {
        questionDisposable = sub.flatMap(Flowable::fromIterable).
                flatMap(test -> databaseRoom.getQuestionDao().getQuestionByTestId(test.getTestId())).
                filter(answerList -> answerList.size() > 0).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(questionList -> {
                    TestHelper.INSTANCE.getTestById(questionList.get(0).getTestOwnerId()).setQuestionList(questionList);
//                            Log.d("RxJava", "Set question " + questionList.size());
                });
    }

    public void loadTestById(long testId) {
        if ( mPresenter instanceof TestDescriptionPresenter ) {
            TestModel currentTest = TestHelper.INSTANCE.getTestById(testId);
            ((TestDescriptionPresenter) mPresenter).setupAboutTest(currentTest);
        }
    }

    public void loadQuestion(long testId, int questionNumber) {
        if ( mPresenter instanceof QuestionPresenter ) {
            QuestionModel question = TestHelper.INSTANCE.getQuestion(testId, questionNumber);
            ((QuestionPresenter) mPresenter).setupQuestion(question);
        }
    }

    public void loadAnswer(long testId) {
        if ( mPresenter instanceof AnswerPresenter ) {
            ArrayList<AnswerModel> answerList = TestHelper.INSTANCE.getAnswer(testId);
            ((AnswerPresenter) mPresenter).setupAnswers(answerList);
        }
    }

    public int loadQuestionListSize(long testId) {
        if ( mPresenter instanceof AnswerPresenter ) {
            return TestHelper.INSTANCE.getTestById(testId).getQuestionList().size();
        }
        return Integer.MAX_VALUE;
    }

    public void detaching() {
        if ( answerDisposable != null || questionDisposable != null || testDisposable != null ) {
            answerDisposable.dispose();
            questionDisposable.dispose();
            testDisposable.dispose();
            Log.d("RxJava", "Dispose");
        }
    }
}
