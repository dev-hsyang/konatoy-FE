package com.konai.hsyang.konatoyfe.mypage.controller;

import com.konai.hsyang.konatoyfe.login.dto.UserUpdateRequestDto;
import com.konai.hsyang.konatoyfe.mypage.service.MypageRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MyPageApiController {

    private final MypageRestClientService mypageRestClient;

    @PostMapping("/api/mypage/update-nickname/{id}")
    public Long updateNickname(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto){

        return mypageRestClient.updateNickname(id, requestDto);
    }

    @PostMapping("/api/mypage/update-password/{id}")
    public Long updatePassword(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto){

        return mypageRestClient.updatePassword(id, requestDto);
    }

    @PostMapping("/api/mypage/delete/{id}")
    public void deleteUser(@PathVariable Long id){

        mypageRestClient.deleteUser(id);
    }
}
