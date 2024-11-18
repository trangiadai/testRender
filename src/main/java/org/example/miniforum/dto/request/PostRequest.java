package org.example.miniforum.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniforum.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    private int userId;
    private String title;
    private String content;
    private String location;
    private Long price;
    private List<MultipartFile> images;
    private List<Category> categories;
}