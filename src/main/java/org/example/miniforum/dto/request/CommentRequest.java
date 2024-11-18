package org.example.miniforum.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.miniforum.model.Post;
import org.example.miniforum.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    private String content;

    private int postId;

    private int userId;
}
