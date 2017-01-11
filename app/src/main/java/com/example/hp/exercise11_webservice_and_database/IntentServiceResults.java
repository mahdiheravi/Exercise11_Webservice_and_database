package com.example.hp.exercise11_webservice_and_database;

/**
 * Created by hp on 01/11/2017.
 */

public class IntentServiceResults {
    int mResult;
    String mResultValue;

    IntentServiceResults(int resultCode, String resultValue) {
        mResult = resultCode;
        mResultValue = resultValue;
    }

    public int getResult() {
        return mResult;
    }

    public String getResultValue() {
        return mResultValue;
    }
}
