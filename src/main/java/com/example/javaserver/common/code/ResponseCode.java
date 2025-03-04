package com.example.javaserver.common.code;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseCode {

    private ServiceType service;
    private HttpStatus id;
    private String code;
    private String message;
    private Object data;


    public ResponseCode(ServiceType service, CodeName codeName) {
        super();

        this.id = codeName.id;
        this.code = codeName.code;
        this.message = codeName.message;
        this.service = service;
    }

    public ResponseCode(ServiceType service, CodeName codeName, String message) {
        super();

        this.id = codeName.id;
        this.code = codeName.code;
        this.message = message;
        this.service = service;
    }

    public ResponseCode(ServiceType service, CodeName codeName, Object data) {
        super();

        this.id = codeName.id;
        this.code = codeName.code;
        this.message = codeName.message;
        this.service = service;
        this.data = data;
    }

    public ResponseCode(ServiceType service, CodeName codeName, Object resultData, String message) {
        super();
        this.id = codeName.id;
        this.code = codeName.code;
        this.message = message;
        this.service = service;
        this.data = resultData;
    }
}
