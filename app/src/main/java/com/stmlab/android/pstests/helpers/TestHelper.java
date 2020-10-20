package com.stmlab.android.pstests.helpers;

import com.stmlab.android.pstests.models.AnswerModel;
import com.stmlab.android.pstests.models.QuestionModel;
import com.stmlab.android.pstests.models.TestModel;

import java.util.ArrayList;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class TestHelper {
    private ArrayList<TestModel> mTestModels;
    public static final TestHelper INSTANCE = new TestHelper();
    private Subject<ArrayList<TestModel>> mObservable = PublishSubject.create();

    private TestHelper() {
        mTestModels = new ArrayList<>();
    }

    public Subject<ArrayList<TestModel>> getObservable() {
        return mObservable;
    }

    public TestModel getTestById(long testId) {
        for (TestModel testModel : mTestModels) {
            if ( testModel.getTestId() == testId ) {
                return testModel;
            }
        }
        return null;
    }

    public ArrayList<TestModel> getTestModels() {
        if ( mTestModels == null ) {
            return new ArrayList<>();
        }
        return mTestModels;
    }

    public void setTestModels(ArrayList<TestModel> testModels) {
        mTestModels = testModels;
        mObservable.onNext(mTestModels);
    }

    public QuestionModel getQuestion(long testId, int questionNumber) {
        TestModel testModel = getTestById(testId);
        return testModel.getQuestionList().get(questionNumber);
    }

    public ArrayList<AnswerModel> getAnswer(long testId) {
        TestModel testModel = getTestById(testId);
        return testModel.getAnswerList();
    }
}
