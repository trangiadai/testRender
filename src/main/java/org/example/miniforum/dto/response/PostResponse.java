package org.example.miniforum.dto.response;

import lombok.*;
import org.example.miniforum.model.Image;
import org.example.miniforum.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponse {
    private int id;

    private String title;

    private String content;

    private UserResponse user;

    private List<ImageResponse> images;

    private String location;

    private Long price;

    private Long votes;

    private Long comments;

    private List<CommentResponse> comment;

    private LocalDateTime createDate;
}
