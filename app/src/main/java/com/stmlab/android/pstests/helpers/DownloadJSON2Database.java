package com.stmlab.android.pstests.helpers;

import android.content.res.AssetManager;

import com.stmlab.android.pstests.MyApplication;
import com.stmlab.android.pstests.models.AnswerModel;
import com.stmlab.android.pstests.models.QuestionModel;
import com.stmlab.android.pstests.models.TestModel;
import com.stmlab.android.pstests.room.AppDatabaseRoom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

public class DownloadJSON2Database {
    int tests_counter = 0;
    int question_counter = 0;
    int answer_counter = 0 ;

    @Inject
    AppDatabaseRoom databaseRoom;
    @Inject
    AssetManager mAssetManager;

    public DownloadJSON2Database() {
        MyApplication.getAppComponent().inject(this);
    }

    public void startDownload() {
        insertTest2DatabaseFromJSON("tests/test1.json");
        insertTest2DatabaseFromJSON("tests/test2.json");
        insertTest2DatabaseFromJSON("tests/test3.json");
        insertTest2DatabaseFromJSON("tests/test4.json");
        insertTest2DatabaseFromJSON("tests/test5.json");
    }
    void insertTest2DatabaseFromJSON(String fileNAme) {
        TestModel test;
        ArrayList<QuestionModel> questionList = new ArrayList<>();
        ArrayList<AnswerModel> answerList = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(loadJSONFromAsset(fileNAme));
            JSONArray array = object.getJSONArray("question");
            test = new TestModel(++tests_counter,object.optString("title"), object.optString("description"),object.getInt("size"));
            for (int i = 0; i < array.length(); i++) {
                questionList.add(new QuestionModel(++question_counter, array.getString(i), test.getTestId()));
            }
            array = object.getJSONArray("answer");
            JSONObject answerObject;
            for (int i = 0; i < array.length(); i++) {
                answerObject = array.getJSONObject(i);
                answerList.add(new AnswerModel(++answer_counter, test.getTestId(), answerObject.getInt("value"), answerObject.optString("text")));
            }
            databaseRoom.getTestDao().insertTest(test);
            databaseRoom.getQuestionDao().insertQuestion(questionList);
            databaseRoom.getAnswerDao().insertAnswer(answerList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    String loadJSONFromAsset(String fileName) {
        String jsonString = "";
        try {
            InputStream io = mAssetManager.open(fileName);
            int size = io.available();
            byte[] buffer = new byte[size];
            io.read(buffer);
            io.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
