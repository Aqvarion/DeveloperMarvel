package org.blackapple.developermarvel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id) && Objects.equals(title, character.title) && Objects.equals(biography, character.biography) && Arrays.equals(img, character.img) && Objects.equals(comics, character.comics);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, biography, comics);
        result = 31 * result + Arrays.hashCode(img);
        return result;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", biography='" + biography + '\'' +
                ", img=" + Arrays.toString(img) +
                ", comics=" + comics +
                '}';
    }
}
