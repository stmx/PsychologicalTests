package com.stmlab.android.pstests.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.stmlab.android.pstests.models.TestModel;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;


@Dao
public interface TestDao {
    @Query("SELECT * FROM tests")
    Flowable<List<TestModel>> getAllTests();

    @Query("SELECT * FROM tests WHERE test_id = :id")
    TestModel getTestById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTest(TestModel testModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTest(List<TestModel> testsList);

    @Delete
    void deleteTest(TestModel testModel);

    @Delete
    void deleteTest(List<TestModel> testsList);
}
