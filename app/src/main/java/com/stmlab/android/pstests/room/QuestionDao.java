package com.stmlab.android.pstests.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stmlab.android.pstests.models.QuestionModel;
import com.stmlab.android.pstests.models.TestModel;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM questions")
    Flowable<List<QuestionModel>> getAllQuestion();

    @Query("SELECT * FROM questions WHERE test_owner_id = :id")
    Flowable<List<QuestionModel>> getQuestionByTestId(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestion(QuestionModel question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestion(List<QuestionModel> question);
}
