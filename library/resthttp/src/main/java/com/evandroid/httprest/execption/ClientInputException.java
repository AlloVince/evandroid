package com.evandroid.httprest.execption;

import com.squareup.okhttp.Response;

/**
 * Created by allovince on 15/8/17.
 */
public class ClientInputException extends RestBaseException {
    public ClientInputException(String message, Response response) {
        super(message, response);
    }
}
