package com.bootcamp.socialnetwork.service.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank
    @Size(min = 1, max = 60)
    private String username;

    private boolean enabled;

    @Size(max = 60)
    private String firstName;

    @Size(max = 60)
    private String lastName;

    @Size(max = 256)
    private String imageUrl;


    public UserDto() {

    }

    public UserDto(String username, boolean enabled, String firstName, String lastName, String imageUrl) {
        this.username = username;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
    }


    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", enabled=" + enabled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                "}";
    }
}
