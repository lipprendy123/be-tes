package com.example.gudangapp.utils.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponse implements StandardResponseWrapper {
    private String message;
    private int status = 200;

    public DefaultResponse(String message) {
        this.message = message;
    }

    public DefaultResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
