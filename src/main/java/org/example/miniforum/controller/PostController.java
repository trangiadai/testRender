package org.example.miniforum.controller;

import org.example.miniforum.dto.ApiResponse;
import org.example.miniforum.dto.request.PostRequest;
import org.example.miniforum.dto.response.PostResponse;
import org.example.miniforum.model.Post;
import org.example.miniforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/posts")
    public ApiResponse<List<PostResponse>> getAllPosts() {

        ApiResponse<List<PostResponse>> response;

        response = ApiResponse.<List<PostResponse>>builder()
                .data(postService.getAllPosts())
                .message("getAllPosts thanh cong")
                .build();

        return response;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getPostById(@PathVariable int id) {
        ApiResponse<PostResponse> response;
        PostResponse post = postService.getPostById(id);
        response = ApiResponse.<PostResponse>builder()
                .data(post)
                .message("find by id success")
                .build();

        return response;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@ModelAttribute PostRequest postRequest) {
        Post createdPost = postService.createPost(postRequest);
        return ResponseEntity.ok(createdPost);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/like/{postId}")
    public ApiResponse likePost(@PathVariable int postId) {
        ApiResponse apiResponse = new ApiResponse();
        postService.likePost(postId);
        apiResponse.setMessage("like success");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getByUser/{userId}")
    public ApiResponse<List<PostResponse>> getPostByUser(@PathVariable int userId) {
        ApiResponse<List<PostResponse>> response =
                ApiResponse.<List<PostResponse>>builder()
                        .data(postService.getPostsByUserId(userId))
                        .message("getPostsByUser success")
                        .build();

        return response;
    }

}
