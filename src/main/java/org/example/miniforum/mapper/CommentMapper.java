package org.example.miniforum.mapper;

import org.example.miniforum.dto.request.CommentRequest;
import org.example.miniforum.dto.response.CommentResponse;
import org.example.miniforum.model.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    public Comment toComment(CommentRequest comment);
    public CommentResponse toCommentResponse(Comment comment);
}
