package com.example.javaserver.common.interceptor;


import com.example.javaserver.common.annotation.CheckRole;
import com.example.javaserver.common.code.CodeName;
import com.example.javaserver.common.code.ResponseCode;
import com.example.javaserver.common.code.ServiceType;
import com.example.javaserver.common.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 컨트롤러 메서드인지 확인
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();


            // 클래스(컨트롤러)에 @CheckRole이 붙어 있는지 확인
            CheckRole classAnnotation = handlerMethod.getBeanType().getAnnotation(CheckRole.class);
            // 메서드에 @CheckRole이 붙어 있는지 확인
            CheckRole methodAnnotation = handlerMethod.getMethodAnnotation(CheckRole.class);

            if (classAnnotation != null || methodAnnotation != null) {
                //method가 먼저임..
                String[] requiredRoles = (methodAnnotation != null) ? methodAnnotation.value() : classAnnotation.value();
                List<String> allowedRoles = Arrays.asList(requiredRoles);

                // 사용자의 권한 가져오기 (예제: JWT 토큰에서 추출한다고 가정)
                String userRole = extractUserRoleFromToken(request);
                System.out.println("userRole : " + userRole);
                // 사용자의 권한이 requiredRoles 목록에 포함되어 있는지 확인
                if (!allowedRoles.contains(userRole)) {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write(objectMapper.writeValueAsString(new ResponseCode(ServiceType.AUTH, CodeName.ERR_AUTH_USER)));
                    return false; // 요청 차단
                }
            }
        }
        return true; // 요청 진행
    }

    // JWT 토큰에서 유저의 역할을 추출하는 예제 (실제 구현 필요)
    private String extractUserRoleFromToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String role = null;
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7); // "Bearer " 제거
            role = (String) jwtTokenUtil.extractUsername(token).get("role");
        }
        return role; // 토큰이 없으면 비회원으로 처리
    }
}
