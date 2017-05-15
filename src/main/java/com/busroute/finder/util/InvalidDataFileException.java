package com.busroute.finder.util;

/**
 * Created by linaik on 2017/05/15.
 */
public class InvalidDataFileException extends Exception{

    public InvalidDataFileException(String message) {
        super(message);
    }

    public InvalidDataFileException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
