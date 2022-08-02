package com.globits.da.utils;

public class Response<T> {
    private T data;
    private int errorCode;
    private String errorMessage;

    public Response() {
    }

    public Response(T data, Error error) {
        this.data = data;
        this.errorCode = error.getCode();
        this.errorMessage = error.getMessage();
    }
    public Response(T data, String errorMessage) {
        this.data = data;
        this.errorCode = Error.OK.getCode();
        this.errorMessage = errorMessage;
    }

    public Response(T data) {
        this.data = data;
        this.errorCode = Error.OK.getCode();
        this.errorMessage = "";
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
