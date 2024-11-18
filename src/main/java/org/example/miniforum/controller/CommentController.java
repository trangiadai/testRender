package org.example.miniforum.controller;

import org.example.miniforum.dto.ApiResponse;
import org.example.miniforum.dto.request.CommentRequest;
import org.example.miniforum.dto.response.CommentResponse;
import org.example.miniforum.mapper.CommentMapper;
import org.example.miniforum.model.Comment;
import org.example.miniforum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("/create")
    public ApiResponse<CommentResponse> createComment(@RequestBody CommentRequest comment) {
        ApiResponse<CommentResponse> response = ApiResponse.<CommentResponse>builder()
                .data(commentMapper.toCommentResponse(commentService.createComment(comment)))
                .build();


        return response;
    }
}
