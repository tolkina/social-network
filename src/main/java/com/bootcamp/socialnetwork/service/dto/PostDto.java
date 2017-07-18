package com.bootcamp.socialnetwork.service.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

public class PostDto {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(min = 1, max = 60)
    private String username;

    @Size(max = 1000)
    private String text;

    private Date time;

    private Long groupId;


    public PostDto(){

    }

    public PostDto(Long id, String username, String text, Date time, Long groupId) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.time = time;
        this.groupId = groupId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }


    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", groupId=" + groupId +
                '}';
    }
}
