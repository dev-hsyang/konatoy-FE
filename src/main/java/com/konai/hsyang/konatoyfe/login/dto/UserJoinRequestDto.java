package com.konai.hsyang.konatoyfe.login.dto;

import com.konai.hsyang.konatoyfe.login.domain.Club;
import com.konai.hsyang.konatoyfe.login.domain.Role;
import lombok.Getter;

@Getter
public class UserJoinRequestDto {

    private Club clubID;
    private String username;
    private String password;
    private String nickname;
    private Role role;
}
