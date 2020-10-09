package com.example.demo.dto;

import com.example.demo.model.Game;
import com.example.demo.model.User;

public class RatingDTO extends AbstractDTO<String> {
    private String id;
    private String commentary;
    private User user;
    private Integer rating;
    private Game game;

    public RatingDTO() {
    }

    public Game getGame() { return game; }

    public void setGame(Game game) { this.game = game; }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getCommentary() {
        return this.commentary;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return this.rating;
    }
}