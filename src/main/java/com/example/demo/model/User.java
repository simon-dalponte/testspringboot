package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", catalog = "postgres" ,name = "user")
public class User implements AbstractEntity<String> {

    @Id
    private String id;
    @Column(name = "frist_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "pseudo")
    private String pseudo;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Rating> ratings = new HashSet<>();

    public void addRatings(Rating rating){
        rating.setUser(this);
        getRatings().add(rating);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public String getPseudo() { return pseudo; }
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<Rating> getRatings() { return ratings; }
    public void setRatings(Set<Rating> ratings) { this.ratings = ratings; }
}
