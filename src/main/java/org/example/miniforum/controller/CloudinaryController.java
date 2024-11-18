package org.example.miniforum.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.miniforum.dto.ApiResponse;
import org.example.miniforum.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@Slf4j
public class CloudinaryController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ApiResponse<String> uploadImage(@RequestParam("image") MultipartFile file) {
        ApiResponse<String> response;
        try {
            response = ApiResponse.<String>builder()
                    .data(cloudinaryService.uploadImage(file))
                    .message("upload image successful")
                    .build();
            log.info("json format: {}", response.getData());
            return response;  // Trả về URL ảnh đã upload
        } catch (IOException e) {
            return ApiResponse.<String>builder().message("upload failed").build();
        }
    }
}
