package com.konai.hsyang.konatoyfe.config.auth;

import com.konai.hsyang.konatoyfe.login.domain.Role;
import com.konai.hsyang.konatoyfe.login.domain.User;
import lombok.Getter;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private Role role;

    public SessionUser(User user) {
        this.id= user.getUserID();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.nickname = user.getNickname();
    }
}
