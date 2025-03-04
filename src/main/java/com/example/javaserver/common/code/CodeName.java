package com.example.javaserver.common.code;

import org.springframework.http.HttpStatus;

public enum CodeName {

    SUCCESS(HttpStatus.OK , "200", "성공")
    ,ERR_PARAM( HttpStatus.BAD_REQUEST , "1000", "파라미터가 에러가 발생하였습니다.")
    ,ERR_INVALID_USER(HttpStatus.FORBIDDEN  , "1001", "사용자를 찾을 수 없습니다.")
    ,ERR_AUTH_USER(HttpStatus.FORBIDDEN  , "1002", "인증되지 않는 사용자입니다.")
    ,ERR_SERVER(HttpStatus.INTERNAL_SERVER_ERROR , "9999", "서버 에러(Server error)");

    public HttpStatus id;
    public String code;
    public String message;

    CodeName(HttpStatus id, String code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }
//    }
//
//    public static enum CodeName{
//        SUCCESS(HttpStatus.OK , "200", "성공")
//        ,ERR_PARAM( HttpStatus.BAD_REQUEST , "1000", "파라미터가 에러가 발생하였습니다.")
//        ,ERR_INVALID_USER(HttpStatus.UNAUTHORIZED  , "1001", "인증받지않은 사용자입니다.")
//        ,ERR_SERVER(HttpStatus.INTERNAL_SERVER_ERROR , "1002", "서버 에러(Server error)");
//
//        public HttpStatus id;
//        public String code;
//        public String message;
//
//        CodeName(HttpStatus id, String code, String message) {
//            this.id = id;
//            this.code = code;
//            this.message = message;
//        }
//    }
//
//    public static enum ServiceError{
//        COMMON("common")
//        , AUTH("auth")
//        , API("api");
//
//
//        public String service;
//
//        ServiceError(String service ) {
//            this.service = service;
//        }
//    }

}
