package com.evandroid.httprest.execption;

import com.squareup.okhttp.Response;

/**
 * Created by allovince on 15/8/18.
 */
public class ServerException extends RestBaseException {
    public ServerException(String message, Response response) {
        super(message, response);
    }
}
