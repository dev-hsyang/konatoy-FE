package com.konai.hsyang.konatoyfe.config.auth;

import com.konai.hsyang.konatoyfe.login.service.UserRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRestClientService userRestClientService;

    // 로그인시 실행
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SessionUser user = new SessionUser(userRestClientService.findByUsername(username));
        if(user != null){
            return new PrincipalDetails(user);
        }
        else throw new UsernameNotFoundException("존재하지 않는 Username입니다.");
    }
}
