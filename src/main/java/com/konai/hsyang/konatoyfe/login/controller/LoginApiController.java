package com.konai.hsyang.konatoyfe.login.controller;

import com.konai.hsyang.konatoyfe.login.dto.UserJoinRequestDto;
import com.konai.hsyang.konatoyfe.login.service.ClubRestClientService;
import com.konai.hsyang.konatoyfe.login.service.UserRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final UserRestClientService userRestClient;
    private final ClubRestClientService clubRestClient;


    @PostMapping("/api/join")
    public Long join(@RequestBody UserJoinRequestDto requestDto, @RequestParam(required = true) String club){

        clubRestClient.setClub(requestDto, club);
        return userRestClient.join(requestDto);
    }

    @GetMapping("/api/join/checkId")
    public int validateUsername(@RequestParam("username") String username){

        return userRestClient.validateUsername(username);
    }

    @GetMapping("/api/join/checkNick")
    public int validateNickname(@RequestParam("nickname") String nickname){

        return userRestClient.validateNickname(nickname);
    }
}
