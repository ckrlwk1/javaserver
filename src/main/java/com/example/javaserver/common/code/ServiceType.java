package com.example.javaserver.common.code;


public enum ServiceType {
    COMMON("common")
    , AUTH("auth")
    , API("api");

    public String service;

    ServiceType(String service ) {
        this.service = service;
    }
}
