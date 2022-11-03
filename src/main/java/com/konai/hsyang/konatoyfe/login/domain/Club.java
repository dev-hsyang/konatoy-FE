package com.konai.hsyang.konatoyfe.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Club {

    private Long clubID;
    private String clubname;
    private String clubinfo;

    @Builder
    public Club(String clubname, String clubinfo){
        this.clubname = clubname;
        this.clubinfo = clubinfo;
    }
}
