package com.example.hp.exercise11_webservice_and_database;

/**
 * Created by hp on 01/13/2017.
 */

public class MessageEvent {
    public final String message;
    public final int messageCode;

    public MessageEvent(String message  , int code) {
        this.message = message;
        messageCode = code;
    }

}
