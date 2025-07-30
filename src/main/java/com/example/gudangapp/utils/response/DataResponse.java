package com.example.gudangapp.utils.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResponse<T> implements StandardResponseWrapper {
    private T data;
    private String message = "OK";
    private int status = 200;

    public DataResponse(T data) {
        this.data = data;
    }

    public DataResponse(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
