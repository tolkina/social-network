package com.bootcamp.socialnetwork.service.dto;

import com.bootcamp.socialnetwork.domain.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * Profile DTO of entity User.
 */
public class UserProfileDto implements Serializable {

    private Long id;

    @JsonIgnore
    private boolean enabled;

    private String email;

    private String firstName;

    private String lastName;

    private Sex sex;

    private Date birthday;

    private String imageUrl;

    private String country;

    private String city;

    private String resume;


    public UserProfileDto() {

    }

    public UserProfileDto(String email,
                          String firstName,
                          String lastName,
                          Sex sex,
                          Date birthday,
                          String imageUrl,
                          String country,
                          String city,
                          String resume) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthday = birthday;
        this.imageUrl = imageUrl;
        this.country = country;
        this.city = city;
        this.resume = resume;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }


    @Override
    public String toString() {
        return "UserProfileDto{" +
                "id=" + id +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", imageUrl='" + imageUrl + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", resume='" + resume + '\'' +
                '}';
    }
}
