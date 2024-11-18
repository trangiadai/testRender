package org.example.miniforum.service;

import org.example.miniforum.dto.response.CategoryResponse;
import org.example.miniforum.mapper.CategoryMapper;
import org.example.miniforum.model.Category;
import org.example.miniforum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoriesResponse = new ArrayList<CategoryResponse>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = categoryMapper.toCategoryResponse(category);
            categoriesResponse.add(categoryResponse);
        }
        return categoriesResponse;
    }
}