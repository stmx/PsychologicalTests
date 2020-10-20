package com.stmlab.android.pstests.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "questions")
public class QuestionModel implements Parcelable {
    @ColumnInfo(name = "question_id")
    @PrimaryKey
    @NonNull
    private long questionId;
    @ColumnInfo(name = "question_text")
    private String questionText;
    @ColumnInfo(name = "test_owner_id")
    private long testOwnerId;

    public QuestionModel(@NonNull long questionId, String questionText, long testOwnerId) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.testOwnerId = testOwnerId;
    }

    protected QuestionModel(Parcel in) {
        questionId = in.readLong();
        questionText = in.readString();
        testOwnerId = in.readLong();
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

    @NonNull
    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(@NonNull long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public long getTestOwnerId() {
        return testOwnerId;
    }

    public void setTestOwnerId(long testOwnerId) {
        this.testOwnerId = testOwnerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(questionId);
        dest.writeString(questionText);
        dest.writeLong(testOwnerId);
    }
}
