package com.example.javaserver.common.user;

import com.example.javaserver.common.code.CodeName;
import com.example.javaserver.common.code.ResponseCode;
import com.example.javaserver.common.code.ServiceType;
import com.example.javaserver.common.user.service.UserService;
import com.example.javaserver.common.user.vo.UserVo;
import com.example.javaserver.common.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/api/user/login")
    public ResponseEntity<?> loginTest(@RequestBody UserVo userVo) throws Exception {
        // 33
        return ApiResponseUtil.getOkResponseEntity(userService.login(userVo));
    }

    @RequestMapping("/hello")
    public ResponseEntity<?> hello() throws Exception {
        return ApiResponseUtil.getOkResponseEntity(new ResponseCode(ServiceType.API, CodeName.SUCCESS, CodeName.SUCCESS.message));
    }

//    @RequestMapping("/api/auth/login")
//    public ResponseEntity<?> loginTest(@RequestBody TestVo testVo) throws Exception {
//        return ApiResponseUtil.getOkResponseEntity(testService.login(testVo));
//    }
}
