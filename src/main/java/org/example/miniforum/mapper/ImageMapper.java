package org.example.miniforum.mapper;

import org.example.miniforum.dto.response.ImageResponse;
import org.example.miniforum.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageResponse toImageResponse(Image image);
}
