package com.konai.hsyang.konatoyfe.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class User {

    private Long userID;
    private Club club;
    private String username;
    private String password;
    private String nickname;
    private Role role;

    @Builder
    public User(Long userID, String username, String userpassword, String usernickname, Club club, Role role){
        this.userID = userID;
        this.username = username;
        this.password = userpassword;
        this.nickname = usernickname;
        this.club = club;
        this.role = role;
    }

}
