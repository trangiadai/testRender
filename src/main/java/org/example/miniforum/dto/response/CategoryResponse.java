package org.example.miniforum.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryResponse {

    private int id;

    private String name;
}