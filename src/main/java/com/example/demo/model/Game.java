package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(catalog = "postgres", schema = "public" , name = "game")
public class Game implements AbstractEntity<String> {

    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "editor")
    private String editor;
    @Column(name = "description")
    private String description;
    @Column(name = "platform")
    private Platform platform;

    @OneToMany(mappedBy = "rating")
    @JsonBackReference
    private Set<Rating> ratings = new HashSet<>();

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id_game) {
        id = id_game;
    }

    public Set<Rating> getRatings() { return ratings; }

    public void setRatings(Set<Rating> ratings) { this.ratings = ratings; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
