package org.example.miniforum.controller;

import org.example.miniforum.dto.ApiResponse;
import org.example.miniforum.dto.response.CategoryResponse;
import org.example.miniforum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        ApiResponse<List<CategoryResponse>> response =
                ApiResponse.<List<CategoryResponse>>builder()
                        .data(categoryService.findAll())
                        .message("get categories success")
                        .build();
        return response;
    }

}