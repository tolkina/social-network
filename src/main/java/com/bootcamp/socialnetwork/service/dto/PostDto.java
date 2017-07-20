package com.bootcamp.socialnetwork.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * DTO of entity Post.
 */
public class PostDto implements Serializable {

    private Long id;

    @JsonIgnore
    private boolean enabled;

    /**
     * Author of the post (User only).
     */
    private Long authorId;

    /**
     * Owner of the post (Group (negative), User (positive)).
     */
    private Long ownerId;

    private Long time;

    private String text;


    public PostDto() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", enabled=" + enabled +
                ", authorId=" + authorId +
                ", ownerId=" + ownerId +
                ", time=" + time +
                ", text='" + text + '\'' +
                '}';
    }
}
