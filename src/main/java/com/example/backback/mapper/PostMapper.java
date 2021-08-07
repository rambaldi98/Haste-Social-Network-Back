package com.example.backback.mapper;

import com.example.backback.domain.entity.post.Like;
import com.example.backback.domain.entity.post.Post;
import com.example.backback.dto.request.PostCreate;

import java.time.Instant;

public class PostMapper {
    private Long id;
    private String description;
    private Integer status;
    private String imageUrl;
    private Instant createDate;
    private Like like;
    public PostMapper(String description, Integer status, String imageUrl, Instant createDate) {
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
    }

    public PostMapper(String description, Integer status, String imageUrl, Instant createDate, Like like) {
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
        this.like = like;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public static Post build(PostCreate postCreate){

    return new Post(
        postCreate.getDescription(),
            postCreate.getStatus(),
            postCreate.getImage(),
            Instant.now());
    }
}
