package com.example.gudangapp.utils.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponse<T> implements StandardResponseWrapper {
    private List<T> data;
    private String message = "OK";
    private int status = 200;

    public ListResponse(List<T> data) {
        this.data = data;
    }

    public ListResponse(List<T> data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
