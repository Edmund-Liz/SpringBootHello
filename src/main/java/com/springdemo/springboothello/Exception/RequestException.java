package com.springdemo.springboothello.Exception;

import utils.AppLogger;

public class RequestException extends RuntimeException {

    private final String funName;
    private final String msg;

    public RequestException(String funcName, String message) {
        super(funcName+":"+message);
        this.funName = funcName;
        this.msg = message;
        AppLogger.getLogger().info(funcName+":"+message);
    }

    public String getFunName() {
        return funName;
    }

    public String getMsg() {
        return msg;
    }
}

