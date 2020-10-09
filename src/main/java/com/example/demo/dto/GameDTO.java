package com.example.demo.dto;

import com.example.demo.model.Platform;
import com.example.demo.model.Rating;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class GameDTO extends AbstractDTO<String> {
    private String id;
    private String name;
    private String editor;
    private String description;
    private Platform platform;
    private Set<Rating> ratings;

    public GameDTO() {
    }

    public void setId(String id_game) {
        this.id = id_game;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEditor() {
        return this.editor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public Set<Rating> getRatings() { return ratings; }

    public void setRatings(Set<Rating> ratings) { this.ratings = ratings; }
}