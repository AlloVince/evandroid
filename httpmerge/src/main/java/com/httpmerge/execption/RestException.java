package com.httpmerge.execption;

import com.squareup.okhttp.Response;

/**
 * Created by allovince on 15/8/17.
 */
public interface RestException {
    public boolean hasResponse();

    public void setStatusCode(int code);

    public int getStatusCode();

    public void setResponse(Response response);

    public Response getResponse();

    public void setApiErrorCode(int errorCode);

    public int getApiErrorCode();

    public void setApiErrorMessage(String errorMessage);

    public String getApiErrorMessage();

    public void setApiReadableErrorMessage(String errorMessage);

    public String getApiReadableErrorMessage();
}
