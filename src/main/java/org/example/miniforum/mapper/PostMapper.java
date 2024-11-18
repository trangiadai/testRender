package org.example.miniforum.mapper;

import org.example.miniforum.dto.request.PostRequest;
import org.example.miniforum.dto.response.PostResponse;
import org.example.miniforum.model.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
    Post toPost(PostRequest postRequest);
}
