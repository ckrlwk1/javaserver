package com.example.javaserver.common.security;

import com.example.javaserver.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

//    @Autowired
//    private  UserInfoRepository userRepository;

//    @Autowired
//    private DealerInfoRepository dealerInfoRepository;
//
//    @Autowired
//    private MetaInfoRepository metaInfoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws ApiException {

//        DealerInfoEntity dealerInfoEntity = dealerInfoRepository.findByUserId(username)
//            .orElseThrow(() -> new ApiException(new ResponseCode(ServiceType.AUTH, CodeName.ERR_INVALID_USER)));
//
//
//        MetaInfoEntity metaInfoEntity = metaInfoRepository.findByMetaId(dealerInfoEntity.getMetaId())
//                .orElseThrow(() -> new ApiException(new ResponseCode(ServiceType.AUTH, CodeName.ERR_AUTH_USER)));

        // 권한을 SimpleGrantedAuthority로 변환
//        Collection<GrantedAuthority> authorities = Arrays.stream(metaInfoEntity.getMetaName().split(","))
//                .map(role -> "ROLE_" + role)  // "ROLE_" 접두어를 추가
//                .map(SimpleGrantedAuthority::new) // 권한을 SimpleGrantedAuthority로 변환
//                .collect(Collectors.toList());
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(dealerInfoEntity.getUserId())
//                .password(dealerInfoEntity.getUserPw())
//                .authorities(authorities)
//                .build();
            Collection<GrantedAuthority> authorities = Arrays.stream("ROLE".split(","))
            .map(role -> "ROLE_" + role)  // "ROLE_" 접두어를 추가
            .map(SimpleGrantedAuthority::new) // 권한을 SimpleGrantedAuthority로 변환
            .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.builder()
                .username("1234")
                .password("555")
                .authorities(authorities)
                .build();
    }
}
