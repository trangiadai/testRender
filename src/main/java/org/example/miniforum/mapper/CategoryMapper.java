package org.example.miniforum.mapper;

import org.example.miniforum.dto.response.CategoryResponse;
import org.example.miniforum.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
}
