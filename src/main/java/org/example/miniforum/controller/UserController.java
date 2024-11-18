package org.example.miniforum.controller;

import org.example.miniforum.dto.ApiResponse;
import org.example.miniforum.dto.request.UserLogin;
import org.example.miniforum.dto.request.UserRequest;
import org.example.miniforum.dto.response.LoginResponse;
import org.example.miniforum.dto.response.UserResponse;
import org.example.miniforum.model.User;
import org.example.miniforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody UserLogin userLogin) {
        ApiResponse<LoginResponse> response = ApiResponse.<LoginResponse>builder()
                .data(userService.login(userLogin))
                .message("login success")
                .build();

        return response;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/setAvt")
    public ApiResponse<UserResponse> setAvt(@RequestParam MultipartFile avtImg,
                                            @RequestParam int userId,
                                            @RequestParam String accountName,
                                            @RequestParam String email,
                                            @RequestParam String phone) throws IOException {
        ApiResponse<UserResponse> response =
                ApiResponse.<UserResponse>builder()
                        .data(userService.setAvt(avtImg,userId, accountName, email, phone))
                        .message("set success")
                        .build();
        return response;
    }
}
