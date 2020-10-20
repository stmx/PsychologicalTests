package com.stmlab.android.pstests.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.stmlab.android.pstests.models.AnswerModel;
import com.stmlab.android.pstests.models.QuestionModel;
import com.stmlab.android.pstests.models.TestModel;


@Database(entities = {TestModel.class, QuestionModel.class, AnswerModel.class}, version = 1,exportSchema = false)
public abstract class AppDatabaseRoom extends RoomDatabase {
    public abstract TestDao getTestDao();
    public abstract QuestionDao getQuestionDao();
    public abstract AnswerDao getAnswerDao();
}
