package com.example.javaserver.common.user.service;


import com.example.javaserver.common.code.CodeName;
import com.example.javaserver.common.code.ResponseCode;
import com.example.javaserver.common.code.ServiceType;
import com.example.javaserver.common.exception.ApiException;
import com.example.javaserver.common.jpa.entity.UserEntity;
import com.example.javaserver.common.jpa.repository.UserRepository;
import com.example.javaserver.common.user.vo.UserVo;
import com.example.javaserver.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userInfoRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public List<UserEntity> getAllUsers() {
        return userInfoRepository.findAll();
    }

    public ResponseCode login(UserVo userVo) {
        try {
            // 사용자 인증 시도`
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userVo.getUsername(),
                            userVo.getPassword()
                    )
            );

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            String role = null;
            for (GrantedAuthority authority : authorities) {
                role = authority.getAuthority();
            }
            // 인증 성공 시 JWT 토큰 생성
            String token = jwtTokenUtil.generateToken(userVo.getUsername(), role);
            return new ResponseCode(ServiceType.API, CodeName.SUCCESS, token, CodeName.SUCCESS.message);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new ApiException(new ResponseCode(ServiceType.AUTH, CodeName.ERR_INVALID_USER));
        }
    }

}
