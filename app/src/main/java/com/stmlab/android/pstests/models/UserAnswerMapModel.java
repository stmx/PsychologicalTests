package com.stmlab.android.pstests.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserAnswerMapModel {
    private HashMap<Integer, Integer> mAnswerMap = new HashMap<>();
    private HashMap<Integer, Integer> mResultMap = new HashMap<>();
    ArrayList<Integer> scale1 = new ArrayList<>();
    ArrayList<Integer> scale2 = new ArrayList<>();
    ArrayList<Integer> scale3 = new ArrayList<>();
    int sum1 =0;
    int sum2 =0;
    int sum3 =0;

    public UserAnswerMapModel() {
//        mAnswerMap.clear();
        Log.d("UserAnswerMapModel", "CreateMap");


        scale1.add(1);
        scale1.add(3);
        scale1.add(5);
        scale1.add(7);
        scale1.add(10);
        scale1.add(13);

        scale2.add(2);
        scale2.add(4);
        scale2.add(6);
        scale2.add(8);
        scale2.add(11);
        scale2.add(14);

        scale3.add(9);
        scale3.add(12);

        mResultMap.put(0, 1);
        mResultMap.put(1, 1);
        mResultMap.put(2, 1);
        mResultMap.put(3, 1);
        mResultMap.put(4, 1);
        mResultMap.put(5, 1);
        mResultMap.put(6, 1);
        mResultMap.put(7, 1);
        mResultMap.put(8, 1);
        mResultMap.put(9, 1);
        mResultMap.put(10, 1);
        mResultMap.put(11, 1);
        mResultMap.put(12, 1);
        mResultMap.put(13, 1);
        mResultMap.put(14, 1);
    }

    public void addAnswer(int currentNumberQuestion, int value) {
        mAnswerMap.put(currentNumberQuestion, value);
        if ( scale1.contains(currentNumberQuestion) ) {
            sum1 += sumCounter(currentNumberQuestion);
        } else if ( scale2.contains(currentNumberQuestion) ) {
            sum2 += sumCounter(currentNumberQuestion);
        } else if ( scale3.contains(currentNumberQuestion) ) {
            sum3 += sumCounter(currentNumberQuestion);
        }
        Log.d("UserAnswerMapModel", "Map Value1 " + String.valueOf(sum1));
        Log.d("UserAnswerMapModel", "Map Value2 " + String.valueOf(sum2));
        Log.d("UserAnswerMapModel", "Map Value3 " + String.valueOf(sum3));



//        Log.d("UserAnswerMapModel", "New Map");
//        for (Map.Entry entry : mAnswerMap.entrySet()) {
//            Log.d("UserAnswerMapModel", "Key: " + entry.getKey() + " Value: " + entry.getValue());
//        }
    }

    private int sumCounter(int currentNumberQuestion) {
        if ( mAnswerMap.get(currentNumberQuestion) == mResultMap.get(currentNumberQuestion) ) {
            return  1;
//        }
//        if ( mAnswerMap.get(currentNumberQuestion) == 1 ) {
//            return 1;
        } else {
            return 0;
        }
    }
}
