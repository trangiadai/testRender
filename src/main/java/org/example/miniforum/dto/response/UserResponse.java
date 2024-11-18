package org.example.miniforum.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniforum.model.Comment;
import org.example.miniforum.model.Post;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private int id;

    private String username;

    private String accountName;

    private String email;

    private String avartarImgUrl;


}
