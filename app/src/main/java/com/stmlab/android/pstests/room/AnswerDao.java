package com.stmlab.android.pstests.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stmlab.android.pstests.models.AnswerModel;
import com.stmlab.android.pstests.models.QuestionModel;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface AnswerDao {
    @Query("SELECT * FROM answers")
    Flowable<List<AnswerModel>> getAllAnswer();

    @Query("SELECT * FROM answers WHERE test_owner_id = :id")
    Flowable<List<AnswerModel>> getAnswerByTestId(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnswer(AnswerModel question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnswer(List<AnswerModel> question);
}
