package com.national.security.community.event;

/**
 * @ description:  事件总线
 * @ author:  ljn
 * @ time:  2018/1/2
 */
public class MessageEvent {

    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
