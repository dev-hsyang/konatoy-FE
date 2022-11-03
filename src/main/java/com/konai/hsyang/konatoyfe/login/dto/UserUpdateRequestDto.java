package com.konai.hsyang.konatoyfe.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String nickname;
    private String oldPassword;
    private String newPassword;

    public void setEncPassword(String encPassword){

        this.newPassword = encPassword;
    }
}
