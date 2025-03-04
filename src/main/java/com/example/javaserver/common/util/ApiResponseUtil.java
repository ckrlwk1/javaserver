package com.example.javaserver.common.util;

import com.example.javaserver.common.code.CodeName;
import com.example.javaserver.common.code.ResponseCode;
import com.example.javaserver.common.code.ServiceType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiResponseUtil {

    public static ResponseEntity<?> getOkResponseEntity(Object obj) throws Exception {
        try {
            return new ResponseEntity<Object>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(new ResponseCode(ServiceType.API, CodeName.ERR_SERVER), HttpStatus.OK);
        }
    }
}
