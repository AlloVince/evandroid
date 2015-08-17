package com.example.execption;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.AnnotatedParameterizedType;
import java.util.RandomAccess;

/**
 * Created by allovince on 15/8/17.
 */
public class ClientInputException extends RestBaseException {
    public ClientInputException(String message, Response response) {
        super(message, response);
    }
}
