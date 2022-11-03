package com.konai.hsyang.konatoyfe.comments.controller;

import com.konai.hsyang.konatoyfe.comments.dto.CommentsSaveRequestDto;
import com.konai.hsyang.konatoyfe.comments.dto.CommentsUpdateRequestDto;
import com.konai.hsyang.konatoyfe.comments.service.CommentsRestClientService;
import com.konai.hsyang.konatoyfe.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {

    private final CommentsRestClientService commentsRestClient;

    @PostMapping("/api/comments/save/{postID}")
    public ResponseEntity<?> saveComment(@PathVariable Long postID, @RequestBody CommentsSaveRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails principalDetails){


        return ResponseEntity.ok(commentsRestClient.saveComment(principalDetails.getUsername(), postID, requestDto));
    }

    @PostMapping("/api/comments/delete/{commentID}")
    public Long deleteComment(@PathVariable Long commentID){

        return commentsRestClient.deleteComment(commentID);
    }

    @PostMapping("/api/comments/update/{commentID}")
    public Long updateComment(@RequestBody CommentsUpdateRequestDto requestDto, @PathVariable Long commentID){

        return commentsRestClient.updateComment(commentID, requestDto);
    }

    @GetMapping("/api/comments/click/{commentID}")
    public Long findPostByCommentID(@PathVariable Long commentID){

        return commentsRestClient.findPostByCommentID(commentID);
    }
}
