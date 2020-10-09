package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(catalog = "postgres", name = "rating", schema = "public")
public class Rating implements AbstractEntity<String> {

    @Id
    private String id;
    @Column(name = "commentary")
    private String commentary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @Column(name = "rating")
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCommentary() { return commentary; }
    public void setCommentary(String commentary) { this.commentary = commentary; }
    @JsonIgnore
    public User getUser() { return user; }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setUser(User user) { this.user = user; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}
