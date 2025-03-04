package com.example.javaserver.common.exception;


import com.example.javaserver.common.code.CodeName;
import com.example.javaserver.common.code.ResponseCode;
import com.example.javaserver.common.code.ServiceType;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(new ResponseCode(ServiceType.API, CodeName.ERR_INVALID_USER), HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(SignatureException ex) {
        return new ResponseEntity<>(new ResponseCode(ServiceType.API, CodeName.ERR_INVALID_USER), HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(ApiException ex) {
        return new ResponseEntity<>(ex.getError(), HttpStatus.UNAUTHORIZED);
    }

}
