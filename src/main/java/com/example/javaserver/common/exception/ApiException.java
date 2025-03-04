package com.example.javaserver.common.exception;


import com.example.javaserver.common.code.CodeName;
import com.example.javaserver.common.code.ResponseCode;
import com.example.javaserver.common.code.ServiceType;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -2310235629517721586L;

    ResponseCode error;

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public ApiException() {
        super();
    }

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(ServiceType service, CodeName codename) {
        super(codename.message);
        this.error = new ResponseCode(service, codename);
    }

    public ApiException(ResponseCode businessCode) {
        super();
        this.error = businessCode;
    }

    public ApiException(ServiceType service, CodeName codename, Object object, String errorMessage) {
        super(errorMessage);
        this.error = new ResponseCode(service, codename, object, errorMessage);
    }

    public ApiException(ServiceType service, CodeName codename, String errorMessage) {
        super(errorMessage);
        this.error = new ResponseCode(service, codename, errorMessage);
    }

    public ResponseCode getError() {
        return error;
    }

    public void setError(ResponseCode error) {
        this.error = error;
    }
}