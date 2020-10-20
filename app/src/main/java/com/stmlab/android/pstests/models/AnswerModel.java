package com.stmlab.android.pstests.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "answers")
public class AnswerModel implements Parcelable {
    @ColumnInfo(name = "answer_id")
    @PrimaryKey
    @NonNull
    private long mAnswerId;
    @ColumnInfo(name = "test_owner_id")
    private long mTestOwnerId;
    @ColumnInfo(name = "answer_value")
    private int mValue;
    @ColumnInfo(name = "answer_text")
    private String mText;

    public AnswerModel(long answerId, long testOwnerId, int value, String text) {
        mAnswerId = answerId;
        mTestOwnerId = testOwnerId;
        mValue = value;
        mText = text;
    }

    protected AnswerModel(Parcel in) {
        mAnswerId = in.readLong();
        mTestOwnerId = in.readLong();
        mValue = in.readInt();
        mText = in.readString();
    }

    public static final Creator<AnswerModel> CREATOR = new Creator<AnswerModel>() {
        @Override
        public AnswerModel createFromParcel(Parcel in) {
            return new AnswerModel(in);
        }

        @Override
        public AnswerModel[] newArray(int size) {
            return new AnswerModel[size];
        }
    };

    public long getAnswerId() {
        return mAnswerId;
    }

    public void setAnswerId(long answerId) {
        mAnswerId = answerId;
    }

    public long getTestOwnerId() {
        return mTestOwnerId;
    }

    public void setTestOwnerId(long testOwnerId) {
        mTestOwnerId = testOwnerId;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mAnswerId);
        dest.writeLong(mTestOwnerId);
        dest.writeInt(mValue);
        dest.writeString(mText);
    }
}
