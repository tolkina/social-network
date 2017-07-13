package com.bootcamp.socialnetwork.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Locale;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "username", length = 60, nullable = false)
    private String username;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "first_name", length = 60)
    private String firstName;

    @Column(name = "last_name", length = 60)
    private String lastName;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "birthday", length = 60)
    private String birthday;

    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Column(name = "country", length = 60)
    private String country;

    @Column(name = "city", length = 60)
    private String city;

    @Column(name = "resume", length = 1000)
    private String resume;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = StringUtils.lowerCase(username, Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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
}
