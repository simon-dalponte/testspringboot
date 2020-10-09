package com.example.demo.dto;

import com.example.demo.model.Rating;

import java.util.Set;

public class UserDTO extends AbstractDTO<String> {
    private String id;
    private String first_name;
    private String last_name;
    private String pseudo;
    private String email;
    private Set<Rating> ratings;

    public UserDTO() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setRatings(java.util.Set<com.example.demo.model.Rating> ratings) {
        this.ratings = ratings;
    }

    public java.util.Set<com.example.demo.model.Rating> getRatings() {
        return this.ratings;
    }
}