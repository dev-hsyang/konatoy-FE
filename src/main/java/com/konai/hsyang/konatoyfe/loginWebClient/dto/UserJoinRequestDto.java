package com.konai.hsyang.konatoyfe.loginWebClient.dto;

import lombok.Getter;

@Getter
public class UserJoinRequestDto {

    private Club clubID;
    private String username;
    private String password;
    private String nickname;
    private Role role;
}
