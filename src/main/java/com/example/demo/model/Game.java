package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(catalog = "postgres", schema = "public" , name = "game")
public class Game implements AbstractEntity<String> {

    @Id
    private String id;

    private String name;

    private String editor;

    private String description;

    private Platform platform;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id_game) {
        id = id_game;
    }

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
