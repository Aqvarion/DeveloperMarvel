package org.blackapple.developermarvel.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comic")
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "published")
    private Date published;
    @Column(name = "descript")
    private String description;
    @Column(name = "img")
    private String img;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "comic_character1",
            joinColumns = {@JoinColumn(name="comic_id")},
            inverseJoinColumns = {@JoinColumn(name = "character1_id")}
    )
    Set<Character> characters = new HashSet<>();

    public Comic() {
    }

    public Comic(Long id, String title, String author, Date published, String description, String img) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published = published;
        this.description = description;
        this.img = img;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublished() {
        return published;
    }

    public String getDescription() {
        return description;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public String getImg() {
        return img;
    }
}
