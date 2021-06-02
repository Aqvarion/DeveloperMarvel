package org.blackapple.developermarvel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Lob
    @Column(name = "img")
    private byte[] img;


    @JsonIgnore
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getBiography() {
        return biography;
    }

    public Set<Comic> getComics() {
        return comics;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
