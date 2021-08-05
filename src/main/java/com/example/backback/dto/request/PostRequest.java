package com.example.backback.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    private String description;
    private int privacy; // quyen
    private Integer likeCount;

}
