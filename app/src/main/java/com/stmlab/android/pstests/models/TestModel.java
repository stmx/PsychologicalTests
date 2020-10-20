package com.stmlab.android.pstests.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(tableName = "tests")
public class TestModel implements Parcelable {

    @Ignore
    private ArrayList<QuestionModel> mQuestionList;

    @Ignore
    ArrayList<AnswerModel> mAnswerList;

    @ColumnInfo(name = "test_title")
    private String mTitle;

    @ColumnInfo(name = "test_description")
    private String mDescription;

    @ColumnInfo(name = "test_size")
    private int mTestSize;

    @ColumnInfo(name = "test_id")
    @PrimaryKey
    @NonNull
    private long mTestId;

    public TestModel(long testId, String title, String description,int testSize) {
        mQuestionList = new ArrayList<>();
        mAnswerList = new ArrayList<>();
        mTitle = title;
        mDescription = description;
        mTestId = testId;
        mTestSize = testSize;
    }

    protected TestModel(Parcel in) {
        mQuestionList = in.createTypedArrayList(QuestionModel.CREATOR);
        mAnswerList = in.createTypedArrayList(AnswerModel.CREATOR);
        mTitle = in.readString();
        mDescription = in.readString();
        mTestSize = in.readInt();
        mTestId = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mQuestionList);
        dest.writeTypedList(mAnswerList);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeInt(mTestSize);
        dest.writeLong(mTestId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TestModel> CREATOR = new Creator<TestModel>() {
        @Override
        public TestModel createFromParcel(Parcel in) {
            return new TestModel(in);
        }

        @Override
        public TestModel[] newArray(int size) {
            return new TestModel[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof TestModel) ) return false;
        TestModel testModel = (TestModel) o;
        return Objects.equals(mTestId, testModel.mTestId)&&Objects.equals(mTitle, testModel.mTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mTitle);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public long getTestId() {
        return mTestId;
    }

    public void setTestId(long testId) {
        mTestId = testId;
    }

    public int getTestSize() {
        return mTestSize;
    }

    public void setTestSize(int testSize) {
        mTestSize = testSize;
    }

    public ArrayList<QuestionModel> getQuestionList() {
        return mQuestionList;
    }

    public void setQuestionList(List<QuestionModel> questionList) {
        mQuestionList = (ArrayList<QuestionModel>) questionList;
    }

    public ArrayList<AnswerModel> getAnswerList() {
        return mAnswerList;
    }

    public void setAnswerList(List<AnswerModel> answerList) {
        mAnswerList = (ArrayList<AnswerModel>) answerList;
    }

    public void addAnswer(AnswerModel answer) {
        mAnswerList.add(answer);
    }
    public void addQuestion(QuestionModel question) {
        mQuestionList.add(question);
    }

}
