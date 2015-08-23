package com.httpmerge.execption;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by allovince on 15/8/17.
 */
public class RestBaseException extends ReflectiveOperationException implements RestException {

    private int statusCode = -1;
    private Response apiResponse;
    private int apiErrorCode;
    private String apiErrorMessage;
    private String apiReadableErrorMessage;

    @Override
    public boolean hasResponse() {
        return true;
    }

    @Override
    public void setStatusCode(int code) {
        statusCode = code;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public void setResponse(Response response) {
        apiResponse = response;
    }

    @Override
    public Response getResponse() {
        return apiResponse;
    }

    @Override
    public void setApiErrorCode(int errorCode) {
        apiErrorCode = errorCode;
    }

    @Override
    public int getApiErrorCode() {
        return apiErrorCode;
    }

    @Override
    public void setApiErrorMessage(String errorMessage) {
        apiErrorMessage = errorMessage;
    }

    @Override
    public String getApiErrorMessage() {
        return apiErrorMessage;
    }

    @Override
    public void setApiReadableErrorMessage(String errorMessage) {
        apiReadableErrorMessage = errorMessage;
    }

    @Override
    public String getApiReadableErrorMessage() {
        return apiReadableErrorMessage;
    }

    /*
    public ClientInputException() {
        super();
    }

    public ClientInputException(String message) {
        super(message);
    }

    public ClientInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientInputException(Throwable cause) {
        super(cause);
    }
    */

    public RestBaseException(String message, Response response) {
        super(message);
        apiResponse = response;
        statusCode = response.code();
        Gson gson = new Gson();

        try {
            String body = response.body().string();
            com.httpmerge.execption.RestErrorMessage errorMessage = gson.fromJson(body, com.httpmerge.execption.RestErrorMessage.class);
            com.httpmerge.execption.RestErrorMessage.ErrorsEntity error = errorMessage.getErrors().get(0);
            apiErrorMessage = error.getMessage();
            apiReadableErrorMessage = error.getMessageHuman();
        } catch (JsonSyntaxException e) {

        } catch (IOException e) {

        }

    }
}
