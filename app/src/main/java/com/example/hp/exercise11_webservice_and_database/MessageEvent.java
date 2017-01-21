package com.example.hp.exercise11_webservice_and_database;

/**
 * Created by hp on 01/13/2017.
 */

public class MessageEvent {
    public final String message;
    public final int messageCode;
    public int Count;

    public MessageEvent(String message  , int code,int count) {
        Count = count;
        this.message = message;
        messageCode = code;
    }

}
