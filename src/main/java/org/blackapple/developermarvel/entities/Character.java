package org.blackapple.developermarvel.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "character1")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String title;
    @Column(name = "biography")
    private String biography;
    @Column(name = "img")
    private String img;

    @ManyToMany(mappedBy = "characters")
    Set<Comic> comics = new HashSet<>();

    public Character() {
    }

    public Character(Long id, String title, String biography) {
        this.id = id;
        this.title = title;
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBiography() {
        return biography;
    }

    public String getImg() {
        return img;
    }
}
